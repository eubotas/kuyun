package com.kuyun.upms.rpc.service.impl;

import com.kuyun.upms.dao.mapper.*;
import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.rpc.api.UpmsApiService;
import com.kuyun.upms.rpc.api.UpmsUserOrganizationService;
import com.kuyun.upms.rpc.api.UpmsUserService;
import com.kuyun.upms.rpc.mapper.UpmsApiMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.text.ParseException;
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

    private long getExpirationDate() {
        return 1000 * 60 * 60 * 24 * 30;
    }

    private String getIssuer(){
        return "Kuyun";
    }

    private byte[] getSharedKey(){
        return "KzQq9It4qAlgBMx3wS4m4MrHZnOTKuBh".getBytes();
    }


    public String createToken(Object userId) {
        try {
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

            builder.issuer(getIssuer());
            builder.subject(userId.toString());
            builder.issueTime(new Date());
            builder.notBeforeTime(new Date());
            builder.expirationTime(new Date(new Date().getTime() + getExpirationDate()));
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

    public boolean validateToken(String token) {

        try {
            SignedJWT signed = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifierExtended(getSharedKey(), signed.getJWTClaimsSet());
            return signed.verify(verifier);
        } catch (ParseException ex) {
            return false;
        } catch (JOSEException ex) {
            return false;
        }

    }
}