package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamFileTemplate;
import com.kuyun.eam.dao.model.EamFileTemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamFileTemplateMapper {
    long countByExample(EamFileTemplateExample example);

    int deleteByExample(EamFileTemplateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamFileTemplate record);

    int insertSelective(EamFileTemplate record);

    List<EamFileTemplate> selectByExample(EamFileTemplateExample example);

    EamFileTemplate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamFileTemplate record, @Param("example") EamFileTemplateExample example);

    int updateByExample(@Param("record") EamFileTemplate record, @Param("example") EamFileTemplateExample example);

    int updateByPrimaryKeySelective(EamFileTemplate record);

    int updateByPrimaryKey(EamFileTemplate record);

    void batchInsert(@Param("items") List<EamFileTemplate> items);
}