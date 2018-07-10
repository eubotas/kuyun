package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamStatisticDataByYear;
import com.kuyun.eam.dao.model.EamStatisticDataByYearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamStatisticDataByYearMapper {
    long countByExample(EamStatisticDataByYearExample example);

    int deleteByExample(EamStatisticDataByYearExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EamStatisticDataByYear record);

    int insertSelective(EamStatisticDataByYear record);

    List<EamStatisticDataByYear> selectByExample(EamStatisticDataByYearExample example);

    EamStatisticDataByYear selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EamStatisticDataByYear record, @Param("example") EamStatisticDataByYearExample example);

    int updateByExample(@Param("record") EamStatisticDataByYear record, @Param("example") EamStatisticDataByYearExample example);

    int updateByPrimaryKeySelective(EamStatisticDataByYear record);

    int updateByPrimaryKey(EamStatisticDataByYear record);

    void batchInsert(@Param("items") List<EamStatisticDataByYear> items);
}