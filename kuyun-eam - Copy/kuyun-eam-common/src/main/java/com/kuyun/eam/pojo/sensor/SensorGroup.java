package com.kuyun.eam.pojo.sensor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-08.
 */
public class SensorGroup implements Serializable{

    private String groupName;
    private String type;

    private List<SensorData> vars = new ArrayList<>();

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SensorData> getVars() {
        return vars;
    }

    public void setVars(List<SensorData> vars) {
        this.vars = vars;
    }

    public void addVars(SensorData var){
        this.vars.add(var);
    }
}
