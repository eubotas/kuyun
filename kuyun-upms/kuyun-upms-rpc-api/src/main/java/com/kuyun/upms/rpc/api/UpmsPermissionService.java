package com.kuyun.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.kuyun.common.base.BaseService;
import com.kuyun.upms.dao.model.UpmsPermission;
import com.kuyun.upms.dao.model.UpmsPermissionExample;

/**
* UpmsPermissionService接口
* Created by kuyun on 2017/3/20.
*/
public interface UpmsPermissionService extends BaseService<UpmsPermission, UpmsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}