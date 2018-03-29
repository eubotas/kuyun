package com.kuyun.upms.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JspUtil {
    public static List getMapList(List list, String valField, String textField){
        List results=new ArrayList();
        Map map=null;
        try {
            for (Object obj : list) {
                map = new HashMap();
                String val = ifNull(getFieldValueByName(valField,obj));
                String text = ifNull(getFieldValueByName(textField,obj));
                map.put("VALUEFIELD",val);
                map.put("DESCFIELD", text);
                results.add(map);
            }
        }catch(Exception ex){}
        return results;
    }

    public static List getList(List list, String valField){
        List results=new ArrayList();
        try {
            for (Object obj : list) {
                String val = ifNull(getFieldValueByName(valField,obj));
                results.add(val);
            }
        }catch(Exception ex){}
        return results;
    }

    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    public static String ifNull(Object obj){
        if(obj == null)
            return "";
        else
            return obj.toString().trim();
    }
}
