package com.kuyun.eam.admin.util;

/**
 * Created by user on 2017-11-20.
 */
public enum KnowledgeCategory {
    TRAINING_VIDEO("training-video", "培训视频"),
    TRAINING_DOC("training-doc", "培训文档"),
    REPAIR_KNOWLEDGE("repair-knowledge", "维修知识"),
    MAINTAIN_KNOWLEDGE("maintain-knowledge", "保养知识"),
    EQUIPMENT_MANUAL("equipment-manual", "设备手册");

    private String name;
    private String description;

    KnowledgeCategory(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
}
