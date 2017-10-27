package com.kuyun.eam.alarm;

import com.kuyun.common.jpush.JpushUtil;
import com.kuyun.common.mail.service.EmailService;
import com.kuyun.common.netease.SMSUtil;
import com.kuyun.eam.common.constant.AlarmStatus;
import com.kuyun.eam.common.constant.AlarmTarget;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserExample;
import com.kuyun.upms.rpc.api.UpmsUserService;
import net.sf.json.JSONArray;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 2017-09-08.
 */
public abstract class AbstractAlarmHandler {
    private static Logger _log = LoggerFactory.getLogger(AbstractAlarmHandler.class);

    private final static String TEMPLATE_ID = "3084134";
    private final static String CLEAR_TEMPLATE_ID = "3119134";
    @Autowired
    private EamAlarmRecordService eamAlarmRecordService;

    @Autowired
    private EamAlarmRecordHistoryService eamAlarmRecordHistoryService;

    @Autowired
    private EamAlarmTargetUserService eamAlarmTargetUserService;

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private EmailService emailService;

    @Autowired
    protected EamSensorDataService eamSensorDataService;

    @Autowired
    private  EamSensorDataHistoryService eamSensorDataHistoryService;

    @Autowired
    private EamEquipmentService eamEquipmentService;

    @Autowired
    private EamEquipmentModelPropertiesService eamEquipmentModelPropertiesService;

    @Autowired
    private JpushUtil jpushUtil;

    public void process(EamSensorData sensorData, EamAlarm alarm) {
        EamAlarmRecord alarmRecord = getAlarmRecord(sensorData, alarm);

        if (metAlarmCondition(sensorData, alarm) && alarmRecord == null) {
            //1. create alarm record
            createAlarmRecord(sensorData, alarm);
            //2. create alarm record history
            createAlarmRecordHistory(sensorData, alarm, AlarmStatus.ANU);
            //3. send message
            sendAlarmMessage(sensorData, alarm, false);
        }

        if (!metAlarmCondition(sensorData, alarm) && alarmRecord != null){
            //1. update alarm record
            updateAlarmRecord(alarmRecord, sensorData);
            //2. create alarm record history
            updateAlarmRecordHistory(sensorData, alarm, AlarmStatus.CNU);
            //3. send clear message
            sendAlarmMessage(sensorData, alarm, true);
        }
    }

    private void updateAlarmRecordHistory(EamSensorData sensorData, EamAlarm alarm, AlarmStatus alarmStatus) {
        EamAlarmRecordHistory alarmRecordHistory = getAlarmRecordHistory(sensorData, alarm);
        if (alarmRecordHistory != null){
            alarmRecordHistory.setAlarmStatus(alarmStatus.getCode());
            alarmRecordHistory.setAlarmClearValue(sensorData.getStringValue());
            alarmRecordHistory.setUpdateTime(new Date());

            eamAlarmRecordHistoryService.updateByPrimaryKeySelective(alarmRecordHistory);
        }

    }

    private EamAlarmRecord getAlarmRecord(EamSensorData sensorData, EamAlarm alarm){
        EamAlarmRecordExample example = new EamAlarmRecordExample();
        EamAlarmRecordExample.Criteria criteria1 = example.createCriteria();
        criteria1.andAlarmIdEqualTo(alarm.getAlarmId())
                .andEquipmentIdEqualTo(sensorData.getEquipmentId())
                .andEquipmentModelPropertyIdEqualTo(alarm.getEquipmentModelPropertyId())
                .andAlarmStatusEqualTo(AlarmStatus.ANU.getCode());

        EamAlarmRecordExample.Criteria criteria2 = example.createCriteria();
        criteria2.andAlarmIdEqualTo(alarm.getAlarmId())
                .andEquipmentIdEqualTo(sensorData.getEquipmentId())
                .andEquipmentModelPropertyIdEqualTo(alarm.getEquipmentModelPropertyId())
                .andAlarmStatusEqualTo(AlarmStatus.ANA.getCode());

        example.or(criteria2);

        return eamAlarmRecordService.selectFirstByExample(example);
    }

