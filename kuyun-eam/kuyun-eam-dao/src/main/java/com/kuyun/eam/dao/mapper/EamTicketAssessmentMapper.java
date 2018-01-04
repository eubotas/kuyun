package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamTicketAssessment;
import com.kuyun.eam.dao.model.EamTicketAssessmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamTicketAssessmentMapper {
    long countByExample(EamTicketAssessmentExample example);

    int deleteByExample(EamTicketAssessmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamTicketAssessment record);

    int insertSelective(EamTicketAssessment record);

    List<EamTicketAssessment> selectByExample(EamTicketAssessmentExample example);

    EamTicketAssessment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamTicketAssessment record, @Param("example") EamTicketAssessmentExample example);

    int updateByExample(@Param("record") EamTicketAssessment record, @Param("example") EamTicketAssessmentExample example);

    int updateByPrimaryKeySelective(EamTicketAssessment record);

    int updateByPrimaryKey(EamTicketAssessment record);

    void batchInsert(@Param("items") List<EamTicketAssessment> items);
}