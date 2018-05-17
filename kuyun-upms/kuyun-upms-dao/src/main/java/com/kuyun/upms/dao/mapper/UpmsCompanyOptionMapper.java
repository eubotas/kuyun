package com.kuyun.upms.dao.mapper;

import com.kuyun.upms.dao.model.UpmsCompanyOption;
import com.kuyun.upms.dao.model.UpmsCompanyOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsCompanyOptionMapper {
    long countByExample(UpmsCompanyOptionExample example);

    int deleteByExample(UpmsCompanyOptionExample example);

    int deleteByPrimaryKey(Integer companyId);

    int insert(UpmsCompanyOption record);

    int insertSelective(UpmsCompanyOption record);

    List<UpmsCompanyOption> selectByExample(UpmsCompanyOptionExample example);

    UpmsCompanyOption selectByPrimaryKey(Integer companyId);

    int updateByExampleSelective(@Param("record") UpmsCompanyOption record, @Param("example") UpmsCompanyOptionExample example);

    int updateByExample(@Param("record") UpmsCompanyOption record, @Param("example") UpmsCompanyOptionExample example);

    int updateByPrimaryKeySelective(UpmsCompanyOption record);

    int updateByPrimaryKey(UpmsCompanyOption record);

    void batchInsert(@Param("items") List<UpmsCompanyOption> items);
}