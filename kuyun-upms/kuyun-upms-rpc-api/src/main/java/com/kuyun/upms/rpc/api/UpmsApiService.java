package com.kuyun.upms.rpc.api;

import com.kuyun.upms.dao.model.*;
import com.kuyun.upms.dao.vo.UpmsCompanyVo;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.dao.vo.UpmsUserVo;

import java.util.List;

/**
 * upms系统接口
 * Created by kuyun on 2017/2/11.
 */
public interface UpmsApiService {

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param upmsUserId
     * @return
     */
    List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据用户id获取所属的角色
     * @param upmsUserId
     * @return
     */
    List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

    // 根据角色id获取所属的用户
    List<UpmsUser> selectUpmsUserByUpmsRoleId(Integer upmsRoleId);

    /**
     * 根据角色id获取所拥有的权限
     * @param upmsRoleId
     * @return
     */
    List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId);

    /**
     * 根据用户id获取所拥有的权限
     * @param upmsUserId
     * @return
     */
    List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId);

    /**
     * 根据条件获取系统数据
     * @param upmsSystemExample
     * @return
     */
    List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample);

    /**
     * 根据条件获取组织数据
     * @param upmsOrganizationExample
     * @return
     */
    List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample);

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    UpmsUser selectUpmsUserByUsername(String username);


    /**
     * 根据userId获取所在组织的父节点
     * @param upmsUserId
     * @return
     */
    UpmsOrganization selectParentOrganizationByUserId(Integer upmsUserId);

    /**
     * 根据userId获取所在组织的所有父节点
     * @param upmsUserId
     * @return
     */
    List<UpmsOrganization> selectAllParentOrganizationByUserId(Integer upmsUserId);

    /**
     *根据userId获取所在组织的所有子节点
     * @param upmsUserId
     * @return
     */
    List<UpmsOrganization> selectAllChildOrganizationByUserId(Integer upmsUserId);

    /**
     * 根据userId获取所在组织的所有的用户
     * @param upmsUserId
     * @return
     */
    List<UpmsUser> selectUsersByUserId(Integer upmsUserId);

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    int insertUpmsLogSelective(UpmsLog record);

    String createToken(Object userId);

    boolean validateToken(String token);

    void handleReg(String userName, String name, String password, String email, String phone, String company);

    UpmsCompany getUpmsCompany(Integer userId);

    List<UpmsUserVo> selectLoginUsers(UpmsUserVo upmsUserVo);

    UpmsUserCompany getUserCompany(UpmsUser user);

    List<UpmsCompanyVo> selectCompanyEquipments(UpmsCompanyVo upmsCompanyVo);

    Long countCompanyEquipments(UpmsCompanyVo upmsCompanyVo);

    List<UpmsOrgUserVo> selectUsersByOrg(UpmsOrgUserVo orgUserVo);
    Long getUsersCountByOrg(int orgId);
    List<UpmsOrgUserVo> selectOrgUsersByOrgNameCompanyId(UpmsOrgUserVo orgUserVo);

}
