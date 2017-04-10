package com.kuyun.pay.common.constant;

import com.kuyun.common.base.BaseResult;

/**
 * pay系统常量枚举类
 * Created by kuyun on 2017/3/29.
 */
public class PayResult extends BaseResult {

    public PayResult(PayResultConstant payResultConstant, Object data) {
        super(payResultConstant.getCode(), payResultConstant.getMessage(), data);
    }

}
