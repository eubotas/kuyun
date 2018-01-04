package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamTicketAppointedRecord;
import com.kuyun.eam.dao.model.EamTicketAppointedRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamTicketAppointedRecordMapper {
    long countByExample(EamTicketAppointedRecordExample example);

    int deleteByExample(EamTicketAppointedRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamTicketAppointedRecord record);

    int insertSelective(EamTicketAppointedRecord record);

    List<EamTicketAppointedRecord> selectByExample(EamTicketAppointedRecordExample example);

    EamTicketAppointedRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamTicketAppointedRecord record, @Param("example") EamTicketAppointedRecordExample example);

    int updateByExample(@Param("record") EamTicketAppointedRecord record, @Param("example") EamTicketAppointedRecordExample example);

    int updateByPrimaryKeySelective(EamTicketAppointedRecord record);

    int updateByPrimaryKey(EamTicketAppointedRecord record);

    void batchInsert(@Param("items") List<EamTicketAppointedRecord> items);
}