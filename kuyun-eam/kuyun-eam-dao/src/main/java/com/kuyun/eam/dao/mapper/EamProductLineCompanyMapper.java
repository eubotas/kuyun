package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamProductLineCompany;
import com.kuyun.eam.dao.model.EamProductLineCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamProductLineCompanyMapper {
    long countByExample(EamProductLineCompanyExample example);

    int deleteByExample(EamProductLineCompanyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamProductLineCompany record);

    int insertSelective(EamProductLineCompany record);

    List<EamProductLineCompany> selectByExample(EamProductLineCompanyExample example);

    EamProductLineCompany selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamProductLineCompany record, @Param("example") EamProductLineCompanyExample example);

    int updateByExample(@Param("record") EamProductLineCompany record, @Param("example") EamProductLineCompanyExample example);

    int updateByPrimaryKeySelective(EamProductLineCompany record);

    int updateByPrimaryKey(EamProductLineCompany record);

    void batchInsert(@Param("items") List<EamProductLineCompany> items);
}