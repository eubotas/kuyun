<%@ page contentType="text/html; charset=utf-8" %>

<div class="m-portlet m-portlet--full-height  ">
    <div class="m-portlet__head">
        <div class="m-portlet__head-caption">
            <div class="m-portlet__head-title">
                <h2 class="m-portlet__head-text">
                    各地设备列表
                </h2>
            </div>
        </div>
    </div>

    <div id="ticketList" class="m-portlet__body">
        <ul id="treeCity" class="ztree" style="width:260px; overflow:auto;"></ul>
    </div>
</div>

<link href="${basePath}/resources/kuyun-admin/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet"/>
<script src="${basePath}/resources/kuyun-admin/plugins/zTree_v3/js/jquery.ztree.all.min.js"></script>

<script type="text/javascript">
    var zTree;
    var setting = {
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            beforeClick: function(treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeCity");
                if (treeNode.isParent) {
                    zTree.expandNode(treeNode);
                    return false;
                } else {
                    callbackClickItem(treeNode);
                    return true;
                }
            }
        }
    };

    var equipmentId, longitude, latitude, isOnline;
    var equipmentLocations =[];
    $(document).ready(function(){
        ajaxGet('${basePath}/manage/equipment/city/tree', function (responseData) {
            if (responseData) {
                var data = responseData.data.provices;
                var jsonstr = "[]",  jsonTemp, pid=0, online, latitude,longitude, tmpId;
                var jsonarray = eval('('+jsonstr+')');
                $.each(data, function (n, value) {  //省
                    tmpId=parseInt(value.code);
                    if(n==0)
                        jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online, "open":true};
                    else
                        jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online};
                    jsonarray.push(jsonTemp);
                    pid=tmpId;
                    $.each(value.children, function (n, value) { //市
                        tmpId=parseInt(value.code);
                        if(n==0)
                            jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online,"latitude":value.latitude,"longitude":value.longitude, "open":true};
                        else
                            jsonTemp ={"id":tmpId, "name":value.name,"pId":pid,"online":value.online,"latitude":value.latitude,"longitude":value.longitude};
                        jsonarray.push(jsonTemp);
                        pid=tmpId;
                        online = value.online;
                        latitude=value.latitude;
                        longitude = value.longitude;
                        $.each(value.children, function (n, value) { //data
                            jsonTemp ={"id":value.id, "name":value.name,"pId":pid,"online":online,"latitude":latitude,"longitude":longitude};
                            jsonarray.push(jsonTemp);
                            equipmentLocations.push(jsonTemp);
                        });
                    });
                    pid= 0;
                });
                var t = $("#treeCity");
                t = $.fn.zTree.init(t, setting, jsonarray);
            }
        });


        //zTree默认选中指定节点并执行事件
        // var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        // var node = treeObj.getNodeByParam("id", "370000");
        // treeObj.selectNode(node);
        // setting.callback.onClick = function(){};
    });

    function changelistState(){
        window.location.href="${basePath}/manage/equipment/monitor/map?equipmentId="+equipmentId+"&longitude="+longitude+"&latitude="+latitude;
    }

</script>