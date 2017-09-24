package com.kuyun.marketing.dao.mapper;

import com.kuyun.marketing.dao.model.MktSmsTemplate;
import com.kuyun.marketing.dao.model.MktSmsTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MktSmsTemplateMapper {
    long countByExample(MktSmsTemplateExample example);

    int deleteByExample(MktSmsTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MktSmsTemplate record);

    int insertSelective(MktSmsTemplate record);

    List<MktSmsTemplate> selectByExample(MktSmsTemplateExample example);

    MktSmsTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MktSmsTemplate record, @Param("example") MktSmsTemplateExample example);

    int updateByExample(@Param("record") MktSmsTemplate record, @Param("example") MktSmsTemplateExample example);

    int updateByPrimaryKeySelective(MktSmsTemplate record);

    int updateByPrimaryKey(MktSmsTemplate record);

    void batchInsert(@Param("items") List<MktSmsTemplate> items);
}