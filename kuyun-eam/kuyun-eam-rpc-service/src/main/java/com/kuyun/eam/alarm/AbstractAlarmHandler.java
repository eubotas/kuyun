package com.kuyun.eam.alarm;

import com.kuyun.common.jpush.JpushUtil;
import com.kuyun.common.mail.service.EmailService;
import com.kuyun.common.netease.SMSUtil;
import com.kuyun.common.util.EhCacheUtil;
import com.kuyun.eam.common.constant.*;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.util.TicketUtil;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.model.UpmsUserCompanyExample;
import com.kuyun.upms.dao.model.UpmsUserExample;
import com.kuyun.upms.rpc.api.UpmsUserCompanyService;
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
    private  EamGrmVariableDataHistoryService grmVariableDataHistoryService;

    @Autowired
    private EamEquipmentService eamEquipmentService;

    @Autowired
    private EamProductLineService eamProductLineService;


    @Autowired
    private JpushUtil jpushUtil;

    @Autowired
    private EamDataElementService eamDataElementService;

    @Autowired
    private EamTicketService eamTicketService;
    @Autowired
    private EamProductLineCompanyService eamProductLineCompanyService;

    @Autowired
    private UpmsUserCompanyService upmsUserCompanyService;

    public void process(EamGrmVariableData variableData, EamAlarm alarm) {
        EamAlarmRecord alarmRecord = getAlarmRecord(variableData, alarm);

        if (metAlarmCondition(variableData, alarm) && alarmRecord == null) {
            //1. create alarm record
            createAlarmRecord(variableData, alarm);
            //2. create alarm record history
            createAlarmRecordHistory(variableData, alarm, AlarmStatus.ANU);
            //3. send message
            sendAlarmMessage(variableData, alarm, false);
            //4. create ticket
            createTicket(variableData, alarm);
        }

        if (!metAlarmCondition(variableData, alarm) && alarmRecord != null){
            //1. update alarm record
            updateAlarmRecord(alarmRecord, variableData);
            //2. create alarm record history
            updateAlarmRecordHistory(variableData, alarm, AlarmStatus.CNU);
            //3. send clear message
            sendAlarmMessage(variableData, alarm, true);
        }
    }

    protected void createTicket(EamGrmVariableData variableData, EamAlarm alarm){
        if (alarm.getIsCreateTicket() != null && alarm.getIsCreateTicket().booleanValue()){
            EamEquipment equipment = eamEquipmentService.selectByPrimaryKey(variableData.getEquipmentId());
            List<UpmsUser> users = getUpmsUsers(alarm);
            List<EamTicket> tickets = new ArrayList<>(users != null ? users.size() : 0);
            for (UpmsUser user : users){
                EamTicket ticket = new EamTicket();
                ticket.setProductLineId(variableData.getProductLineId());
                ticket.setEquipmentId(variableData.getEquipmentId());
                ticket.setEquipmentCategoryId(equipment.getEquipmentCategoryId());
                String message = buildSmsMessage(variableData, alarm, false);
                ticket.setDescription(message);
                ticket.setPriority(TicketPriority.URGENT.getName());
                ticket.setStatus(TicketStatus.TO_PROCESS.getName());
                ticket.setTicketTypeId(TicketType.ALARM.getCode());
                ticket.setTicketNumber(TicketUtil.generatorTicketNumber());
                ticket.setExecutorId(user.getUserId());
                ticket.setCompanyId(getCompanyId(user.getUserId()));
                ticket.setCreateTime(new Date());
                ticket.setUpdateTime(new Date());
                ticket.setDeleteFlag(Boolean.FALSE);
                tickets.add(ticket);
            }
            eamTicketService.batchInsert(tickets);
        }

    }

    private Integer getCompanyId(Integer userId) {
        Integer result = null;
        UpmsUserCompanyExample example = new UpmsUserCompanyExample();
        example.createCriteria().andUserIdEqualTo(userId);

        UpmsUserCompany userCompany = upmsUserCompanyService.selectFirstByExample(example);
        if (userCompany != null){
            result = userCompany.getCompanyId();
        }
        return result;
    }


    private void updateAlarmRecordHistory(EamGrmVariableData variableData, EamAlarm alarm, AlarmStatus alarmStatus) {
        EamAlarmRecordHistory alarmRecordHistory = getAlarmRecordHistory(variableData, alarm);
        if (alarmRecordHistory != null){
            alarmRecordHistory.setAlarmStatus(alarmStatus.getCode());
            alarmRecordHistory.setAlarmClearValue(variableData.getValue());
            alarmRecordHistory.setUpdateTime(new Date());

            eamAlarmRecordHistoryService.updateByPrimaryKeySelective(alarmRecordHistory);
        }

    }

    private EamAlarmRecord getAlarmRecord(EamGrmVariableData variableData, EamAlarm alarm){
        EamAlarmRecordExample example = new EamAlarmRecordExample();
        EamAlarmRecordExample.Criteria criteria1 = example.createCriteria();
        criteria1.andAlarmIdEqualTo(alarm.getAlarmId())
                .andProductLineIdEqualTo(variableData.getProductLineId())
                .andAlarmStatusEqualTo(AlarmStatus.ANU.getCode());

        if (variableData.getEquipmentId() != null){
            criteria1.andEquipmentIdEqualTo(variableData.getEquipmentId());
        }

        EamAlarmRecordExample.Criteria criteria2 = example.createCriteria();
        criteria2.andAlarmIdEqualTo(alarm.getAlarmId())
                .andProductLineIdEqualTo(variableData.getProductLineId())
                .andAlarmStatusEqualTo(AlarmStatus.ANA.getCode());

        if (variableData.getEquipmentId() != null){
            criteria2.andEquipmentIdEqualTo(variableData.getEquipmentId());
        }

        example.or(criteria2);

        return eamAlarmRecordService.selectFirstByExample(example);
    }

    private EamAlarmRecordHistory getAlarmRecordHistory(EamGrmVariableData variableData, EamAlarm alarm){
        EamAlarmRecordHistoryExample example = new EamAlarmRecordHistoryExample();
        EamAlarmRecordHistoryExample.Criteria criteria1 = example.createCriteria();
        criteria1.andAlarmIdEqualTo(alarm.getAlarmId())
                .andProductLineIdEqualTo(variableData.getProductLineId())
                .andAlarmStatusEqualTo(AlarmStatus.ANU.getCode());

        if (variableData.getEquipmentId() != null){
            criteria1.andEquipmentIdEqualTo(variableData.getEquipmentId());
        }

        EamAlarmRecordHistoryExample.Criteria criteria2 = example.createCriteria();
        criteria2.andAlarmIdEqualTo(alarm.getAlarmId())
                .andProductLineIdEqualTo(variableData.getProductLineId())
                .andAlarmStatusEqualTo(AlarmStatus.ANA.getCode());

        if (variableData.getEquipmentId() != null){
            criteria2.andEquipmentIdEqualTo(variableData.getEquipmentId());
        }

        example.or(criteria2);

        return eamAlarmRecordHistoryService.selectFirstByExample(example);
    }

    public void createAlarmRecord(EamGrmVariableData variableData, EamAlarm alarm) {
        EamAlarmRecord record = new EamAlarmRecord();
        record.setAlarmId(alarm.getAlarmId());
        record.setAlarmValue(variableData.getValue());
        record.setAlarmStatus(AlarmStatus.ANU.getCode());
        record.setEquipmentId(variableData.getEquipmentId());
        record.setProductLineId(variableData.getProductLineId());
        record.setCreateTime(new Date());
        record.setDeleteFlag(Boolean.FALSE);

        eamAlarmRecordService.insertSelective(record);


    }

    private void createAlarmRecordHistory(EamGrmVariableData variableData, EamAlarm alarm, AlarmStatus alarmStatus) {
        //Create a alarm history
        EamAlarmRecordHistory recordHistory = new EamAlarmRecordHistory();
        recordHistory.setAlarmId(alarm.getAlarmId());
        recordHistory.setAlarmValue(variableData.getValue());
        recordHistory.setAlarmStatus(alarmStatus.getCode());
        recordHistory.setEquipmentId(variableData.getEquipmentId());
        recordHistory.setProductLineId(variableData.getProductLineId());
        recordHistory.setCreateTime(new Date());
        recordHistory.setDeleteFlag(Boolean.FALSE);

        eamAlarmRecordHistoryService.insertSelective(recordHistory);
    }

    private void updateAlarmRecord(EamAlarmRecord alarmRecord, EamGrmVariableData variableData){
        alarmRecord.setAlarmStatus(AlarmStatus.CNU.getCode());
        alarmRecord.setAlarmValue(variableData.getValue());
        alarmRecord.setUpdateTime(new Date());

        eamAlarmRecordService.updateByPrimaryKeySelective(alarmRecord);


    }

    public void sendAlarmMessage(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {
        if (AlarmTarget.SMS.match(alarm.getAlarmTarget())) {
            handleSms(variableData, alarm, isClearMessage);
        } else if (AlarmTarget.EMAIL.match(alarm.getAlarmTarget())) {
            handleEmail(variableData, alarm, isClearMessage);
        }
        //send APP notification
        handleJpush(variableData, alarm, isClearMessage);
    }



    private void handleEmail(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {
        String message = buildEmailMessage(variableData, alarm, isClearMessage);

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

    private void handleSms(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {
        String mobiles = getJsonMobiles(alarm);
        String message = buildSmsMessage(variableData, alarm, isClearMessage);
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

    private void handleJpush(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {
        List<String> mobiles = getMobiles(alarm);
        String message = buildJpushMessage(variableData, alarm, isClearMessage);
        _log.info("Send APP To : [ {} ], Message: [ {} ]", mobiles, message);
        if (!mobiles.isEmpty() && !StringUtils.isEmpty(message) ){
            jpushUtil.sendPush(mobiles, message, OrgDepartment.ALARM_DEPARTMENT.getCode());
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
        if (users != null){
            for (UpmsUser user : users) {
                if (!StringUtils.isEmpty(user.getPhone())) {
                    result.add(user.getPhone());
                }
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

    private Date getStartDate(EamGrmVariableData variableData, EamAlarm alarm) {
        Date endDate = variableData.getCreateTime();
        LocalDateTime ldt = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime newLdt = ldt.plusMinutes(-alarm.getDuration().longValue());

        ZonedDateTime zdt = newLdt.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    protected List<EamGrmVariableDataHistory> getGrmVariableDataHistories(EamGrmVariableData variable, EamAlarm alarm) {
        Date endDate = variable.getCreateTime();
        Date startDate = getStartDate(variable, alarm);

        EamGrmVariableDataHistoryExample example = new EamGrmVariableDataHistoryExample();
        EamGrmVariableDataHistoryExample.Criteria criteria = example.createCriteria();

        criteria.andProductLineIdEqualTo(variable.getProductLineId());
        criteria.andDataElementIdEqualTo(variable.getDataElementId());
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);

        if (variable.getEquipmentId() == null){
            criteria.andEquipmentIdIsNull();
        }else {
            criteria.andEquipmentIdEqualTo(variable.getEquipmentId());
        }

        criteria.andUpdateTimeBetween(startDate, endDate);
        example.setOrderByClause("update_time desc");

        return grmVariableDataHistoryService.selectByExample(example);
    }

    protected EamEquipment getEquipment(EamGrmVariableData variableData) {
        EamEquipment result = null;
        if (variableData.getEquipmentId() != null){
            result = eamEquipmentService.selectByPrimaryKey(variableData.getEquipmentId());
        }
        return result;
    }

    protected EamProductLine getProductLine(EamGrmVariableData variableData) {
        EamProductLine result = null;
        if (variableData.getEquipmentId() != null){
            result = eamProductLineService.selectByPrimaryKey(variableData.getProductLineId());
        }
        return result;
    }

    protected EamDataElement getEamDataElement(EamGrmVariableData variableData) {
        EamDataElement result = null;
        if (variableData.getDataElementId() != null){
            result = eamDataElementService.selectByPrimaryKey(variableData.getDataElementId());
        }
        return result;
    }

    protected String buildEmailMessage(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {
        EamProductLine productLine = getProductLine(variableData);
        EamEquipment equipment = getEquipment(variableData);
        EamDataElement dataElement = getEamDataElement(variableData);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("报警产线：");
        stringBuilder.append(productLine.getName() + "<br/>");

        if (equipment != null){
            stringBuilder.append("报警设备：");
            stringBuilder.append(equipment.getName());
            stringBuilder.append("(");
            stringBuilder.append(equipment.getNumber());
            stringBuilder.append(")  ");
        }

        stringBuilder.append(dataElement.getLableName() + "  ");
        stringBuilder.append(buildAlarmMessage(variableData, alarm) + "<br/>");

        String content = isClearMessage ? "当前值: " : "报警值：";
        stringBuilder.append(content);
        stringBuilder.append(variableData.getValue() + "<br/>");

        content = isClearMessage ? "清除时间: " : "报警时间：";
        stringBuilder.append(content);

        stringBuilder.append(getCurrentTimeStamp());

        return stringBuilder.toString();
    }

    protected String buildSmsMessage(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {
        return buildJpushMessage(variableData, alarm, isClearMessage);
    }

    protected String buildJpushMessage(EamGrmVariableData variableData, EamAlarm alarm, boolean isClearMessage) {

        EamProductLine productLine = getProductLine(variableData);
        EamEquipment equipment = getEquipment(variableData);
        EamDataElement dataElement = getEamDataElement(variableData);


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("报警产线：");
        stringBuilder.append(productLine.getName() + "\n");

        if (equipment != null){
            stringBuilder.append("报警设备：");
            stringBuilder.append(equipment.getName());
            stringBuilder.append("(");
            stringBuilder.append(equipment.getNumber());
            stringBuilder.append(")  \n");
        }


        stringBuilder.append("触发条件：");
        stringBuilder.append(dataElement.getLableName() + "  ");
        stringBuilder.append(buildAlarmMessage(variableData, alarm));

        String content = isClearMessage ? "\n当前值: " : "\n报警值：";
        stringBuilder.append(content);
        stringBuilder.append(variableData.getValue());

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

    protected abstract String buildAlarmMessage(EamGrmVariableData variableData, EamAlarm alarm);

    protected abstract boolean metAlarmCondition(EamGrmVariableData variableData, EamAlarm alarm);
}
