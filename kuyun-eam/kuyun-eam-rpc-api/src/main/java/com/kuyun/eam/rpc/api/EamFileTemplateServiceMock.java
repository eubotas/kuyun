package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamFileTemplateMapper;
import com.kuyun.eam.dao.model.EamFileTemplate;
import com.kuyun.eam.dao.model.EamFileTemplateExample;

/**
* 降级实现EamFileTemplateService接口
* Created by kuyun on 2018/4/1.
*/
public class EamFileTemplateServiceMock extends BaseServiceMock<EamFileTemplateMapper, EamFileTemplate, EamFileTemplateExample> implements EamFileTemplateService {

}
