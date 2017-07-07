package com.kuyun.upms.rpc.mapper;


import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsPermission;
import com.kuyun.upms.dao.model.UpmsRole;
import com.kuyun.upms.dao.model.UpmsUser;

import java.util.List;

/**
 * 用户VOMapper
 * Created by kuyun on 2017/01/07.
 */
public interface UpmsApiMapper {

	// 根据用户id获取所拥有的权限
	List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId);

	// 根据用户id获取所属的角色
	List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId);

	// 根据角色id获取所属的用户
	List<UpmsUser> selectUpmsUserByUpmsRoleId(Integer upmsRoleId);

	/**
	 * 根据organizationId获取所在组织的所有父节点
	 * @param organizationId
	 * @return
	 */
	List<UpmsOrganization> selectAllParentOrganizationById(Integer organizationId);

	/**
	 *根据organizationId获取所在组织的所有子节点
	 * @param organizationId
	 * @return
	 */
	List<UpmsOrganization> selectAllChildOrganizationById(Integer organizationId);
	
}