package com.kuyun.eam.admin.initialize;

import com.kuyun.common.util.RedisUtil;
import com.kuyun.eam.dao.model.EamCodeValue;
import com.kuyun.eam.dao.model.EamCodeValueExample;
import com.kuyun.eam.rpc.api.EamCodeValueService;

import java.util.List;

/**
 * Created by user on 2018-03-26.
 */
public class EamCodeValueInitialize {

    /**
     * 5 minute
     */
    private static int TIME_OUT = 60 * 5;

    public EamCodeValueInitialize(EamCodeValueService eamCodeValueService){
        EamCodeValueExample example = new EamCodeValueExample();
        example.createCriteria().andDeleteFlagEqualTo(Boolean.FALSE);
        if (eamCodeValueService != null){
            List<EamCodeValue> codes = eamCodeValueService.selectByExample(example);

            for(EamCodeValue code : codes){
                String key = code.getCategory() + "::" + code.getCodeName();
                RedisUtil.set(key, code.getCodeValue(), TIME_OUT);
            }
        }

    }

}
