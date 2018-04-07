package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamOrder;
import com.kuyun.eam.dao.model.EamOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamOrderMapper {
    long countByExample(EamOrderExample example);

    int deleteByExample(EamOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamOrder record);

    int insertSelective(EamOrder record);

    List<EamOrder> selectByExampleWithBLOBs(EamOrderExample example);

    List<EamOrder> selectByExample(EamOrderExample example);

    EamOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamOrder record, @Param("example") EamOrderExample example);

    int updateByExampleWithBLOBs(@Param("record") EamOrder record, @Param("example") EamOrderExample example);

    int updateByExample(@Param("record") EamOrder record, @Param("example") EamOrderExample example);

    int updateByPrimaryKeySelective(EamOrder record);

    int updateByPrimaryKeyWithBLOBs(EamOrder record);

    int updateByPrimaryKey(EamOrder record);

    void batchInsert(@Param("items") List<EamOrder> items);
}