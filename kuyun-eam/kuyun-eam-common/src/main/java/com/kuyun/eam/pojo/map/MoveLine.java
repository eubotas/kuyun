package com.kuyun.eam.pojo.map;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2018-06-15.
 */
public class MoveLine implements Serializable {

    private String fromName;
    private String toName;

    private List<List<String>> coords;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public List<List<String>> getCoords() {
        return coords;
    }

    public void setCoords(List<List<String>> coords) {
        this.coords = coords;
    }
}
