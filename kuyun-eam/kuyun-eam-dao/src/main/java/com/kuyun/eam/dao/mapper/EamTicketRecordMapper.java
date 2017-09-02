package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamTicketRecord;
import com.kuyun.eam.dao.model.EamTicketRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamTicketRecordMapper {
    long countByExample(EamTicketRecordExample example);

    int deleteByExample(EamTicketRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamTicketRecord record);

    int insertSelective(EamTicketRecord record);

    List<EamTicketRecord> selectByExample(EamTicketRecordExample example);

    EamTicketRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamTicketRecord record, @Param("example") EamTicketRecordExample example);

    int updateByExample(@Param("record") EamTicketRecord record, @Param("example") EamTicketRecordExample example);

    int updateByPrimaryKeySelective(EamTicketRecord record);

    int updateByPrimaryKey(EamTicketRecord record);

    void batchInsert(@Param("items") List<EamTicketRecord> items);
}