package com.kuyun.eam.admin.util;

import com.kuyun.eam.admin.model.*;

/**
 * Created by user on 2017-11-20.
 */
public enum KnowledgeCategory {
    TRAINING_VIDEO("training-video", "培训视频", TrainingVideo.class),
    TRAINING_DOC("training-doc", "培训文档", TrainingDoc.class),
    REPAIR_KNOWLEDGE("repair-knowledge", "维修知识", RepairKnowledge.class),
    MAINTAIN_KNOWLEDGE("maintain-knowledge", "保养知识", MaintainKnowledge.class),
    EQUIPMENT_MANUAL("equipment-manual", "设备手册", EquipmentManual.class);

    private String name;
    private String description;
    private Class clazz;

    KnowledgeCategory(String name, String description, Class clazz){
        this.name = name;
        this.description = description;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Class getClazz(){return clazz;}

    public static KnowledgeCategory getKnowledge(String name){
        if (TRAINING_VIDEO.getName().equals(name)){
            return TRAINING_VIDEO;
        }else if (TRAINING_DOC.getName().equals(name)){
            return TRAINING_DOC;
        }else if (REPAIR_KNOWLEDGE.getName().equals(name)){
            return REPAIR_KNOWLEDGE;
        }else if (MAINTAIN_KNOWLEDGE.getName().equals(name)){
            return MAINTAIN_KNOWLEDGE;
        }else if (EQUIPMENT_MANUAL.getName().equals(name)){
            return EQUIPMENT_MANUAL;
        }else {
            return null;
        }
    }
}
