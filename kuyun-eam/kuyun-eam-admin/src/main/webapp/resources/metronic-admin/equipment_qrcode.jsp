<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<script src="${basePath}/resources/metronic-admin/assets/js/jquery.qrcode.min.js" type="text/javascript"></script>
<script src="${basePath}/resources/metronic-admin/assets/js/html2canvas.min.js" type="text/javascript"></script>
<script>
    var qrcodeHeight =128;
    var qrcodeWidth =128;
    function generateQrcode(id, text){
        $('#'+id).qrcode({
            width: qrcodeWidth,
            height: qrcodeHeight,
            text: text,
            render: "canvas",//设置渲染方式 （有两种方式 table和canvas，默认是canvas）
            typeNumber: -1,      //计算模式
            correctLevel: 0,//纠错等级
            background: "#ffffff",
            foreground: "#000000"
        });
    }

    function convertCanvasToImage(canvas) {
        //新Image对象，可以理解为DOM
        var image = new Image();
        // canvas.toDataURL 返回的是一串Base64编码的URL，当然,浏览器自己肯定支持
        // 指定格式 PNG
        image.src = canvas.toDataURL("image/png");
        return image;
    }

    function qrcodeActionImpl(rows) {
        if (rows.length == 0) {
            swWarn("请至少选择一条记录");
        }else {
            $('#qrcodeList').html(generateTemplate(rows));
            var eid;
            for (var i in rows) {
                eid=$.trim(rows[i].equipmentId);
                generateQrcode('Q'+eid, eid);
            }

            jQuery("#qrcodeContainer").modal("show");
        }//end else
    }

    function generateTemplate(rows){
        var row=Math.ceil(rows.length/2);
        var html=''
        for (var i=0;i<row; i++) {
            html+=generateTemplateRow(rows, i);
        }
        return html;
    }

    function generateTemplateRow(rows, i){
        var total = rows.length;
        var secondId = (2*i+1<total)? $.trim(rows[2*i+1].equipmentId) : '';
        var secondName = secondId==''? '' : rows[2*i+1].name +'('+rows[2*i+1].number+')';
        return '<div class="form-group m-form__group row">'+
            '<div id="Q'+$.trim(rows[2*i].equipmentId)+'" class="col-lg-6">'+
            '</div>'+
            '<div id="Q'+secondId+'" class="col-lg-6">'+
            '</div>'+
            '</div>'+

            '<div class="form-group m-form__group row">'+
            '<label class="col-lg-6 col-form-label" style="text-align:left;">'+ rows[2*i].equipmentId +'</label>'+
            '<label class="col-lg-6 col-form-label" style="text-align:left;">'+secondId+'</label>'+
            '</div>'+

            '<div class="form-group m-form__group row" style="margin-top:-40px;">'+
            '<label class="col-lg-6 col-form-label" style="text-align:left;">'+ rows[2*i].name +'('+rows[2*i].number+')</label>'+
            '<label class="col-lg-6 col-form-label" style="text-align:left;">'+secondName+'</label>'+
            '</div>';
    }
</script>

<div id="qrcodeContainer" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <form id="equipmentForm" class="m-form m-form--fit m-form--label-align-right">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div id="qrcodeList" class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" id="saveQrcode" onclick="saveDiv('qrcodeList')">
                        导出
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>


<script>
    function saveDiv(divId) {
        html2canvas(document.getElementById('qrcodeList')).then(function(canvas) {
            var type = 'png';
            var imgData = canvas.toDataURL(type);
            var _fixType = function(type) {
                type = type.toLowerCase().replace(/jpg/i, 'jpeg');
                var r = type.match(/png|jpeg|bmp|gif/)[0];
                return 'image/' + r;
            };
            imgData = imgData.replace(_fixType(type),'image/octet-stream');
            var saveFile = function(data, filename){
                var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
                save_link.href = data;
                save_link.download = filename;

                var event = document.createEvent('MouseEvents');
                event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
                save_link.dispatchEvent(event);
            };
            var filename =  'kuyunGrcode.' + type;
            saveFile(imgData,filename);
        });
    }

</script>