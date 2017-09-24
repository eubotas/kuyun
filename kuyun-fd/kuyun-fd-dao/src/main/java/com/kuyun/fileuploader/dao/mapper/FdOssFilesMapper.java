package com.kuyun.fileuploader.dao.mapper;

import com.kuyun.fileuploader.dao.model.FdOssFiles;
import com.kuyun.fileuploader.dao.model.FdOssFilesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FdOssFilesMapper {
    long countByExample(FdOssFilesExample example);

    int deleteByExample(FdOssFilesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FdOssFiles record);

    int insertSelective(FdOssFiles record);

    List<FdOssFiles> selectByExample(FdOssFilesExample example);

    FdOssFiles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FdOssFiles record, @Param("example") FdOssFilesExample example);

    int updateByExample(@Param("record") FdOssFiles record, @Param("example") FdOssFilesExample example);

    int updateByPrimaryKeySelective(FdOssFiles record);

    int updateByPrimaryKey(FdOssFiles record);

    void batchInsert(@Param("items") List<FdOssFiles> items);
}