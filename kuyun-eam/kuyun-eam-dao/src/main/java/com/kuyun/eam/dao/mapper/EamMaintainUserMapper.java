package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamMaintainUser;
import com.kuyun.eam.dao.model.EamMaintainUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamMaintainUserMapper {
    long countByExample(EamMaintainUserExample example);

    int deleteByExample(EamMaintainUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamMaintainUser record);

    int insertSelective(EamMaintainUser record);

    List<EamMaintainUser> selectByExample(EamMaintainUserExample example);

    EamMaintainUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamMaintainUser record, @Param("example") EamMaintainUserExample example);

    int updateByExample(@Param("record") EamMaintainUser record, @Param("example") EamMaintainUserExample example);

    int updateByPrimaryKeySelective(EamMaintainUser record);

    int updateByPrimaryKey(EamMaintainUser record);

    void batchInsert(@Param("items") List<EamMaintainUser> items);
}