package com.kuyun.upms.dao.mapper;

import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.model.UpmsUserCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsUserCompanyMapper {
    long countByExample(UpmsUserCompanyExample example);

    int deleteByExample(UpmsUserCompanyExample example);

    int deleteByPrimaryKey(Integer userCompanyId);

    int insert(UpmsUserCompany record);

    int insertSelective(UpmsUserCompany record);

    List<UpmsUserCompany> selectByExample(UpmsUserCompanyExample example);

    UpmsUserCompany selectByPrimaryKey(Integer userCompanyId);

    int updateByExampleSelective(@Param("record") UpmsUserCompany record, @Param("example") UpmsUserCompanyExample example);

    int updateByExample(@Param("record") UpmsUserCompany record, @Param("example") UpmsUserCompanyExample example);

    int updateByPrimaryKeySelective(UpmsUserCompany record);

    int updateByPrimaryKey(UpmsUserCompany record);

    void batchInsert(@Param("items") List<UpmsUserCompany> items);
}