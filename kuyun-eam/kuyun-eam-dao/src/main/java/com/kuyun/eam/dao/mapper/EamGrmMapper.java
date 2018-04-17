package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrm;
import com.kuyun.eam.dao.model.EamGrmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmMapper {
    long countByExample(EamGrmExample example);

    int deleteByExample(EamGrmExample example);

    int deleteByPrimaryKey(String grmId);

    int insert(EamGrm record);

    int insertSelective(EamGrm record);

    List<EamGrm> selectByExample(EamGrmExample example);

    EamGrm selectByPrimaryKey(String grmId);

    int updateByExampleSelective(@Param("record") EamGrm record, @Param("example") EamGrmExample example);

    int updateByExample(@Param("record") EamGrm record, @Param("example") EamGrmExample example);

    int updateByPrimaryKeySelective(EamGrm record);

    int updateByPrimaryKey(EamGrm record);

    void batchInsert(@Param("items") List<EamGrm> items);
}