    private EamAlarmRecordHistory getAlarmRecordHistory(EamSensorData sensorData, EamAlarm alarm){
        EamAlarmRecordHistoryExample example = new EamAlarmRecordHistoryExample();
        EamAlarmRecordHistoryExample.Criteria criteria1 = example.createCriteria();
        criteria1.andAlarmIdEqualTo(alarm.getAlarmId())
                .andEquipmentIdEqualTo(sensorData.getEquipmentId())
                .andEquipmentModelPropertyIdEqualTo(alarm.getEquipmentModelPropertyId())
                .andAlarmStatusEqualTo(AlarmStatus.ANU.getCode());

        EamAlarmRecordHistoryExample.Criteria criteria2 = example.createCriteria();
        criteria2.andAlarmIdEqualTo(alarm.getAlarmId())
                .andEquipmentIdEqualTo(sensorData.getEquipmentId())
                .andEquipmentModelPropertyIdEqualTo(alarm.getEquipmentModelPropertyId())
                .andAlarmStatusEqualTo(AlarmStatus.ANA.getCode());

        example.or(criteria2);

        return eamAlarmRecordHistoryService.selectFirstByExample(example);
    }

    public void createAlarmRecord(EamSensorData sensorData, EamAlarm alarm) {
        EamAlarmRecord record = new EamAlarmRecord();
        record.setAlarmId(alarm.getAlarmId());
        record.setAlarmValue(sensorData.getStringValue());
        record.setAlarmStatus(AlarmStatus.ANU.getCode());
        record.setEquipmentId(sensorData.getEquipmentId());
        record.setEquipmentModelPropertyId(alarm.getEquipmentModelPropertyId());
        record.setCreateTime(new Date());
        record.setDeleteFlag(Boolean.FALSE);

        eamAlarmRecordService.insertSelective(record);


    }

    private void createAlarmRecordHistory(EamSensorData sensorData, EamAlarm alarm, AlarmStatus alarmStatus) {
        //Create a alarm history
        EamAlarmRecordHistory recordHistory = new EamAlarmRecordHistory();
        recordHistory.setAlarmId(alarm.getAlarmId());
        recordHistory.setAlarmValue(sensorData.getStringValue());
        recordHistory.setAlarmStatus(alarmStatus.getCode());
        recordHistory.setEquipmentId(sensorData.getEquipmentId());
        recordHistory.setEquipmentModelPropertyId(alarm.getEquipmentModelPropertyId());
        recordHistory.setCreateTime(new Date());
        recordHistory.setDeleteFlag(Boolean.FALSE);

        eamAlarmRecordHistoryService.insertSelective(recordHistory);
    }

    private void updateAlarmRecord(EamAlarmRecord alarmRecord, EamSensorData sensorData){
        alarmRecord.setAlarmStatus(AlarmStatus.CNU.getCode());
        alarmRecord.setAlarmValue(sensorData.getStringValue());
        alarmRecord.setUpdateTime(new Date());

        eamAlarmRecordService.updateByPrimaryKeySelective(alarmRecord);


    }

    public void sendAlarmMessage(EamSensorData sensorData, EamAlarm alarm, boolean isClearMessage) {
        if (AlarmTarget.SMS.match(alarm.getAlarmTarget())) {
            handleSms(sensorData, alarm, isClearMessage);
        } else if (AlarmTarget.EMAIL.match(alarm.getAlarmTarget())) {
            handleEmail(sensorData, alarm, isClearMessage);
        }
        //send APP notification
        handleJpush(sensorData, alarm, isClearMessage);
    }



    private void handleEmail(EamSensorData sensorData, EamAlarm alarm, boolean isClearMessage) {
        String message = buildEmailMessage(sensorData, alarm, isClearMessage);

        List<String> emails = getEmails(alarm);
        for (String email : emails) {
            try {
                _log.info("Send Email To : [ {} ], Message: [ {} ]", email, message);
                emailService.sendSimpleMail(message, email, isClearMessage);
            } catch (MessagingException e) {
                _log.error("Send Email Error:" + e.getMessage());
            }
        }
    }

