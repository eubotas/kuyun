package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamLocation;
import com.kuyun.eam.dao.model.EamLocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamLocationMapper {
    long countByExample(EamLocationExample example);

    int deleteByExample(EamLocationExample example);

    int deleteByPrimaryKey(Integer locationId);

    int insert(EamLocation record);

    int insertSelective(EamLocation record);

    List<EamLocation> selectByExample(EamLocationExample example);

    EamLocation selectByPrimaryKey(Integer locationId);

    int updateByExampleSelective(@Param("record") EamLocation record, @Param("example") EamLocationExample example);

    int updateByExample(@Param("record") EamLocation record, @Param("example") EamLocationExample example);

    int updateByPrimaryKeySelective(EamLocation record);

    int updateByPrimaryKey(EamLocation record);
}