package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamDtu;
import com.kuyun.eam.dao.model.EamDtuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamDtuMapper {
    long countByExample(EamDtuExample example);

    int deleteByExample(EamDtuExample example);

    int deleteByPrimaryKey(String dtuId);

    int insert(EamDtu record);

    int insertSelective(EamDtu record);

    List<EamDtu> selectByExample(EamDtuExample example);

    EamDtu selectByPrimaryKey(String dtuId);

    int updateByExampleSelective(@Param("record") EamDtu record, @Param("example") EamDtuExample example);

    int updateByExample(@Param("record") EamDtu record, @Param("example") EamDtuExample example);

    int updateByPrimaryKeySelective(EamDtu record);

    int updateByPrimaryKey(EamDtu record);

    void batchInsert(@Param("items") List<EamDtu> items);
}