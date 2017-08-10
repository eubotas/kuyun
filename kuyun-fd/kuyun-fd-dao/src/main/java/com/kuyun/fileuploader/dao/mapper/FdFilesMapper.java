package com.kuyun.fileuploader.dao.mapper;

import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.dao.model.FdFilesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FdFilesMapper {
    long countByExample(FdFilesExample example);

    int deleteByExample(FdFilesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FdFiles record);

    int insertSelective(FdFiles record);

    List<FdFiles> selectByExample(FdFilesExample example);

    FdFiles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FdFiles record, @Param("example") FdFilesExample example);

    int updateByExample(@Param("record") FdFiles record, @Param("example") FdFilesExample example);

    int updateByPrimaryKeySelective(FdFiles record);

    int updateByPrimaryKey(FdFiles record);
}