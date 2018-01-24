package com.kuyun.eam.util;

import com.google.gson.Gson;
import com.kuyun.eam.pojo.tree.Areas;
import com.kuyun.eam.pojo.tree.CodeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by user on 2017-08-08.
 */
public class AreaUtil {

    private static Logger _log = LoggerFactory.getLogger(AreaUtil.class);
    private Areas areas = new Areas();

    public AreaUtil() {
        Gson gson = new Gson();
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            InputStream in = getClass().getResourceAsStream("/areas.json");

            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            areas = gson.fromJson(br, Areas.class);

        }catch (IOException e) {
            _log.error("areas.json Not Found:" + e.getMessage());
        }

    }

    public String getName(String code) {
        String result = "";

        CodeValue codeValue = areas.getProvince().stream()
                .filter(p -> p.getId().equals(code))
                .findFirst().orElse(null);

        if (codeValue != null){
            result = codeValue.getText();
        }else {
            codeValue = areas.getCity().stream()
                    .filter(p -> p.getId().equals(code))
                    .findFirst().orElse(null);

            if (codeValue != null){
                result = codeValue.getText();
            }else {
                codeValue = areas.getDistrict().stream()
                        .filter(p -> p.getId().equals(code))
                        .findFirst().orElse(null);

                if (codeValue != null){
                    result = codeValue.getText();
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        AreaUtil areaUtil = new AreaUtil();

        System.out.println(areaUtil.getName("340000"));
    }

}
