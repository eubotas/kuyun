package com.kuyun.upms.rpc.api;

import com.alibaba.fastjson.JSONArray;
import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.upms.dao.mapper.UpmsPermissionMapper;
import com.kuyun.upms.dao.model.UpmsPermission;
import com.kuyun.upms.dao.model.UpmsPermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsPermissionService接口
* Created by kuyun on 2017/3/20.
*/
public class UpmsPermissionServiceMock extends BaseServiceMock<UpmsPermissionMapper, UpmsPermission, UpmsPermissionExample> implements UpmsPermissionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsPermissionServiceMock.class);

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        _log.info("UpmsPermissionServiceMock => getTreeByRoleId");
        return null;
    }

    @Override
    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
        _log.info("UpmsPermissionServiceMock => getTreeByUserId");
        return null;
    }

}