    private void handleSms(EamSensorData sensorData, EamAlarm alarm, boolean isClearMessage) {
        String mobiles = getJsonMobiles(alarm);
        String message = buildSmsMessage(sensorData, alarm, isClearMessage);
        _log.info("Send SMS To : [ {} ], Message: [ {} ]", mobiles, message);
        if (!StringUtils.isEmpty(mobiles) && !StringUtils.isEmpty(message) ){

            String templateId = isClearMessage ? CLEAR_TEMPLATE_ID : TEMPLATE_ID;
            try{
                SMSUtil.sendTemplate(templateId, mobiles, message);
            }catch (Exception e){
                _log.error("Send SMS Error: Send SMS To : [ {} ], Message: [ {} ]", mobiles, message);
            }

        }
    }

    private void handleJpush(EamSensorData sensorData, EamAlarm alarm, boolean isClearMessage) {
        List<String> mobiles = getMobiles(alarm);
        String message = buildJpushMessage(sensorData, alarm, isClearMessage);
        _log.info("Send APP To : [ {} ], Message: [ {} ]", mobiles, message);
        if (!mobiles.isEmpty() && !StringUtils.isEmpty(message) ){
            jpushUtil.sendPush(mobiles, message);
        }
    }

    private List<String> getEmails(EamAlarm alarm) {
        List<String> result = new ArrayList<>();
        List<UpmsUser> users = getUpmsUsers(alarm);
        for (UpmsUser user : users) {
            if (StringUtils.isNotEmpty(user.getEmail())) {
                result.add(user.getEmail());
            }
        }

        return result;
    }

    private String getJsonMobiles(EamAlarm alarm) {
        List<UpmsUser> users = getUpmsUsers(alarm);

        JSONArray phones = new JSONArray();
        for (UpmsUser user : users) {
            if (!StringUtils.isEmpty(user.getPhone())) {
                phones.add(user.getPhone());
            }
        }

        return phones.toString();
    }

    private List<String> getMobiles(EamAlarm alarm) {
        List<String> result = new ArrayList<>();
        List<UpmsUser> users = getUpmsUsers(alarm);

        for (UpmsUser user : users) {
            if (!StringUtils.isEmpty(user.getPhone())) {
                result.add(user.getPhone());
            }
        }

        return result;
    }

    private List<UpmsUser> getUpmsUsers(EamAlarm alarm) {
        EamAlarmTargetUserExample example = new EamAlarmTargetUserExample();
        example.createCriteria().andAlarmIdEqualTo(alarm.getAlarmId()).andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamAlarmTargetUser> targetUsers = eamAlarmTargetUserService.selectByExample(example);

        List<Integer> userIds = targetUsers.stream().map(EamAlarmTargetUser::getUserId).collect(Collectors.toList());

        UpmsUserExample userExample = new UpmsUserExample();
        userExample.createCriteria().andUserIdIn(userIds);

        return upmsUserService.selectByExample(userExample);
    }

    protected BigDecimal covertToBigDecimal(String data) {
        BigDecimal result = null;
        try {
            result = NumberUtils.createBigDecimal(data);
        } catch (Exception e) {
            _log.error(data + " Can't Covert To BigDecimal");
        }
        return result;
    }

    protected Boolean covertToBoolean(String data) {
        Boolean result = null;
        if ("0".equals(data)) {
            result = Boolean.FALSE;
        } else if ("1".equals(data)) {
            result = Boolean.TRUE;
        } else {
            result = BooleanUtils.toBooleanObject(data);
        }

        return result;
    }

