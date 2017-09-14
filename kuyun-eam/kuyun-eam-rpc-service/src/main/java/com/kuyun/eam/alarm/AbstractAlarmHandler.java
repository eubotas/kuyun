package com.kuyun.eam.alarm;

import com.kuyun.common.mail.service.EmailService;
import com.kuyun.common.netease.SMSUtil;
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
    @Autowired
    private EamAlarmRecordService eamAlarmRecordService;

    @Autowired
    private EamAlarmTargetUserService eamAlarmTargetUserService;

    @Autowired
    private UpmsUserService upmsUserService;

    @Autowired
    private EmailService emailService;

    @Autowired
    protected EamSensorDataService eamSensorDataService;

    @Autowired
    private EamEquipmentService eamEquipmentService;

    @Autowired
    private EamEquipmentModelPropertiesService eamEquipmentModelPropertiesService;

    public void process(EamSensorData sensorData, EamAlarm alarm) {
        if (metAlarmCondition(sensorData, alarm)) {
            //1. create alarm record
            createAlarmRecord(sensorData, alarm);
            //2. send message
            sendAlarmMessage(sensorData, alarm);
        }
    }

    public void createAlarmRecord(EamSensorData sensorData, EamAlarm alarm) {
        EamAlarmRecord record = new EamAlarmRecord();
        record.setAlarmId(alarm.getAlarmId());
        record.setSensorDataId(sensorData.getSensorDataId());
        record.setEquipmentId(sensorData.getEquipmentId());
        record.setEquipmentModelPropertyId(alarm.getEquipmentModelPropertyId());
        record.setCreateTime(new Date());
        record.setDeleteFlag(Boolean.FALSE);

        eamAlarmRecordService.insertSelective(record);
    }

    public void sendAlarmMessage(EamSensorData sensorData, EamAlarm alarm) {
        if (AlarmTarget.SMS.match(alarm.getAlarmTarget())) {
            handleSms(sensorData, alarm);
        } else if (AlarmTarget.EMAIL.match(alarm.getAlarmTarget())) {
            handleEmail(sensorData, alarm);
        }
    }

    private void handleEmail(EamSensorData sensorData, EamAlarm alarm) {
        String message = buildEmailMessage(sensorData, alarm);

        List<String> emails = getEmails(alarm);
        for (String email : emails) {
            try {
                emailService.sendSimpleMail(message, email);
            } catch (MessagingException e) {
                _log.error("Send Email Error:" + e.getMessage());
            }
        }
    }

    private void handleSms(EamSensorData sensorData, EamAlarm alarm) {
        String mobiles = getMobiles(alarm);
        String message = buildSmsMessage(sensorData, alarm);
        SMSUtil.sendTemplate(TEMPLATE_ID, mobiles, message);
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

    private String getMobiles(EamAlarm alarm) {
        List<UpmsUser> users = getUpmsUsers(alarm);

        JSONArray phones = new JSONArray();
        for (UpmsUser user : users) {
            if (!StringUtils.isEmpty(user.getPhone())) {
                phones.add(user.getPhone());
            }
        }

        return phones.toString();
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

    private Date getStartDate(EamAlarm alarm) {
        Date endDate = alarm.getCreateTime();
        LocalDateTime ldt = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime newLdt = ldt.plusMinutes(-alarm.getDuration().longValue());

        ZonedDateTime zdt = newLdt.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    protected List<EamSensorData> getSensorDates(EamSensorData sensorData, EamAlarm alarm) {
        Date endDate = alarm.getCreateTime();
        Date startDate = getStartDate(alarm);

        EamSensorDataExample example = new EamSensorDataExample();
        example.createCriteria().andSensorIdEqualTo(sensorData.getSensorId())
                .andEquipmentIdEqualTo(sensorData.getEquipmentId())
                .andCreateTimeBetween(startDate, endDate)
                .andDeleteFlagEqualTo(Boolean.FALSE);

        example.setOrderByClause("create_time desc");

        return eamSensorDataService.selectByExample(example);
    }

    protected EamEquipment getEquipment(EamSensorData sensorData) {
        return eamEquipmentService.selectByPrimaryKey(sensorData.getEquipmentId());
    }

    protected EamEquipmentModelProperties getEamEquipmentModelProperties(EamAlarm alarm) {
        return eamEquipmentModelPropertiesService.selectByPrimaryKey(alarm.getEquipmentModelPropertyId());
    }

    protected String buildEmailMessage(EamSensorData sensorData, EamAlarm alarm) {
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

        stringBuilder.append("报警值：");
        stringBuilder.append(sensorData.getStringValue() + "<br/>");

        stringBuilder.append("报警时间：");

        stringBuilder.append(getCurrentTimeStamp());

        return stringBuilder.toString();
    }

    protected String buildSmsMessage(EamSensorData sensorData, EamAlarm alarm) {

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
        stringBuilder.append("报警值：");
        stringBuilder.append(sensorData.getStringValue());
        msg.add(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        stringBuilder.append(getCurrentTimeStamp());
        msg.add(stringBuilder.toString());

        return msg.toString();
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
