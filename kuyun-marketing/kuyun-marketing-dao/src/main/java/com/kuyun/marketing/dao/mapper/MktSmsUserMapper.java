package com.kuyun.marketing.dao.mapper;

import com.kuyun.marketing.dao.model.MktSmsUser;
import com.kuyun.marketing.dao.model.MktSmsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MktSmsUserMapper {
    long countByExample(MktSmsUserExample example);

    int deleteByExample(MktSmsUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MktSmsUser record);

    int insertSelective(MktSmsUser record);

    List<MktSmsUser> selectByExample(MktSmsUserExample example);

    MktSmsUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MktSmsUser record, @Param("example") MktSmsUserExample example);

    int updateByExample(@Param("record") MktSmsUser record, @Param("example") MktSmsUserExample example);

    int updateByPrimaryKeySelective(MktSmsUser record);

    int updateByPrimaryKey(MktSmsUser record);
}