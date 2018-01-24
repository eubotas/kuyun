package com.kuyun.eam.common.constant;

public enum BitOrder {

	ABCD("AB CD"),
    CDAB("CD AB"),
    BADC("BA DC"),
    DCBA("DC BA");

    private BitOrder(String name){
        this.name = name;
    }
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
