package com.kuyun.eam.admin.util;

import com.kuyun.eam.admin.model.Tag;
import com.kuyun.eam.admin.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by user on 2017-11-06.
 */
public class TagUtil {
    @Resource
    private TagRepository tagRepository;

    @Autowired
    private BaseModelUtil baseModelUtil;

    public void handleTag(String tag){
        if (!StringUtils.isEmpty(tag)){
            Tag tagEntry = tagRepository.findOneByTag(tag);
            if (tagEntry == null){
                tagEntry = new Tag();
                tagEntry.setTag(tag);
                baseModelUtil.addAddtionalValue(tagEntry);
                tagRepository.save(tagEntry);
            }
        }
    }
}
