package com.kuyun.eam.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-02-21.
 */
public class Positions implements Serializable {
    private List<Position> positions = new ArrayList<>();

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public void addPosition(Position position){
        this.positions.add(position);
    }
}
