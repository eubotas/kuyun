package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamCodeValue;
import com.kuyun.eam.dao.model.EamCodeValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamCodeValueMapper {
    long countByExample(EamCodeValueExample example);

    int deleteByExample(EamCodeValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamCodeValue record);

    int insertSelective(EamCodeValue record);

    List<EamCodeValue> selectByExample(EamCodeValueExample example);

    EamCodeValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamCodeValue record, @Param("example") EamCodeValueExample example);

    int updateByExample(@Param("record") EamCodeValue record, @Param("example") EamCodeValueExample example);

    int updateByPrimaryKeySelective(EamCodeValue record);

    int updateByPrimaryKey(EamCodeValue record);

    void batchInsert(@Param("items") List<EamCodeValue> items);
}