    private Date getStartDate(EamSensorData sensorData, EamAlarm alarm) {
        Date endDate = sensorData.getCreateTime();
        LocalDateTime ldt = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime newLdt = ldt.plusMinutes(-alarm.getDuration().longValue());

        ZonedDateTime zdt = newLdt.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    protected List<EamSensorDataHistory> getSensorDataHistories(EamSensorData sensorData, EamAlarm alarm) {
        Date endDate = sensorData.getCreateTime();
        Date startDate = getStartDate(sensorData, alarm);

        EamSensorDataHistoryExample example = new EamSensorDataHistoryExample();
        example.createCriteria().andSensorIdEqualTo(sensorData.getSensorId())
                .andEquipmentIdEqualTo(sensorData.getEquipmentId())
                .andCreateTimeBetween(startDate, endDate)
                .andDeleteFlagEqualTo(Boolean.FALSE);

        example.setOrderByClause("create_time desc");

        return eamSensorDataHistoryService.selectByExample(example);
    }

    protected EamEquipment getEquipment(EamSensorData sensorData) {
        return eamEquipmentService.selectByPrimaryKey(sensorData.getEquipmentId());
    }

    protected EamEquipmentModelProperties getEamEquipmentModelProperties(EamAlarm alarm) {
        return eamEquipmentModelPropertiesService.selectByPrimaryKey(alarm.getEquipmentModelPropertyId());
    }

    protected String buildEmailMessage(EamSensorData sensorData, EamAlarm alarm, boolean isClearMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        EamEquipment equipment = getEquipment(sensorData);
        EamEquipmentModelProperties eamEquipmentModelProperties = getEamEquipmentModelProperties(alarm);
        stringBuilder.append("报警内容：");
        stringBuilder.append(equipment.getName());
        stringBuilder.append("(");
        stringBuilder.append(equipment.getNumber());
        stringBuilder.append(")  ");
        stringBuilder.append(eamEquipmentModelProperties.getName() + "  ");
        stringBuilder.append(buildAlarmMessage(sensorData, alarm) + "<br/>");

        String content = isClearMessage ? "当前值: " : "报警值：";
        stringBuilder.append(content);
        stringBuilder.append(sensorData.getStringValue() + "<br/>");

        content = isClearMessage ? "清除时间: " : "报警时间：";
        stringBuilder.append(content);

        stringBuilder.append(getCurrentTimeStamp());

        return stringBuilder.toString();
    }

    protected String buildSmsMessage(EamSensorData sensorData, EamAlarm alarm, boolean isClearMessage) {

        EamEquipment equipment = getEquipment(sensorData);
        EamEquipmentModelProperties eamEquipmentModelProperties = getEamEquipmentModelProperties(alarm);

        JSONArray msg = new JSONArray();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(equipment.getName());
        stringBuilder.append("(");
        stringBuilder.append(equipment.getNumber());
        stringBuilder.append(")  ");
        msg.add(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        stringBuilder.append(eamEquipmentModelProperties.getName() + "  ");
        stringBuilder.append(buildAlarmMessage(sensorData, alarm));
        msg.add(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        String content = isClearMessage ? "当前值: " : "报警值：";
        stringBuilder.append(content);
        stringBuilder.append(sensorData.getStringValue());
        msg.add(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        stringBuilder.append(getCurrentTimeStamp());
        msg.add(stringBuilder.toString());

        return msg.toString();
    }

    protected String buildJpushMessage(EamSensorData sensorData, EamAlarm alarm, boolean isClearMessage) {

        EamEquipment equipment = getEquipment(sensorData);
        EamEquipmentModelProperties eamEquipmentModelProperties = getEamEquipmentModelProperties(alarm);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("报警设备：");
        stringBuilder.append(equipment.getName());
        stringBuilder.append("(");
        stringBuilder.append(equipment.getNumber());
        stringBuilder.append(")  ");

        stringBuilder.append("\n触发条件：");
        stringBuilder.append(eamEquipmentModelProperties.getName() + "  ");
        stringBuilder.append(buildAlarmMessage(sensorData, alarm));

        String content = isClearMessage ? "\n当前值: " : "\n报警值：";
        stringBuilder.append(content);
        stringBuilder.append(sensorData.getStringValue());

        content = isClearMessage ? "\n报警清除时间：" : "\n报警时间：";
        stringBuilder.append(content);
        stringBuilder.append(getCurrentTimeStamp());

        return stringBuilder.toString();
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    protected abstract String buildAlarmMessage(EamSensorData sensorData, EamAlarm alarm);

    protected abstract boolean metAlarmCondition(EamSensorData sensorData, EamAlarm alarm);
}
