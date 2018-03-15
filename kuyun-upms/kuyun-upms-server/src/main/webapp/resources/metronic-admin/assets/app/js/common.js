
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