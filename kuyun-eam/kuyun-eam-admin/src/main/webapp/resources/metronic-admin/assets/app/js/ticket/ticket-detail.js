$(function() {
    $('span[id^="createTime"]').each(function() {
        $(this).text(timeDetailFormatter($(this).text()));
    });
    $('span[id^="status"]').each(function() {
        $(this).html(formatStatus($(this).text(), null, null));
    });



    $('[id^="imagePath_"]').each(function() {
        $(this).attr("src", getImagePath($(this).attr('src')));
    });


});

$('#orderTakerId').select2({minimumResultsForSearch: -1});


function toaction(type) {
    if('TOAPPOINT'==type){

        ajaxGet(basePath+'/manage/ticket/' + ticketId + '/appoint/create', function (responseData) {
            if (responseData) {
                var data = responseData.data;
                addOptionToHtmlSelect(null, "orderTakerId", data.users);
                $("#createAppointDialog").modal("show");
            }
        });
    }else if('TOASSESSMENT'==type){

        ajaxGet(basePath+'/manage/ticket/' + ticketId + '/assessment/create', function (responseData) {
            if (responseData) {
                var data = responseData.data;
                buildTicketTags(data.ticketTags);
                $("#createAssessmentDialog").modal("show");
                $('#ass_assessmentLevel').select2({minimumResultsForSearch: -1});

            }
        });
    }
    else if('TORECORD'==type){
        $("#createRecordDialog").modal("show");
    }else if('REJECT'==type){
        $("#rejectTicketDialog").modal("show");
    }
    else if('COMPLETE'==type){
        ajaxGet(basePath+'/manage/ticket/complete/' + ticketId, function (responseData) {
            if (responseData) {
                var data = responseData;
                toastr.success("工单成功完成");
                location.reload();
            }else
                toastr.success("工单完成失败");
        });
    }
    else if('CREATEUSER'==type){
        $("#createUserDialog").modal("show");
    }
}

function buildTicketTags(ticketTags) {
    $("#ticketTagDiv").empty();
    html = '<div class="m-checkbox-inline">';
    $.each(ticketTags,
        function(i, tag) {
            html += '<label class="m-checkbox">';
            html += '<input type="checkbox" name="ticketTag" value='+tag.VALUEFIELD +'>';
            html += tag.DESCFIELD;
            html += '<span></span>';
            html += '</label>';
        }
    );
    $("#ticketTagDiv").append(html);

}
function createAppointSubmit(formId){
    var targetUrl= basePath+'/manage/ticket/'+ticketId+'/appoint/create';
    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("委派工单成功");
            $('#createAppointDialog').modal('toggle');
            location.reload();
        }
    });
}

function createAssessmentSubmit(formId){
    var targetUrl= basePath+'/manage/ticket/'+ticketId+'/assessment/create';
    var id= $("#ass_id").val();
    if(id != '')
        targetUrl= basePath+'/manage/ticket/'+ticketId+'/assessment/update/'+id;
    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("评价工单成功");
            $('#createAssessmentDialog').modal('toggle');
            location.reload();
        }
    });
}

function rejectTicketSubmit(formId){
    var targetUrl= basePath+'/manage/ticket/'+ticketId+'/appoint/reject';
    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("工单拒绝成功");
            $('#rejectTicketDialog').modal('toggle');
            location.reload();
        }
    });
}

function createRecordSubmit(formId){
    var targetUrl= basePath+'/manage/ticket/'+ticketId+'/record/create';
    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("工单记录创建成功");
            $('#createRecordDialog').modal('toggle');
            location.reload();
        }
    });
}

function createUserSubmit(formId){
    var targetUrl= basePath+'/manage/company/createUser';
    ajaxPost(targetUrl, formId, function(result) {
        if (result.code != 1) {
            sendErrorInfo(result);
        } else {
            toastr.success("用户创建成功");
            $('#createUserDialog').modal('toggle');
        }
    });
}