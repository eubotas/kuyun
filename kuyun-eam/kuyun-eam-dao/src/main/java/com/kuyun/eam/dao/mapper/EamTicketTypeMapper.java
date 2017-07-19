package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamTicketType;
import com.kuyun.eam.dao.model.EamTicketTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamTicketTypeMapper {
    long countByExample(EamTicketTypeExample example);

    int deleteByExample(EamTicketTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamTicketType record);

    int insertSelective(EamTicketType record);

    List<EamTicketType> selectByExample(EamTicketTypeExample example);

    EamTicketType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamTicketType record, @Param("example") EamTicketTypeExample example);

    int updateByExample(@Param("record") EamTicketType record, @Param("example") EamTicketTypeExample example);

    int updateByPrimaryKeySelective(EamTicketType record);

    int updateByPrimaryKey(EamTicketType record);
}