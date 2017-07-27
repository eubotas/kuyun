package com.kuyun.marketing.common.constant;

import com.kuyun.common.base.BaseResult;

/**
 * cms系统常量枚举类
 * Created by kuyun on 2017/2/19.
 */
public class MktResult extends BaseResult {

    public MktResult(MktResultConstant mktResultConstant, Object data) {
        super(mktResultConstant.getCode(), mktResultConstant.getMessage(), data);
    }

}
