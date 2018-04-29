///////////////// add edit/delete
toastr.options = {
    "closeButton": false,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-right",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};

var FormWidgets = function () {
    var createForm = function (formid) {
        $("#"+formid+"_Form").validate({
            // define validation rules
            rules: {
                name: {
                    required: true
                }
            },
            submitHandler: function (form) {
                if(formid == 'add')
                    submitForm();
                else{
                    submitForm($('#edit_id').val());
                }

            }
        });
    }

    return {
        // public functions
        init: function (formid) {
            createForm(formid);
        }
    };
}();

function ajaxPost(targetUrl, formId, callSuccess, callValidate, callError)
{
    $.ajax({
        type: 'post',
        url: targetUrl,
        data: $('#'+formId).serialize(),
        beforeSend: function () {
            if(callValidate)
                return callValidate();
        },
        success: function (result) {
            callSuccess(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(callError)
                callError(XMLHttpRequest, textStatus);
            else
                swWarn(textStatus);
        }
    });
}

function ajaxPostData(targetUrl, data, callSuccess, callValidate, callError)
{
    $.ajax({
        type: 'post',
        url: targetUrl,
        data: data,
        beforeSend: function () {
            if(callValidate)
                return callValidate();
        },
        success: function (result) {
            callSuccess(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(callError)
                callError(XMLHttpRequest, textStatus);
            else
                swWarn(textStatus);
        }
    });
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
                toastr.error(textStatus);
        }
    });
}

function ajaxGetWithData(targetUrl, data, callSuccess, callError)
{
    $.ajax({
        type: 'get',
        url: targetUrl,
        data: data,
        success: function (result) {
            callSuccess(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(callError)
                callError(XMLHttpRequest, textStatus);
            else
                toastr.error(textStatus);
        }
    });
}

function ajaxGetDel(targetUrl, successTip, tableObj)
{
    ajaxGet(targetUrl, function(result){
        if (result.code < 1) {
            sendErrorInfo(result);
        } else {
            if(successTip)
                toastr.success(successTip);
            else
                toastr.success("删除成功!");
            if(tableObj)
                tableObj.bootstrapTable('refresh');
            else
                $table.bootstrapTable('refresh');
        }
    });
}

function deleteRows(rows,idName,delUrl, tipContent, successTip, tableObj, callFun) {
    if (rows.length == 0) {
        swWarn("请至少选择一条记录");
    }else {
        swal({
            title: "操作提示",
            text: tipContent,
            showCancelButton: true,
            confirmButtonText: '确认',
            cancelButtonText: '取消'
        }).then(function(result) {
            if (result.value) {
                var ids = new Array();
                for (var i in rows) {
                    ids.push(rows[i][idName]);
                }
                ajaxGet(delUrl + ids.join("-"), function(result){
                    if (result.code < 1) {
                        sendErrorInfo(result);
                    } else {
                        if(successTip)
                            toastr.success(successTip);
                        else
                            toastr.success("删除成功!");

                        if (callFun){
                            callFun();
                        }else{
                            if(tableObj)
                                tableObj.bootstrapTable('refresh');
                            else
                                $table.bootstrapTable('refresh');
                        }
                    }
                });
            }
        });

    }//end else
}

function deleteRowsChar(rows,idName,delUrl,tag, tipContent, successTip, tableObj) {
    if (rows.length == 0) {
        swWarn("请至少选择一条记录");
    }else {
        swal({
            text: tipContent,
            title: "操作提示",
            showCancelButton: true,
            confirmButtonText: '确认',
            cancelButtonText: '取消'
        }).then(function(result) {
            if (result.value) {
                var ids = new Array();
                for (var i in rows) {
                    ids.push(rows[i][idName]);
                }
                var spliteTag="-";
                if(tag)
                    spliteTag=tag;
                ajaxGet(delUrl + ids.join(spliteTag), function(result){
                    if (result.code < 1) {
                        sendErrorInfo(result);
                    } else {
                        if(successTip)
                            toastr.success(successTip);
                        else
                            toastr.success("删除成功!");
                        if(tableObj)
                            tableObj.bootstrapTable('refresh');
                        else
                            $table.bootstrapTable('refresh');
                    }
                });
            }
        });

    }//end else
}

function sendErrorInfo(result)
{
    var errorMsgs = "";
    if (result.data instanceof Array) {
        $.each(result.data, function(index, value) {
            errorMsgs += value.errorMsg + "<br>";
        });
    } else {
        errorMsgs = result.data.errorMsg;
    }
    toastr.warning(errorMsgs);
}

function swWarn(newtips)
{
    swal({
        title: "操作提示",
        text: newtips,
        type: "warning"
    });
}

function swError(newtips)
{
    swal({
        title: "操作提示",
        text: newtips,
        type: "error"
    });
}

function swSuccess(newtips)
{
    swal({
        title: "操作提示",
        text: newtips,
        type: "success"
    });
}

function ifNull(val){
    if(val == null || val =='undefine')
        return "";
    else
        return $.trim(val);
}

function radioBoxcheck(val, idtag){
    if(val != null && val !='')
        $("#edit_"+idtag+"_"+val).attr("checked",true);
}

function addOptionToHtmlSelect(defaultValue, htmlSelectId, data, firstItemVal, firstItemText) {
    var htmlSelectObj = jQuery("#"+htmlSelectId);
    var options = [];
    if(firstItemVal) {
        if(firstItemText)
            options.push(jQuery("<option>", {"value": firstItemVal, "text": firstItemText}));
        else
            options.push(jQuery("<option>", {"value": firstItemVal, "text": "请选择"}));
    }
    for(var i = 0; i < data.length; i++) {
        var selected = false;
        if(defaultValue && defaultValue == data[i].VALUEFIELD) {
            selected = true;
        }
        options.push(jQuery("<option>", {"value": data[i].VALUEFIELD, "text": data[i].DESCFIELD, "selected": selected}));
    }
    htmlSelectObj.empty();
    htmlSelectObj.append(options);
}

function addOptionToHtmlMultiSelect(htmlSelectId, optData, selectedData) {
    var htmlSelectObj = jQuery("#"+htmlSelectId);
    var options = [];
    if(optData) {
        for (var i = 0; i < optData.length; i++) {
            var selected = false;
            if(selectedData) {
                for (var j = 0; j < selectedData.length; j++) {
                    if (selectedData[j].VALUEFIELD == optData[i].VALUEFIELD) {
                        selected = true;
                        break;
                    }
                }
            }
            options.push(jQuery("<option>", {
                "value": optData[i].VALUEFIELD,
                "text": optData[i].DESCFIELD,
                "selected": selected
            }));
        }
    }
    htmlSelectObj.empty();
    htmlSelectObj.append(options);
}
///////////////////////.

function generateAddEditForm(templateID, prefix, data, options, targetEl){
   var pres= prefix.split(',');
   var targets = targetEl.split(',');
   if(pres.length = targets.length){
       pres.forEach(function( val, index ) {
           applyTemplate(jQuery, '#'+templateID, val, data, options, jQuery('#'+targets[index]));
       });
       removeIdHtml(templateID);
   }
}

function removeIdHtml(templateID){
    $("#"+templateID).remove();
}

function applyTemplate(jQuery, templateID, prefix, data, options, targetEl) {
    prefix = ifNull(prefix, '');
    jQuery.template((prefix+templateID), loadHtmlTemplate(jQuery, prefix, jQuery(templateID)));

    return jQuery.tmpl((prefix+templateID), data, options).appendTo(targetEl);
}

function loadHtmlTemplate(jQuery, prefix, el) {
    var html = jQuery(el).html();
    html = strReplaceAll(html, 'templateID_', prefix);

    var titleName='';
    if(prefix=='edit_')
        titleName='编辑';
    else if(prefix=='add_')
        titleName='新建';
    html = strReplaceAll(html, 'templateTitleName_', titleName);
    return html;
}

function ifNull(firstValue) {
    for(var i=0; i<arguments.length; i++) {
        var value = arguments[i];
        if((typeof value != 'undefined') && (value != null))
            return value;
    }
    return firstValue;
}

function strReplaceAll(str, oldValue, newValue) {
    if(str == null)
        return null;
    var idx = 0;
    while((idx = str.indexOf(oldValue, idx)) != -1) {
        str = (str.substring(0, idx) + str.substring(idx).replace(oldValue, newValue));
        idx += newValue.length;
    }
    return str;
}


// 格式化时间
function timeFormatter(value , row, index) {
    return timeSimpleFormatter(value);
    //return new Date(value).toLocaleString();
}

function timeSimpleFormatter(value) {
    var date= new Date(value);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}

function timeDetailFormatter(value) {
    var date= new Date(value);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate() + " "+date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
}

Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}

