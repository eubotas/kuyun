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

function deleteRows(rows,idName,delUrl, tipContent, successTip, tableObj) {
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
///////////////////////
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

function applyTemplate(jQuery, templateID, prefix, data, options, targetEl) {
    prefix = ifNull(prefix, '');
    jQuery.template((prefix+templateID), loadHtmlTemplate(jQuery, prefix, jQuery(templateID)));
    return jQuery.tmpl((prefix+templateID), data, options).appendTo(targetEl);
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
    return new Date(value).toLocaleString();
}