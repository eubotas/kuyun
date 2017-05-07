package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamPartsCategoryMapper;
import com.kuyun.eam.dao.model.EamPartsCategory;
import com.kuyun.eam.dao.model.EamPartsCategoryExample;

/**
* 降级实现EamPartsCategoryService接口
* Created by kuyun on 2017/4/20.
*/
public class EamPartsCategoryServiceMock extends BaseServiceMock<EamPartsCategoryMapper, EamPartsCategory, EamPartsCategoryExample> implements EamPartsCategoryService {

}
