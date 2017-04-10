package com.kuyun.eam.common.constant;

import com.kuyun.common.base.BaseResult;

/**
 * cms系统常量枚举类
 * Created by kuyun on 2017/2/19.
 */
public class EamResult extends BaseResult {

    public EamResult(EamResultConstant cmsResultConstant, Object data) {
        super(cmsResultConstant.getCode(), cmsResultConstant.getMessage(), data);
    }

}
