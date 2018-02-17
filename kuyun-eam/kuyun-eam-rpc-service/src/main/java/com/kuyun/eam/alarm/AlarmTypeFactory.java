package com.kuyun.eam.alarm;

import com.kuyun.eam.common.constant.AlarmType;
import com.kuyun.eam.dao.model.EamAlarm;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 2017-09-08.
 */
public class AlarmTypeFactory {
    @Autowired
    private ValAboveHandler valAboveHandler;

    @Autowired
    private ValBelowHandler valBelowHandler;


    @Autowired
    private ValAboveBelowOFMHandler valAboveBelowOFMHandler;

    @Autowired
    private XTirYRecHandler xTirYRecHandler;

    @Autowired
    private ValBetweenHandler valBetweenHandler;

    @Autowired
    private ValAboveBoundHandler valAboveBoundHandler;


    @Autowired
    private ValBelowBoundHandler valBelowBoundHandler;

    @Autowired
    private OfflineHandler offlineHandler;

    @Autowired
    private OfflineForMinutesHandler offlineForMinutesHandler;

    @Autowired
    private SwitchOnHandler switchOnHandler;

    @Autowired
    private SwitchOffHandler switchOffHandler;

    @Autowired
    private SameDataElementHandler sameDataElementHandler;

    public AbstractAlarmHandler buildAlarmHandler(EamAlarm alarm){
        AbstractAlarmHandler handler = null;
        if (AlarmType.VAL_ABOVE.match(alarm.getAlarmType())) {
            handler = valAboveHandler;

        } else if (AlarmType.VAL_BELOW.match(alarm.getAlarmType())) {
            handler = valBelowHandler;

        }
        else if (AlarmType.VAL_ABOVE_BELOW_OFM.match(alarm.getAlarmType())) {
            handler = valAboveBelowOFMHandler;

        }
//        else if (AlarmType.X_TIR_Y_REC.match(alarm.getAlarmType())) {
//            handler = xTirYRecHandler;
//
//        }
        else if (AlarmType.VAL_BETWEEN.match(alarm.getAlarmType())) {
            handler = valBetweenHandler;

        } else if (AlarmType.VAL_ABOVE_BOUND.match(alarm.getAlarmType())) {
            handler = valAboveBoundHandler;

        } else if (AlarmType.VAL_BELOW_BOUND.match(alarm.getAlarmType())) {
            handler = valBelowBoundHandler;

        } else if (AlarmType.OFFLINE.match(alarm.getAlarmType())) {
            handler = offlineHandler;

        }
//        else if (AlarmType.OFFLINE_FOR_MINUTES.match(alarm.getAlarmType())) {
//            handler = offlineForMinutesHandler;
//
//        }
        else if (AlarmType.SWITCH_ON.match(alarm.getAlarmType())) {

            handler = switchOnHandler;
        } else if (AlarmType.SWITCH_OFF.match(alarm.getAlarmType())) {

            handler = switchOffHandler;
        }else if (AlarmType.SAME_DATA_ELEMENT.match(alarm.getAlarmType())) {

            handler = sameDataElementHandler;
        }

        return handler;
    }
}
