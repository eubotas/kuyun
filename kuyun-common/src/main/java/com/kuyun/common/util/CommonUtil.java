package com.kuyun.common.util;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-06-22.
 */
public class CommonUtil {
    public static final int DEVICE_ID_LENGTH = 16;

    public static String generateRandomToken() {
        return RandomStringUtils.randomAlphanumeric(DEVICE_ID_LENGTH);
//        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static List<Integer> covert(String ids){
        List<Integer> result = new ArrayList<>();
        String[] idArray = ids.split("-");
        for(String idStr : idArray){
            if (StringUtils.isBlank(idStr)) {
                continue;
            }
            Integer id = Integer.parseInt(idStr);
            result.add(id);
        }
        return result;
    }

    //LocalTime time = LocalTime.parse("11:22")

    public static boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
        return !candidate.isBefore(start) && !candidate.isAfter(end);  // Inclusive.
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++){

        System.out.println("token = " + generateRandomToken());
        }
    }
}
