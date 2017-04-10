package com.kuyun.cms.rpc.mapper;

/**
 * 类目VOMapper
 * Created by kuyun on 2017/01/07.
 */
public interface CmsCategoryVOMapper {

    int up(Integer articleId);

    int down(Integer articleId);

}