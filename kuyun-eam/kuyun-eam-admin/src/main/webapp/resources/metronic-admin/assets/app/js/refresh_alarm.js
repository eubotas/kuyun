
function refreshAlarm(){
    $.ajax({
        type: 'get',
        url: eamPath + '/manage/alarm/jsLongPollingList',
        success: function (responseData) {
            if (responseData) {
                var total = responseData.total;
                if (total > 0 && hasNotificationInfo == false) {
                    $("#redDot").addClass("m-badge m-badge--dot m-badge--dot-small m-badge--danger");
                    hasNotificationInfo = true;
                }
                $('#alarmTitle').html('<span id="topAlarmNum" class="m-dropdown__header-title">' + total +
                    '条</span><span class="m-dropdown__header-subtitle">报警信息</span>');

                var data = responseData.rows;
                var html = "";
                $.each(data, function (i, val) {
                    html = html + generateAlarmHtml(val);
                });
                $('#alarmList').html(html);
            }

            refreshAlarm();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            refreshAlarm();
        }
    });
}

function generateAlarmHtml(row) {
    var rowHtml=
        '<div class="m-list-timeline__item">'+
        '<span class="m-list-timeline__badge"></span>'+
        '<span href="'+alarmAddr(row)+'" class="m-list-timeline__text">'+
        row.messageTitle +
        '</span>'+ alarmStatus(row.alarmStatus)+
        '<span class="m-list-timeline__time">'+
        alarmTime(row.updateTime)+
        '</span></div>';

    return rowHtml;
}

function alarmStatus(status){
    if('ANU'== status)
        return '<span class="m-badge m-badge--danger m-badge--wide" title="需处理"> U</span>';
    else if('ANA'== status)
        return '<span class="m-badge m-badge--info m-badge--wide" title="不需处理"> P</span>';
    else if('CNU'== status || 'CNA'== status)
        return '<span class="m-badge m-badge--success m-badge--wide" title="处理结束">C</span>';
    else
        return "";
}

function alarmAddr(row){
    if(row.alarmType == 'E')
        return "";
    else
        return "";
}

function alarmTime(time){
    if(!time)
        return "";
    var diff = ((new Date()).getTime() - time)/1000;

    if(diff/60 > 30*24*60) {
        return '1月前';
    }
    else if(diff/60 > 24*60) {
        var day= Math.floor(diff / (24*3600));
        return day+'天前';
    } else if(diff/60 > 60){
        var hour= Math.floor(diff / 3600);
        return hour+'小时';
    }
    else {
        var min= Math.floor(diff / 60);
        if(min == 0)
            return "刚才";
        else
            return  min+ '分钟';
    }
}


function ajaxGet(targetUrl, callSuccess, callError)
{
    $.ajax({
        type: 'get',
        url: targetUrl,
        success: function (result) {
            callSuccess(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(callError)
                callError(XMLHttpRequest, textStatus);
            else
                alert(textStatus);
        }
    });
}

function isInSet(value, values) {
    if((arguments.length > 2) || !isArray(values)) // in case client did not wrap values in array
        values = Array.prototype.slice.call(arguments, 1, arguments.length);
    for(var i=0; i<values.length; i++)
        if(value == values[i])
            return true;
    return false;
}
