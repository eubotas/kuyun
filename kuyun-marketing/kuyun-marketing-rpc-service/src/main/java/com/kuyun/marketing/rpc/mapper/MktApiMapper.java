package com.kuyun.marketing.rpc.mapper;

import com.kuyun.marketing.vo.MktSmsVo;

import java.util.List;

/**
 * Created by user on 2017-07-23.
 */
public interface MktApiMapper {

    public List<MktSmsVo> getSmsList(MktSmsVo vo);


}
