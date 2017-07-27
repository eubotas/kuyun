package com.kuyun.marketing.dao.mapper;

import com.kuyun.marketing.dao.model.MktSmsSetting;
import com.kuyun.marketing.dao.model.MktSmsSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MktSmsSettingMapper {
    long countByExample(MktSmsSettingExample example);

    int deleteByExample(MktSmsSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MktSmsSetting record);

    int insertSelective(MktSmsSetting record);

    List<MktSmsSetting> selectByExample(MktSmsSettingExample example);

    MktSmsSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MktSmsSetting record, @Param("example") MktSmsSettingExample example);

    int updateByExample(@Param("record") MktSmsSetting record, @Param("example") MktSmsSettingExample example);

    int updateByPrimaryKeySelective(MktSmsSetting record);

    int updateByPrimaryKey(MktSmsSetting record);
}