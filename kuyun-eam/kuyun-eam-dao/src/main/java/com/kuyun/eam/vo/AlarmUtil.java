package com.kuyun.eam.vo;

import com.kuyun.eam.common.constant.AlarmType;

import java.math.BigDecimal;

/**
 * Created by user on 2018-01-28.
 */
public class AlarmUtil {

    public static String getAlarmContent(String alarmType, BigDecimal upperBound, BigDecimal lowerBound, BigDecimal duration) {
        String result = "";
        if (AlarmType.SAME_DATA_ELEMENT.match(alarmType)){
            //do nothing
        }else if (AlarmType.VAL_ABOVE.match(alarmType)){
            result = "数值高于" + upperBound;
        }else if (AlarmType.VAL_BELOW.match(alarmType)){
            result = "数值低于" + lowerBound;

        }else if (AlarmType.VAL_BETWEEN.match(alarmType)){
            result = "数值在" + upperBound + "和" + lowerBound + "之间";

        }else if (AlarmType.VAL_ABOVE_BELOW_OFM.match(alarmType)){
            result = "数值在" + upperBound + "和" + lowerBound + "之间超过" + duration + "分钟";

        }else if (AlarmType.VAL_ABOVE_BOUND.match(alarmType)){
            result = "数值超过" + duration + "分钟高于" + upperBound;

        }else if (AlarmType.VAL_BELOW_BOUND.match(alarmType)){
            result = "数值超过" + duration + "分钟低于" + lowerBound;

        }else if (AlarmType.OFFLINE.match(alarmType)){
            result = "传感器断开";

        }else if (AlarmType.SWITCH_ON.match(alarmType)){

            result = "开关开启";
        }else if (AlarmType.SWITCH_OFF.match(alarmType)){
            result = "开关关闭";
        }

        return result;
    }
}
