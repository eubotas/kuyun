package com.kuyun.cms.rpc.mapper;

/**
 * 标签VOMapper
 * Created by kuyun on 2017/01/07.
 */
public interface CmsTagVOMapper {

    int up(Integer articleId);

    int down(Integer articleId);

}