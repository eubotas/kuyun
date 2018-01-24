package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamTicketAssessmentTag;
import com.kuyun.eam.dao.model.EamTicketAssessmentTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamTicketAssessmentTagMapper {
    long countByExample(EamTicketAssessmentTagExample example);

    int deleteByExample(EamTicketAssessmentTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamTicketAssessmentTag record);

    int insertSelective(EamTicketAssessmentTag record);

    List<EamTicketAssessmentTag> selectByExample(EamTicketAssessmentTagExample example);

    EamTicketAssessmentTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamTicketAssessmentTag record, @Param("example") EamTicketAssessmentTagExample example);

    int updateByExample(@Param("record") EamTicketAssessmentTag record, @Param("example") EamTicketAssessmentTagExample example);

    int updateByPrimaryKeySelective(EamTicketAssessmentTag record);

    int updateByPrimaryKey(EamTicketAssessmentTag record);

    void batchInsert(@Param("items") List<EamTicketAssessmentTag> items);
}