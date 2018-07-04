package com.kuyun.upms.rpc.service.impl;

import com.kuyun.common.constant.OrgDepartment;
import com.kuyun.common.constant.RoleEnum;
import com.kuyun.common.netease.SMSUtil;
import com.kuyun.common.util.MD5Util;
import com.kuyun.eam.common.constant.TicketType;
import com.kuyun.eam.dao.model.EamTicketType;
import com.kuyun.eam.rpc.api.EamTicketTypeService;
import com.kuyun.upms.dao.mapper.*;
import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.dao.vo.UpmsCompanyVo;
import com.kuyun.upms.dao.vo.UpmsOrgRoleVo;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.dao.vo.UpmsUserVo;
import com.kuyun.upms.rpc.api.*;
import com.kuyun.upms.rpc.mapper.UpmsApiMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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
    UpmsOrganizationService upmsOrganizationService;

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

    @Autowired
    private UpmsOrganizationRoleService upmsOrganizationRoleService;

    @Autowired
    private UpmsRoleService upmsRoleService;
    @Autowired
    private UpmsUserRoleService upmsUserRoleService;

    @Autowired
    private EamTicketTypeService eamTicketTypeService;

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
        return upmsApiMapper.getUserCompanyByUserName(username);
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
    public void handleReg(String userName, String name, String password, String email, String phone, String company, boolean sendToManager){
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

        if (sendToManager){
            sendSMSToManager(upmsUser, company);
        }

    }

    private void sendSMSToManager(UpmsUser argUser, String company) {
        List<UpmsUser> users = getManagerUsers();
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

    private List<UpmsUser> getManagerUsers(){
        List<String> phones = new ArrayList<>(2);
        phones.add("13402559532");
        phones.add("18652424835");
        UpmsUserExample example = new UpmsUserExample();
        example.createCriteria().andPhoneIn(phones).andDeleteFlagEqualTo(Boolean.FALSE);

        return upmsUserService.selectByExample(example);
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
        boolean isNewCompany = false;
        UpmsCompany upmsCompany = getUpmsCompany(company);

        if (upmsCompany == null) {
            upmsCompany = createCompany(company);
            isNewCompany = true;
        }

        UpmsUserCompany upmsUserCompany = new UpmsUserCompany();
        upmsUserCompany.setCompanyId(upmsCompany.getCompanyId());
        upmsUserCompany.setUserId(upmsUser.getUserId());
        upmsUserCompanyService.insert(upmsUserCompany);

        if(!isNewCompany) {
            //#1. insert upms_organization
            Integer organizationId_1 = createOrganization(upmsCompany.getCompanyId(), OrgDepartment.REPAIR_DEPARTMENT.getName());
            Integer organizationId_2 = createOrganization(upmsCompany.getCompanyId(), OrgDepartment.MAINTENANCE_DEPARTMENT.getName());
            Integer organizationId_3 = createOrganization(upmsCompany.getCompanyId(), OrgDepartment.ALARM_DEPARTMENT.getName());

            //2. insert upms_role
            Integer roleId_1 = createRole(upmsCompany.getCompanyId(), RoleEnum.SUPER);
            Integer roleId_2 = createRole(upmsCompany.getCompanyId(), RoleEnum.TICKETCREATE);
            Integer roleId_3 = createRole(upmsCompany.getCompanyId(), RoleEnum.TICKETREPAIR);
            Integer roleId_4 = createRole(upmsCompany.getCompanyId(), RoleEnum.TICKETAPPOINT);

            //3. insert upms_role_permission
            createRolePermissions(roleId_1);
            //4. insert upms_user_organization
            createUserOrganization(upmsUser.getUserId(), organizationId_1);
            createUserOrganization(upmsUser.getUserId(), organizationId_2);
            createUserOrganization(upmsUser.getUserId(), organizationId_3);
            //5. insert upms_user_role
            createUserRole(upmsUser.getUserId(), roleId_1);
            createUserRole(upmsUser.getUserId(), roleId_2);
            createUserRole(upmsUser.getUserId(), roleId_3);
            createUserRole(upmsUser.getUserId(), roleId_4);

            createTicketType(upmsCompany.getCompanyId(), TicketType.REPAIR);
            createTicketType(upmsCompany.getCompanyId(), TicketType.MIANTAIN);
            createTicketType(upmsCompany.getCompanyId(), TicketType.ALARM);
        }else{
            createUserOrganization(upmsUser.getUserId(), getOrganization(upmsCompany.getCompanyId(), OrgDepartment.REPAIR_DEPARTMENT.getName()).getOrganizationId());
            createUserRole(upmsUser.getUserId(), getRole(upmsCompany.getCompanyId(), RoleEnum.TICKETCREATE).getRoleId());
        }
    }

    private void createTicketType(Integer companyId, TicketType ticketTypeEnum){
        EamTicketType ticketType = new EamTicketType();
        ticketType.setCompanyId(companyId);
        ticketType.setCreateTime(new Date());
        ticketType.setDeleteFlag(Boolean.FALSE);
        ticketType.setName(ticketTypeEnum.getName());
        eamTicketTypeService.insertSelective(ticketType);

    }
    private void createUserRole(Integer userId, Integer roleId) {
        UpmsUserRole userRole = new UpmsUserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        userRole.setDeleteFlag(Boolean.FALSE);

        upmsUserRoleService.insertSelective(userRole);
    }

    private void createUserOrganization(Integer userId, Integer organizationId) {
        UpmsUserOrganization userOrganization = new UpmsUserOrganization();
        userOrganization.setUserId(userId);
        userOrganization.setOrganizationId(organizationId);
        userOrganization.setDeleteFlag(Boolean.FALSE);
        upmsUserOrganizationService.insertSelective(userOrganization);
    }

    private void createRolePermissions(Integer roleId){
        int permissionCount = 35;
        List<UpmsRolePermission> rolePermissions = new ArrayList<>(permissionCount);
        for (int i = 1; i <= permissionCount; i++){
            UpmsRolePermission rolePermission = new UpmsRolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(i);
            rolePermission.setDeleteFlag(Boolean.FALSE);

            rolePermissions.add(rolePermission);

        }

        upmsRolePermissionMapper.batchInsert(rolePermissions);
    }

    private Integer createRole(Integer companyId, RoleEnum roleEnum){
        UpmsRole role = new UpmsRole();
        role.setDeleteFlag(Boolean.FALSE);
        role.setCompanyId(companyId);
        role.setCtime(System.currentTimeMillis());
        role.setOrders(Long.valueOf(1));
        role.setName(roleEnum.getName());
        role.setTitle(roleEnum.getTitle());
        role.setDescription(roleEnum.getDescription());
        upmsRoleService.insertSelective(role);
        return role.getRoleId();
    }

    private Integer createOrganization(Integer companyId, String orgName){
        UpmsOrganization organization = new UpmsOrganization();
        organization.setDeleteFlag(Boolean.FALSE);
        organization.setName(orgName);
        organization.setDescription(orgName);
        organization.setCompanyId(companyId);
        organization.setCtime(System.currentTimeMillis());
        upmsOrganizationService.insertSelective(organization);
        return organization.getOrganizationId();
    }

    private UpmsOrganization getOrganization(Integer companyId, String orgName){
        UpmsOrganizationExample example = new UpmsOrganizationExample();
        UpmsOrganizationExample.Criteria criteria =  example.createCriteria();
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        criteria.andNameEqualTo(orgName);
        criteria.andCompanyIdEqualTo(companyId);
        return  upmsOrganizationService.selectFirstByExample(example);
    }

    private UpmsRole getRole(Integer companyId, RoleEnum roleEnum){
        UpmsRoleExample example = new UpmsRoleExample();
        UpmsRoleExample.Criteria criteria =  example.createCriteria();
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        criteria.andNameEqualTo(roleEnum.getName());
        criteria.andCompanyIdEqualTo(companyId);
        return  upmsRoleService.selectFirstByExample(example);
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
    public List<UpmsOrgUserVo> selectUsersByOrg(UpmsOrgUserVo orgUserVo) {
        return upmsApiMapper.selectUsersByOrg(orgUserVo);
    }

    @Override
    public Long getUsersCountByOrg(UpmsOrgUserVo orgUserVo) {
        return upmsApiMapper.getUsersCountByOrg(orgUserVo);
    }

    @Override
    public List<UpmsOrgUserVo> selectOrgUsersByOrgNameCompanyId(UpmsOrgUserVo orgUserVo) {
        return upmsApiMapper.selectOrgUsersByOrgNameCompanyId(orgUserVo);
    }

    @Override
    public Long getRoleCountByOrg(UpmsOrgRoleVo orgRoleVo) {
        return upmsApiMapper.getRoleCountByOrgId(orgRoleVo);
    }

    @Override
    public List<UpmsOrgRoleVo> selectRolesByOrg(UpmsOrgRoleVo orgRoleVo) {
        return upmsApiMapper.selectRolesByOrg(orgRoleVo);
    }

    @Override
    public void createOrgUser(int orgId, List<UpmsUserOrganization> list) {
        UpmsUserOrganizationExample example = new UpmsUserOrganizationExample();
        UpmsUserOrganizationExample.Criteria criteria = example.createCriteria();
        criteria.andOrganizationIdEqualTo(orgId);
        upmsUserOrganizationService.deleteByExample(example);
        upmsUserOrganizationService.batchInsert(list);
    }

    @Override
    public void createOrgRole(int orgId, List<UpmsOrganizationRole> list){
        UpmsOrganizationRoleExample example= new UpmsOrganizationRoleExample();
        UpmsOrganizationRoleExample.Criteria  criteria =example.createCriteria();
        criteria.andOrganizationIdEqualTo(orgId);
        upmsOrganizationRoleService.deleteByExample(example);
        upmsOrganizationRoleService.batchInsert(list);
    }

    @Override
    public int handleSimpleUser(UpmsUser upmsUser, int companyId){
        upmsUser.setLocked(Byte.decode("0"));

        long time = System.currentTimeMillis();
        String salt = UUID.randomUUID().toString().replaceAll("-", "");
        upmsUser.setSalt(salt);
        upmsUser.setPassword(MD5Util.md5(upmsUser.getPassword() + upmsUser.getSalt()));
        upmsUser.setCtime(time);
        upmsUserService.insertSelective(upmsUser);
        int userId = upmsUser.getUserId();
        _log.info("新增用户，主键：userId={}", userId);

        UpmsUserCompany upmsUserCompany = new UpmsUserCompany();
        upmsUserCompany.setCompanyId(companyId);
        upmsUserCompany.setUserId(upmsUser.getUserId());
        upmsUserCompanyService.insert(upmsUserCompany);

        assignPermission(upmsUser);

    //    sendSMSToManager(upmsUser, upmsCompanyService.selectByPrimaryKey(companyId).getName());

        UpmsOrganizationExample ex= new  UpmsOrganizationExample();
        UpmsOrganizationExample.Criteria c=ex.createCriteria();
        c.andCompanyIdEqualTo(companyId);
        c.andDescriptionEqualTo("维修部");
        UpmsOrganization organization = upmsOrganizationService.selectFirstByExample(ex);
        int orgId = organization.getOrganizationId();
        UpmsUserOrganization upmsUserOrganization = new UpmsUserOrganization();
        upmsUserOrganization.setUserId(userId);
        upmsUserOrganization.setOrganizationId(orgId);
        upmsUserOrganizationService.insertSelective(upmsUserOrganization);
        return 1;
    }

    @Override
    public List<UpmsUser> selectUsers(UpmsUserVo upmsUserVo) {
        return upmsApiMapper.selectUsers(upmsUserVo);
    }

    @Override
    public Long countUsers(UpmsUserVo upmsUserVo) {
        return upmsApiMapper.countUsers(upmsUserVo);
    }

    @Override
    public List<UpmsCompany> selectCompanies(UpmsCompanyVo companyVo) {
        return upmsApiMapper.selectCompanies(companyVo);
    }

    @Override
    public UpmsUser getUserCompanyByUserName(String username){
        return upmsApiMapper.getUserCompanyByUserName(username);
    }
}