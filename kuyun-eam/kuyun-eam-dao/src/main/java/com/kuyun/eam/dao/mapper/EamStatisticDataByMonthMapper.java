package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamStatisticDataByMonth;
import com.kuyun.eam.dao.model.EamStatisticDataByMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamStatisticDataByMonthMapper {
    long countByExample(EamStatisticDataByMonthExample example);

    int deleteByExample(EamStatisticDataByMonthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EamStatisticDataByMonth record);

    int insertSelective(EamStatisticDataByMonth record);

    List<EamStatisticDataByMonth> selectByExample(EamStatisticDataByMonthExample example);

    EamStatisticDataByMonth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EamStatisticDataByMonth record, @Param("example") EamStatisticDataByMonthExample example);

    int updateByExample(@Param("record") EamStatisticDataByMonth record, @Param("example") EamStatisticDataByMonthExample example);

    int updateByPrimaryKeySelective(EamStatisticDataByMonth record);

    int updateByPrimaryKey(EamStatisticDataByMonth record);

    void batchInsert(@Param("items") List<EamStatisticDataByMonth> items);
}