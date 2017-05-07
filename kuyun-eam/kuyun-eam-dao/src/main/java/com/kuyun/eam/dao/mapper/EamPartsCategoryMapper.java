package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamPartsCategory;
import com.kuyun.eam.dao.model.EamPartsCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamPartsCategoryMapper {
    long countByExample(EamPartsCategoryExample example);

    int deleteByExample(EamPartsCategoryExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(EamPartsCategory record);

    int insertSelective(EamPartsCategory record);

    List<EamPartsCategory> selectByExample(EamPartsCategoryExample example);

    EamPartsCategory selectByPrimaryKey(Integer categoryId);

    int updateByExampleSelective(@Param("record") EamPartsCategory record, @Param("example") EamPartsCategoryExample example);

    int updateByExample(@Param("record") EamPartsCategory record, @Param("example") EamPartsCategoryExample example);

    int updateByPrimaryKeySelective(EamPartsCategory record);

    int updateByPrimaryKey(EamPartsCategory record);
}