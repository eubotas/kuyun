package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamMaintainTicket;
import com.kuyun.eam.dao.model.EamMaintainTicketExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamMaintainTicketMapper {
    long countByExample(EamMaintainTicketExample example);

    int deleteByExample(EamMaintainTicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamMaintainTicket record);

    int insertSelective(EamMaintainTicket record);

    List<EamMaintainTicket> selectByExample(EamMaintainTicketExample example);

    EamMaintainTicket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamMaintainTicket record, @Param("example") EamMaintainTicketExample example);

    int updateByExample(@Param("record") EamMaintainTicket record, @Param("example") EamMaintainTicketExample example);

    int updateByPrimaryKeySelective(EamMaintainTicket record);

    int updateByPrimaryKey(EamMaintainTicket record);

    void batchInsert(@Param("items") List<EamMaintainTicket> items);
}