package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamTicketMapper {
    long countByExample(EamTicketExample example);

    int deleteByExample(EamTicketExample example);

    int deleteByPrimaryKey(Integer ticketId);

    int insert(EamTicket record);

    int insertSelective(EamTicket record);

    List<EamTicket> selectByExample(EamTicketExample example);

    EamTicket selectByPrimaryKey(Integer ticketId);

    int updateByExampleSelective(@Param("record") EamTicket record, @Param("example") EamTicketExample example);

    int updateByExample(@Param("record") EamTicket record, @Param("example") EamTicketExample example);

    int updateByPrimaryKeySelective(EamTicket record);

    int updateByPrimaryKey(EamTicket record);
}