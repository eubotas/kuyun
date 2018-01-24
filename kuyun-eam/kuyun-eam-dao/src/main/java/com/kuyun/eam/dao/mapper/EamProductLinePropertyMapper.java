package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamProductLineProperty;
import com.kuyun.eam.dao.model.EamProductLinePropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamProductLinePropertyMapper {
    long countByExample(EamProductLinePropertyExample example);

    int deleteByExample(EamProductLinePropertyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamProductLineProperty record);

    int insertSelective(EamProductLineProperty record);

    List<EamProductLineProperty> selectByExample(EamProductLinePropertyExample example);

    EamProductLineProperty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamProductLineProperty record, @Param("example") EamProductLinePropertyExample example);

    int updateByExample(@Param("record") EamProductLineProperty record, @Param("example") EamProductLinePropertyExample example);

    int updateByPrimaryKeySelective(EamProductLineProperty record);

    int updateByPrimaryKey(EamProductLineProperty record);

    void batchInsert(@Param("items") List<EamProductLineProperty> items);
}