package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamTicketTag;
import com.kuyun.eam.dao.model.EamTicketTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamTicketTagMapper {
    long countByExample(EamTicketTagExample example);

    int deleteByExample(EamTicketTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamTicketTag record);

    int insertSelective(EamTicketTag record);

    List<EamTicketTag> selectByExample(EamTicketTagExample example);

    EamTicketTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamTicketTag record, @Param("example") EamTicketTagExample example);

    int updateByExample(@Param("record") EamTicketTag record, @Param("example") EamTicketTagExample example);

    int updateByPrimaryKeySelective(EamTicketTag record);

    int updateByPrimaryKey(EamTicketTag record);

    void batchInsert(@Param("items") List<EamTicketTag> items);
}