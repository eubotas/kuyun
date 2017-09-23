package com.kuyun.upms.dao.mapper;

import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsCompanyMapper {
    long countByExample(UpmsCompanyExample example);

    int deleteByExample(UpmsCompanyExample example);

    int deleteByPrimaryKey(Integer companyId);

    int insert(UpmsCompany record);

    int insertSelective(UpmsCompany record);

    List<UpmsCompany> selectByExample(UpmsCompanyExample example);

    UpmsCompany selectByPrimaryKey(Integer companyId);

    int updateByExampleSelective(@Param("record") UpmsCompany record, @Param("example") UpmsCompanyExample example);

    int updateByExample(@Param("record") UpmsCompany record, @Param("example") UpmsCompanyExample example);

    int updateByPrimaryKeySelective(UpmsCompany record);

    int updateByPrimaryKey(UpmsCompany record);

    void batchInsert(@Param("items") List<UpmsCompany> items);
}