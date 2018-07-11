package com.kuyun.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by user on 2018-03-28.
 */
public class NumberUtil {
    private static Logger LOG = LoggerFactory.getLogger(NumberUtil.class);

    public static BigDecimal toBigDecimal(Integer value){
        BigDecimal result = BigDecimal.ZERO;
        try{
            result = new BigDecimal(value);
        }catch (Exception e){
            LOG.error("Can't parse {} to BigDecimal", value);
        }
        return result;
    }

    public static BigDecimal toBigDecimal(String value){
        BigDecimal result = BigDecimal.ZERO;
        try{
            result = new BigDecimal(value);
        }catch (Exception e){
            LOG.error("Can't parse {} to BigDecimal", value);
        }
        return result;
    }

    public static Integer toInteger(String value){
        Integer result = -1;
        try{
            result = Integer.valueOf(value);
        }catch (Exception e){
            LOG.error("Can't parse {} to toInteger", value);
        }
        return result;
    }

    public static BigDecimal nonNull(BigDecimal value){
        BigDecimal result = BigDecimal.ZERO;
        if (value != null){
            result = value;
        }
        return result;
    }

    public static boolean isBigDecimal(String value) {
        try {
            new BigDecimal(value);
            return Boolean.TRUE;
        }catch(Exception e){
            return Boolean.FALSE;
        }
    }

    public static boolean isGreaterThanZero(BigDecimal argValue){
        return argValue.compareTo(BigDecimal.ZERO) == 1;
    }

    public static BigDecimal divide(BigDecimal argA, BigDecimal argB){
        BigDecimal result = BigDecimal.ZERO;
        if (!BigDecimal.ZERO.equals(argB)){
            result = argA.divide(argB, 2, BigDecimal.ROUND_HALF_UP);
        }
        return result;
    }

}
