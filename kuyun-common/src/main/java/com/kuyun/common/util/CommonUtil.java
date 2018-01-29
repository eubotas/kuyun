package com.kuyun.common.util;

import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalTime;

/**
 * Created by user on 2017-06-22.
 */
public class CommonUtil {
    public static final int DEVICE_ID_LENGTH = 16;

    public static String generateRandomToken() {
        return RandomStringUtils.randomAlphanumeric(DEVICE_ID_LENGTH);
//        return UUID.randomUUID().toString().replaceAll("-", "");
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
