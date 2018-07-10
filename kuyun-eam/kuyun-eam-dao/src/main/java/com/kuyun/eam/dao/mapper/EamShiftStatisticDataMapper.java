package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamShiftStatisticData;
import com.kuyun.eam.dao.model.EamShiftStatisticDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamShiftStatisticDataMapper {
    long countByExample(EamShiftStatisticDataExample example);

    int deleteByExample(EamShiftStatisticDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EamShiftStatisticData record);

    int insertSelective(EamShiftStatisticData record);

    List<EamShiftStatisticData> selectByExample(EamShiftStatisticDataExample example);

    EamShiftStatisticData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EamShiftStatisticData record, @Param("example") EamShiftStatisticDataExample example);

    int updateByExample(@Param("record") EamShiftStatisticData record, @Param("example") EamShiftStatisticDataExample example);

    int updateByPrimaryKeySelective(EamShiftStatisticData record);

    int updateByPrimaryKey(EamShiftStatisticData record);

    void batchInsert(@Param("items") List<EamShiftStatisticData> items);
}