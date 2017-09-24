package com.kuyun.marketing.dao.mapper;

import com.kuyun.marketing.dao.model.MktSms;
import com.kuyun.marketing.dao.model.MktSmsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MktSmsMapper {
    long countByExample(MktSmsExample example);

    int deleteByExample(MktSmsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MktSms record);

    int insertSelective(MktSms record);

    List<MktSms> selectByExample(MktSmsExample example);

    MktSms selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MktSms record, @Param("example") MktSmsExample example);

    int updateByExample(@Param("record") MktSms record, @Param("example") MktSmsExample example);

    int updateByPrimaryKeySelective(MktSms record);

    int updateByPrimaryKey(MktSms record);

    void batchInsert(@Param("items") List<MktSms> items);
}