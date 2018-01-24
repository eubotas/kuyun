package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmModel;
import com.kuyun.eam.dao.model.EamAlarmModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmModelMapper {
    long countByExample(EamAlarmModelExample example);

    int deleteByExample(EamAlarmModelExample example);

    int deleteByPrimaryKey(Integer alarmModelId);

    int insert(EamAlarmModel record);

    int insertSelective(EamAlarmModel record);

    List<EamAlarmModel> selectByExample(EamAlarmModelExample example);

    EamAlarmModel selectByPrimaryKey(Integer alarmModelId);

    int updateByExampleSelective(@Param("record") EamAlarmModel record, @Param("example") EamAlarmModelExample example);

    int updateByExample(@Param("record") EamAlarmModel record, @Param("example") EamAlarmModelExample example);

    int updateByPrimaryKeySelective(EamAlarmModel record);

    int updateByPrimaryKey(EamAlarmModel record);

    void batchInsert(@Param("items") List<EamAlarmModel> items);
}