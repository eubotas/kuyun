package com.kuyun.eam.admin.util;

/**
 * Created by user on 2018-03-04.
 */
public enum ActionEnum {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String name;

    ActionEnum(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public boolean match(String name){
        boolean result = false;
        if (getName().equalsIgnoreCase(name)){
            result = true;
        }
        return result;
    }
}
