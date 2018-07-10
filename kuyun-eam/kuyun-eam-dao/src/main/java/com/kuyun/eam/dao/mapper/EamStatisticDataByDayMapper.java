package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamStatisticDataByDay;
import com.kuyun.eam.dao.model.EamStatisticDataByDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamStatisticDataByDayMapper {
    long countByExample(EamStatisticDataByDayExample example);

    int deleteByExample(EamStatisticDataByDayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EamStatisticDataByDay record);

    int insertSelective(EamStatisticDataByDay record);

    List<EamStatisticDataByDay> selectByExample(EamStatisticDataByDayExample example);

    EamStatisticDataByDay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EamStatisticDataByDay record, @Param("example") EamStatisticDataByDayExample example);

    int updateByExample(@Param("record") EamStatisticDataByDay record, @Param("example") EamStatisticDataByDayExample example);

    int updateByPrimaryKeySelective(EamStatisticDataByDay record);

    int updateByPrimaryKey(EamStatisticDataByDay record);

    void batchInsert(@Param("items") List<EamStatisticDataByDay> items);
}