package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamProductLineShiftData;
import com.kuyun.eam.dao.model.EamProductLineShiftDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamProductLineShiftDataMapper {
    long countByExample(EamProductLineShiftDataExample example);

    int deleteByExample(EamProductLineShiftDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EamProductLineShiftData record);

    int insertSelective(EamProductLineShiftData record);

    List<EamProductLineShiftData> selectByExample(EamProductLineShiftDataExample example);

    EamProductLineShiftData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EamProductLineShiftData record, @Param("example") EamProductLineShiftDataExample example);

    int updateByExample(@Param("record") EamProductLineShiftData record, @Param("example") EamProductLineShiftDataExample example);

    int updateByPrimaryKeySelective(EamProductLineShiftData record);

    int updateByPrimaryKey(EamProductLineShiftData record);

    void batchInsert(@Param("items") List<EamProductLineShiftData> items);
}