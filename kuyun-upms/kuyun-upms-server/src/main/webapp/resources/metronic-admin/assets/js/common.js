///////////////// add edit/delete
function post(targetUrl, formId, callValidate, callSuccess, callError)
{
    $.ajax({
        type: 'post',
        url: targetUrl,
        data: $(formId).serialize(),
        beforeSend: function () {
            return callValidate;
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

function get(targetUrl, callSuccess, callError)
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
                swWarn(textStatus);
        }
    });
}

function deleteRow(newtips, callbackDel)
{
    swal({
        title: "操作提示",
        text: newtips,
        type: "warning", showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        cancelButtonText: "取消",
        confirmButtonText: "删除！",
        closeOnConfirm: true
    }, function () {
        callbackDel();
    });
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

function addOptionToHtmlSelect(defaultValue, htmlSelectId, data) {
    var htmlSelectObj = jQuery("#"+htmlSelectId);
    var options = [];
    options.push(jQuery("<option>", {"value": "", "text": "Please Select"}));
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
