package com.kuyun.upms.rpc.api;

import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.dao.vo.UpmsCompanyVo;
import com.kuyun.upms.dao.vo.UpmsOrgRoleVo;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.dao.vo.UpmsUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 降级实现UpmsApiService接口
 * Created by kuyun on 2017/2/14.
 */
public class UpmsApiServiceMock implements UpmsApiService {

    private static Logger _log = LoggerFactory.getLogger(UpmsApiServiceMock.class);

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsRoleByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsUser> selectUpmsUserByUpmsRoleId(Integer upmsRoleId) {
        return null;
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        _log.info("UpmsApiServiceMock => selectUpmsRolePermisstionByUpmsRoleId");
        return null;
    }

    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        _log.info("UpmsApiServiceMock => selectUpmsUserPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        _log.info("UpmsApiServiceMock => selectUpmsSystemByExample");
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        _log.info("UpmsApiServiceMock => selectUpmsOrganizationByExample");
        return null;
    }

    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        _log.info("UpmsApiServiceMock => selectUpmsUserByUsername");
        return null;
    }

    /**
     * 根据userId获取所在组织的父节点
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public UpmsOrganization selectParentOrganizationByUserId(Integer upmsUserId) {
        return null;
    }

    /**
     * 根据userId获取所在组织的所有父节点
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsOrganization> selectAllParentOrganizationByUserId(Integer upmsUserId) {
        return null;
    }

    /**
     * 根据userId获取所在组织的所有子节点
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsOrganization> selectAllChildOrganizationByUserId(Integer upmsUserId) {
        return null;
    }

    /**
     * 根据userId获取所在组织的所有的用户
     *
     * @param upmsUserId
     * @return
     */
    @Override
    public List<UpmsUser> selectUsersByUserId(Integer upmsUserId) {
        return null;
    }

    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        _log.info("UpmsApiServiceMock => insertSelective");
        return 0;
    }

    @Override
    public String createToken(Object userId) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public void handleReg(String userName, String name, String password, String email, String phone, String company) {

    }

    @Override
    public UpmsCompany getUpmsCompany(Integer userId) {
        return null;
    }

    @Override
    public List<UpmsUserVo> selectLoginUsers(UpmsUserVo upmsUserVo) {
        return null;
    }

    @Override
    public UpmsUserCompany getUserCompany(UpmsUser user) {
        return null;
    }

    @Override
    public List<UpmsCompanyVo> selectCompanyEquipments(UpmsCompanyVo upmsCompanyVo) {
        return null;
    }

    @Override
    public Long countCompanyEquipments(UpmsCompanyVo upmsCompanyVo) {
        return null;
    }

    @Override
    public List<UpmsOrgUserVo> selectUsersByOrg(UpmsOrgUserVo orgUserVo) {
        return null;
    }

    @Override
    public Long getUsersCountByOrg(int orgId) {
        return null;
    }

    @Override
    public List<UpmsOrgUserVo> selectOrgUsersByOrgNameCompanyId(UpmsOrgUserVo orgUserVo) {
        return null;
    }

    @Override
    public Long getRoleCountByOrg(int orgId) {
        return null;
    }

    @Override
    public List<UpmsOrgRoleVo> selectRolesByOrg(UpmsOrgRoleVo orgRoleVo) {
        return null;
    }

    @Override
    public void createOrgUser(int orgId, List<UpmsUserOrganization> list) {

    }

    @Override
    public void createOrgRole(int orgId, List<UpmsOrganizationRole> list) {

    }

    @Override
    public int handleSimpleUser(UpmsUser upmsUser, int companyId) {
        return 0;
    }

    @Override
    public int updateUser(UpmsUser upmsUser) {
        return 0;
    }


}
