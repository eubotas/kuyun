package com.kuyun.eam.pojo.map;

import java.io.Serializable;
import java.util.List;

/**
 * Created by user on 2018-06-15.
 */
public class MapData implements Serializable {

    private List<City> citys;

    private List<MoveLine> moveLines;

    public List<City> getCitys() {
        return citys;
    }

    public void setCitys(List<City> citys) {
        this.citys = citys;
    }

    public List<MoveLine> getMoveLines() {
        return moveLines;
    }

    public void setMoveLines(List<MoveLine> moveLines) {
        this.moveLines = moveLines;
    }
}