function changeTimeFormat(timestamp) {
    var newDate = new Date();
    newDate.setTime(timestamp);
    return newDate.format('yyyy/MM/dd');
}

function hideModal(id) {
    jQuery("#"+id).modal('hide');
}

function showModal(id) {
    jQuery("#"+id).modal('show');
}

function showId(id, val) {
    if(id) {
        if(val == true)
            jQuery("#" + id).show();
        else
            jQuery("#" + id).hide();
    }
}

function getUnitName(unit) {
    if(!unit)
        return "";
    else if(unit =='YEAR')
        return "年";
    else if(unit =='MONTH')
        return "月";
    else if(unit =='WEEK')
        return "周";
    else if(unit =='DAY')
        return "天";
}

function formatStatus(value , row, index) {
    var clazz = 'm-badge--info';
    if(value == '评价完成'){
        clazz = 'm-badge--success';
    }else if(value == '待评价'){
        clazz = 'm-badge--metal';
    } else if(value == '待维修'){
        clazz = 'm-badge--brand';
    } else if(value == '待派工'){
        clazz = 'm-badge--brand';
    }else if(value == '维修中'){
        clazz = 'm-badge--warning';
    }

    if(!isNull(value))
        return '<span class="m-badge ' + clazz + ' m-badge--wide">' + ifNull(value) + '</span>';
}

function ifNull(val){
    if(val == null)
        return "";
    else
        return val;
}
function isNull(val){
    if(val == null || val =='')
        return true;
    else
        return null;
}

function selectedItemColor(parentId, id){
    if(!parentId || !id)
        return;
    $("#"+parentId).find("li>a").css("border-left",'solid 8px transparent');
    $("#"+parentId).find("li>span").find("[href]").css("border-left",'solid 8px transparent');
    $("#"+id).find("[href]").css("border-left",'solid 8px #ED5565');
}

$(document).ready(function() {
    if(menuSelectItem) {
        var leftMenu ="";
        if(menuSelectItem.startsWith("submenu"))
            leftMenu = "menu_" + menuSelectItem.substring(8, 14); //middle
        else
            leftMenu = menuSelectItem.substring(7, 18);
        if (!menuSelectItem.startsWith("header_") && menuSelectItem.indexOf("assets") > -1) {
            var leftSubmenu = menuSelectItem.substring(0, menuSelectItem.length - 1);
            $("#" + leftMenu).show();
            $("#" + leftSubmenu).show();
        }else{
            $("#"+leftMenu).find('.m-menu__submenu').show();
        }
    }
});

function isContains(str, substr) {
    return new RegExp(substr).test(str);
}

function setText(id,val){
    $("#"+id).text(val);
}

function goBack() {
    window.history.back();
}
