package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamParts;
import com.kuyun.eam.dao.model.EamPartsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamPartsMapper {
    long countByExample(EamPartsExample example);

    int deleteByExample(EamPartsExample example);

    int deleteByPrimaryKey(Integer partId);

    int insert(EamParts record);

    int insertSelective(EamParts record);

    List<EamParts> selectByExample(EamPartsExample example);

    EamParts selectByPrimaryKey(Integer partId);

    int updateByExampleSelective(@Param("record") EamParts record, @Param("example") EamPartsExample example);

    int updateByExample(@Param("record") EamParts record, @Param("example") EamPartsExample example);

    int updateByPrimaryKeySelective(EamParts record);

    int updateByPrimaryKey(EamParts record);
}