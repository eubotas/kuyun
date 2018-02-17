package com.kuyun.upms.rpc.service.impl;

import com.kuyun.common.netease.SMSUtil;
import com.kuyun.common.util.MD5Util;
import com.kuyun.upms.dao.mapper.*;
import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.dao.vo.UpmsCompanyVo;
import com.kuyun.upms.dao.vo.UpmsUserCompanyVO;
import com.kuyun.upms.dao.vo.UpmsUserVo;
import com.kuyun.upms.rpc.api.*;
import com.kuyun.upms.rpc.mapper.UpmsApiMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户service实现
 * Created by kuyun on 2016/01/19.
 */
@Service
@Transactional
public class UpmsApiServiceImpl implements UpmsApiService {

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceImpl.class);

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Autowired
    UpmsApiMapper upmsApiMapper;

    @Autowired
    UpmsRolePermissionMapper upmsRolePermissionMapper;

    @Autowired
    UpmsUserPermissionMapper upmsUserPermissionMapper;

    @Autowired
    UpmsSystemMapper upmsSystemMapper;

    @Autowired
    UpmsOrganizationMapper upmsOrganizationMapper;

    @Autowired
    UpmsLogMapper upmsLogMapper;

    @Autowired
    UpmsUserService upmsUserService;

    @Autowired
    UpmsUserOrganizationService upmsUserOrganizationService;

    @Autowired
    private UpmsCompanyService upmsCompanyService;

    @Autowired
    private UpmsUserCompanyService upmsUserCompanyService;

    @Autowired
    private UpmsUserPermissionService upmsUserPermissionService;

    @Autowired
    private UpmsPermissionService upmsPermissionService;

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            _log.info("selectUpmsPermissionByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsPermission> upmsPermissions = upmsApiMapper.selectUpmsPermissionByUpmsUserId(upmsUserId);
        return upmsPermissions;
    }

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        // 用户不存在或锁定状态
        UpmsUser upmsUser = upmsUserMapper.selectByPrimaryKey(upmsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            _log.info("selectUpmsRoleByUpmsUserId : upmsUserId={}", upmsUserId);
            return null;
        }
        List<UpmsRole> upmsRoles = upmsApiMapper.selectUpmsRoleByUpmsUserId(upmsUserId);
        return upmsRoles;
    }

    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        UpmsRolePermissionExample upmsRolePermissionExample = new UpmsRolePermissionExample();
        upmsRolePermissionExample.createCriteria()
                .andRoleIdEqualTo(upmsRoleId);
        List<UpmsRolePermission> upmsRolePermissions = upmsRolePermissionMapper.selectByExample(upmsRolePermissionExample);
        return upmsRolePermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        UpmsUserPermissionExample upmsUserPermissionExample = new UpmsUserPermissionExample();
        upmsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(upmsUserId);
        List<UpmsUserPermission> upmsUserPermissions = upmsUserPermissionMapper.selectByExample(upmsUserPermissionExample);
        return upmsUserPermissions;
    }

    /**
     * 根据条件获取系统数据
     * @param upmsSystemExample
     * @return
     */
    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.selectByExample(upmsSystemExample);
    }

    /**
     * 根据条件获取组织数据
     * @param upmsOrganizationExample
     * @return
     */
    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        return upmsOrganizationMapper.selectByExample(upmsOrganizationExample);
    }

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(username);
        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

    @Override
    public UpmsOrganization selectParentOrganizationByUserId(Integer upmsUserId) {
        UpmsOrganization result = null;
        UpmsUserOrganization userOrganization = getUpmsUserOrganization(upmsUserId);
        if (userOrganization != null){
            List<UpmsOrganization> organizations = upmsApiMapper.selectAllParentOrganizationById(userOrganization.getOrganizationId());
            if (!organizations.isEmpty()){
                result = organizations.get(0);
            }

        }
        return result;
    }

    /**
     * 根据userId获取所在组织的所有父节点
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsOrganization> selectAllParentOrganizationByUserId(Integer upmsUserId) {
        UpmsUserOrganization userOrganization = getUpmsUserOrganization(upmsUserId);
        return upmsApiMapper.selectAllParentOrganizationById(userOrganization.getOrganizationId());
    }

    /**
     * 根据userId获取所在组织的所有子节点
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsOrganization> selectAllChildOrganizationByUserId(Integer upmsUserId) {
        UpmsUserOrganization userOrganization = getUpmsUserOrganization(upmsUserId);
        return  upmsApiMapper.selectAllChildOrganizationById(userOrganization.getOrganizationId());
    }

    private UpmsUserOrganization getUpmsUserOrganization(Integer upmsUserId) {
        UpmsUserOrganizationExample userOrganizationExample = new UpmsUserOrganizationExample();
        userOrganizationExample.createCriteria().andUserIdEqualTo(upmsUserId);
        return upmsUserOrganizationService.selectFirstByExample(userOrganizationExample);
    }

    @Override
    public List<UpmsUser> selectUsersByUserId(Integer upmsUserId){
        List<UpmsUser> result = new ArrayList<>();
        UpmsOrganization organization = selectParentOrganizationByUserId(upmsUserId);

        if (organization != null){
            List<Integer> userIds = getUserIds(organization);

            UpmsUserExample userExample = new UpmsUserExample();
            userExample.createCriteria().andUserIdIn(userIds);
            result = upmsUserService.selectByExample(userExample);

        }
        return result;
    }

    private List<Integer> getUserIds(UpmsOrganization organization) {
        UpmsUserOrganizationExample userOrganizationExample = new UpmsUserOrganizationExample();
        userOrganizationExample.createCriteria().andOrganizationIdEqualTo(organization.getOrganizationId());
        List<UpmsUserOrganization> userOrganizations = upmsUserOrganizationService.selectByExample(userOrganizationExample);
        List<Integer> userIds = new ArrayList<>();
        if (userOrganizations != null && !userOrganizations.isEmpty()){
            for (UpmsUserOrganization userOrganization : userOrganizations){
                userIds.add(userOrganization.getUserId());
            }
        }
        return userIds;
    }

    // 根据角色id获取所属的用户
    @Override
    public List<UpmsUser> selectUpmsUserByUpmsRoleId(Integer upmsRoleId){
        return upmsApiMapper.selectUpmsUserByUpmsRoleId(upmsRoleId);
    }

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        return upmsLogMapper.insertSelective(record);
    }


    private byte[] generateSharedKey() {
        SecureRandom random = new SecureRandom();
        byte[] sharedKey = new byte[32];
        random.nextBytes(sharedKey);
        return sharedKey;
    }

    private Date getExpirationDate() {
        LocalDate localDate = LocalDate.now().plusMonths(3);

        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private String getIssuer(){
        return "Kuyun";
    }

    private byte[] getSharedKey(){
        return "KzQq9It4qAlgBMx3wS4m4MrHZnOTKuBh".getBytes();
    }


    @Override
    public String createToken(Object userId) {
        try {
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

            builder.issuer(getIssuer());
            builder.subject(userId.toString());
            builder.issueTime(new Date());
            builder.notBeforeTime(new Date());
            builder.expirationTime(getExpirationDate());
            builder.jwtID(UUID.randomUUID().toString());

            JWTClaimsSet claimsSet = builder.build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

            Payload payload = new Payload(claimsSet.toJSONObject());

            JWSObject jwsObject = new JWSObject(header, payload);

            JWSSigner signer = new MACSigner(getSharedKey());
            jwsObject.sign(signer);
            return jwsObject.serialize();
        } catch (JOSEException ex) {
            _log.error("create token error:"+ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean validateToken(String token) {

        try {
            SignedJWT signed = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifierExtended(getSharedKey(), signed.getJWTClaimsSet());
            return signed.verify(verifier);
        } catch (ParseException ex) {
            System.out.println("Parse token error: = " + ex);
            _log.error("1Parse token error:"+ex.getMessage());
            return false;
        } catch (JOSEException ex) {
            System.out.println("2Parse token error: = " + ex);
            _log.error("Parse token error:"+ex.getMessage());
            return false;
        }

    }

    @Override
    public void handleReg(String userName, String name, String password, String email, String phone, String company){
        UpmsUser upmsUser = new UpmsUser();
        upmsUser.setUsername(userName);
        upmsUser.setRealname(name);
        upmsUser.setEmail(email);
        upmsUser.setPhone(phone);
        upmsUser.setLocked(Byte.decode("0"));

        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(MD5Util.md5(password + upmsUser.getSalt()));
        upmsUser.setCtime(time);

        upmsUserService.insertSelective(upmsUser);
        _log.info("新增用户，主键：userId={}", upmsUser.getUserId());

        handleUserCompany(company, upmsUser);

        assignPermission(upmsUser);

        sendSMSToManager(upmsUser, company);
    }

    private void sendSMSToManager(UpmsUser argUser, String company) {
        //TODO: send sms to manager
        int roleId = 1;
        List<UpmsUser> users = selectUpmsUserByUpmsRoleId(roleId);
        JSONArray phones = new JSONArray();
        for (UpmsUser user : users) {
            if (!StringUtils.isEmpty(user.getPhone())) {
                phones.add(user.getPhone());
            }
        }

        String templateId = "3056289";
        //有新用户注册！姓名：%s，公司：%s，电话：%s，邮箱：%s。
        JSONArray params = new JSONArray();
        params.add(argUser.getRealname());
        params.add(company);
        params.add(argUser.getPhone());
        params.add(argUser.getEmail());

        if (phones.size() > 0) {
            SMSUtil.sendTemplate(templateId, phones.toString(), params.toString());
        }
    }

    private void assignPermission(UpmsUser upmsUser) {

        List<UpmsPermission> permissionList = getPermissions();
        List<UpmsUserPermission> items = new ArrayList<>();

        for (UpmsPermission permission : permissionList) {
            UpmsUserPermission userPermission = new UpmsUserPermission();
            userPermission.setUserId(upmsUser.getUserId());
            userPermission.setPermissionId(permission.getPermissionId());
            userPermission.setType(Byte.decode("1"));
            items.add(userPermission);
        }
        upmsUserPermissionService.batchInsert(items);
    }

    private List<UpmsPermission> getPermissions() {
        UpmsPermissionExample example = new UpmsPermissionExample();
        example.createCriteria().andPermissionIdGreaterThanOrEqualTo(200)
                .andPermissionIdLessThan(2000);

        return upmsPermissionService.selectByExample(example);
    }

    private void handleUserCompany(String company, UpmsUser upmsUser) {

        UpmsCompany upmsCompany = getUpmsCompany(company);

        if (upmsCompany == null) {
            upmsCompany = createCompany(company);
        }

        createUserCompany(upmsUser, upmsCompany.getCompanyId());
    }

    private void createUserCompany(UpmsUser upmsUser, Integer companyId) {
        UpmsUserCompany upmsUserCompany = new UpmsUserCompany();
        upmsUserCompany.setCompanyId(companyId);
        upmsUserCompany.setUserId(upmsUser.getUserId());
        upmsUserCompanyService.insert(upmsUserCompany);
    }

    private UpmsCompany createCompany(String company) {
        UpmsCompany result = new UpmsCompany();
        result.setName(company);
        result.setCreateTime(new Date());
        result.setDeleteFlag(Boolean.FALSE);
        upmsCompanyService.insertSelective(result);
        return result;
    }

    private UpmsCompany getUpmsCompany(String company) {
        UpmsCompanyExample companyExample = new UpmsCompanyExample();
        companyExample.createCriteria().andNameEqualTo(company);
        return upmsCompanyService.selectFirstByExample(companyExample);
    }

    @Override
    public UpmsCompany getUpmsCompany(Integer userId){
        return upmsApiMapper.selectUpmsCompany(userId);
    }

    @Override
    public List<UpmsUserVo> selectLoginUsers(UpmsUserVo upmsUserVo) {
        return upmsApiMapper.selectLoginUsers(upmsUserVo);
    }

    @Override
    public List<UpmsUserVo> selectUsers(UpmsUserVo upmsUserVo) {
        return upmsApiMapper.selectUsers(upmsUserVo);
    }

    @Override
    public long countUsers(UpmsUserVo upmsUserVo) {
        return upmsApiMapper.countUsers(upmsUserVo);
    }

    @Override
    public UpmsUserCompany getUserCompany(UpmsUser user){
        UpmsUserCompanyExample example = new UpmsUserCompanyExample();
        example.createCriteria().andUserIdEqualTo(user.getUserId());
        return upmsUserCompanyService.selectFirstByExample(example);
    }

    @Override
    public List<UpmsCompanyVo> selectCompanyEquipments(UpmsCompanyVo upmsCompanyVo) {
        return upmsApiMapper.selectCompanyEquipments(upmsCompanyVo);
    }

    @Override
    public Long countCompanyEquipments(UpmsCompanyVo upmsCompanyVo) {
        return upmsApiMapper.countCompanyEquipments(upmsCompanyVo);
    }

    @Override
    public List<UpmsUser> selectUsers(UpmsUserCompanyVO userCompany) {
        return upmsApiMapper.selectUsersByUserCompany(userCompany);
    }

    @Override
    public int createUser(UpmsUser upmsUser, UpmsUserCompany upmsUserCompany) {
        int count = upmsUserService.insertSelective(upmsUser);
        createUserCompany(upmsUser, upmsUserCompany.getCompanyId());
        return count;
    }

}