package com.kuyun.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by user on 2018-03-28.
 */
public class NumberUtil {
    private static Logger LOG = LoggerFactory.getLogger(NumberUtil.class);

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
}
