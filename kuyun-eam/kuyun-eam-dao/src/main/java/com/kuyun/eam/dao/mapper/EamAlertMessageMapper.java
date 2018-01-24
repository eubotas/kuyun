package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlertMessage;
import com.kuyun.eam.dao.model.EamAlertMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlertMessageMapper {
    long countByExample(EamAlertMessageExample example);

    int deleteByExample(EamAlertMessageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlertMessage record);

    int insertSelective(EamAlertMessage record);

    List<EamAlertMessage> selectByExample(EamAlertMessageExample example);

    EamAlertMessage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlertMessage record, @Param("example") EamAlertMessageExample example);

    int updateByExample(@Param("record") EamAlertMessage record, @Param("example") EamAlertMessageExample example);

    int updateByPrimaryKeySelective(EamAlertMessage record);

    int updateByPrimaryKey(EamAlertMessage record);

    void batchInsert(@Param("items") List<EamAlertMessage> items);
}