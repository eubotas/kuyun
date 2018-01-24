package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamProtocol;
import com.kuyun.eam.dao.model.EamProtocolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamProtocolMapper {
    long countByExample(EamProtocolExample example);

    int deleteByExample(EamProtocolExample example);

    int deleteByPrimaryKey(Integer protocolId);

    int insert(EamProtocol record);

    int insertSelective(EamProtocol record);

    List<EamProtocol> selectByExample(EamProtocolExample example);

    EamProtocol selectByPrimaryKey(Integer protocolId);

    int updateByExampleSelective(@Param("record") EamProtocol record, @Param("example") EamProtocolExample example);

    int updateByExample(@Param("record") EamProtocol record, @Param("example") EamProtocolExample example);

    int updateByPrimaryKeySelective(EamProtocol record);

    int updateByPrimaryKey(EamProtocol record);

    void batchInsert(@Param("items") List<EamProtocol> items);
}