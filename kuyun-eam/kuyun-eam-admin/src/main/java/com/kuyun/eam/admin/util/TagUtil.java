package com.kuyun.eam.admin.util;

import com.kuyun.eam.admin.model.Tag;
import com.kuyun.eam.admin.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by user on 2017-11-06.
 */
public class TagUtil {
    @Resource
    private TagRepository tagRepository;

    @Autowired
    private BaseModelUtil baseModelUtil;

    public void handleTag(String action, String oldTag, String newTag){
        Tag tagEntry = null;
        if (ActionEnum.CREATE.match(action)){
            tagEntry = tagRepository.findOneByTag(newTag);
            handleCreateTag(newTag, tagEntry);
        }else if (ActionEnum.DELETE.match(action)){
            tagEntry = tagRepository.findOneByTag(newTag);
            handleDeleteTag(tagEntry);
        }else if (ActionEnum.UPDATE.match(action)){
            Tag oldTagEntry = tagRepository.findOneByTag(oldTag);
            if (oldTagEntry != null && !oldTagEntry.getTag().equalsIgnoreCase(newTag)){
                handleDeleteTag(oldTagEntry);
                handleCreateTag(newTag, tagEntry);
            }
        }
    }

    private void handleDeleteTag(Tag tagEntry) {
        if (tagEntry != null){
            int count = tagEntry.getCount() - 1;
            if (count == 0){
                tagRepository.delete(tagEntry);
            }else {
                tagEntry.setCount(count);
                baseModelUtil.addAddtionalValue(tagEntry);
                tagRepository.save(tagEntry);
            }

        }
    }

    private void handleCreateTag(String tag, Tag tagEntry) {
        if (tagEntry == null){
            tagEntry = new Tag();
            tagEntry.setTag(tag);
            tagEntry.setCount(1);
            baseModelUtil.addAddtionalValue(tagEntry);
            tagRepository.save(tagEntry);
        }else {
            int count = tagEntry.getCount() + 1;
            tagEntry.setCount(count);
            baseModelUtil.addAddtionalValue(tagEntry);
            tagRepository.save(tagEntry);
        }
    }
}