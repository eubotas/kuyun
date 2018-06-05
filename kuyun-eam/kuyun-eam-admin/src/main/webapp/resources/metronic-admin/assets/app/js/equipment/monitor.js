$(document).ready(function()
{
    $('#equipmentModelType').select2({minimumResultsForSearch: -1});
});

function callbackShowEquip(treeNode){
    selectedequipid=treeNode.id;
    showEquipmentInfo(selectedequipid);
    getDataModel(selectedequipid);
}

function showEquipmentInfo(eid){
    ajaxGet(basePath+'/manage/equipment/'+selectedequipid, function (responseData) {
        if (responseData.code ==1) {
            var data=responseData.data;

            $('#tab_equipment').addClass('active').siblings().removeClass('active');
            $('.tab-content').find('#equipment').addClass('active').siblings().removeClass('active');
            equipmentId = data.equipmentId;
            equipmentModelId = data.equipmentModelId;
            setText('serialNumber',data.serialNumber);
            setText('equipmentId',data.equipmentId);
            setText('equipname',data.name);
            setText('number',data.number);
            setText('factoryDate',changeTimeFormat(data.factoryDate));
            setText('commissioningDate',changeTimeFormat(data.commissioningDate));
            setText('warrantyStartDate',changeTimeFormat(data.warrantyStartDate));
            setText('warrantyEndDate',changeTimeFormat(data.warrantyEndDate));
            isOnline = data.isOnline;
            if(isOnline==null){
                isOnline = false;
            }
            setText('isOnline',data.isOnline? '在线':'离线');
            longitude = data.longitude;
            latitude =data.latitude;

            $("#imagesrc").attr("src",getImagePath(data.imagePath));
            onlineHideShow(isOnline);
        }
        $('#eqRight').height($('#eqLeft').height());
        $('#imagesrc').height($('#eqLeft').height()-10);
        $('#imagesrc').width($('#eqLeft').width()-10);
    });

}

function getDataModel(equipid){
    ajaxGet(basePath+'/manage/equipment/sensor/data/'+selectedequipid, function (responseData) {
        if(responseData.code == 1) {
            var analogflag=0,digitalflag=0;
            var dataArr=responseData.data;
            if(dataArr!=null && dataArr.length>0){
                $.each(dataArr,function(i, val) {
                        if(val.type == 'analog'){
                            analogflag = analogflag+1;
                            showAnalogTab = true;
                            setText('groupname0',val.groupName);
                            varsArr0=val.vars;
                            $('#groupname0List').html(showRunDataListHtml(varsArr0));
                            showHistorySelect2(varsArr0);
                            formatEchartValue(varsArr0);
                            linevarstab = [];
                            for(var j=0;j<val.vars.length;j++){
                                if(val.vars[j].showchart == true){
                                    linevarstab.push(val.vars[j]);
                                }
                            }
                            if(linevarstab.length>0){
                                selectedlinetab = linevarstab[0];
                                lineTab=selectedlinetab.name;
                                lineLabel=lineType+lineTab;
                            }
                        }
                        if(val.type == 'digital'){
                            digitalflag = digitalflag+1;
                            showDigitalTab = true;
                            setText('groupname1',val.groupName);
                            varsArr1=val.vars;
                            $('#groupname1List').html(showRunDataListHtml(varsArr1));
                            showHistorySelect2(varsArr1);
                        }
                        if(digitalflag == 0){showDigitalTab = false;}
                        if(analogflag == 0){showAnalogTab = false;}
                    }
                );
                generateRunHtml();
                $('#groupname0List, #groupname1List').find(".btn").css("cursor","text");
            }else if(dataArr!=null && dataArr.length==0){
                groupname0=null;
                varsArr0=[];
                formatEchartValue(varsArr0);
                linevarstab = [];
                groupname1=null;
                varsArr1=[];
                showDigitalTab = false;
                showAnalogTab = false;
            }

            showId('nav_runData',showAnalogTab);
            showId('nav_startStop',showDigitalTab);
            onlineHideShow(isOnline);
        }
    });
}

function getDataModelAndValues(equipid) {
    ajaxGet(basePath+'/manage/equipment/sensor/data/'+selectedequipid, function (responseData) {
        if(responseData.code == 1) {
            var dataArr=responseData.data;
            if(dataArr!=null && dataArr.length>0){
                $.each(dataArr,function(i, val) {
                    if(val.type == 'analog'){
                        groupname0=val.groupName;
                        varsArr0=val.vars;
                        formatEchartValue(varsArr0);
                    }
                    if(val.type == 'digital'){
                        groupname1=val.groupName;
                        varsArr1=val.vars;
                    }
                });
                onlineHideShow(isOnline);
            }
        }
    });
}

//toMap
function callbackShowMap(treeNode){
    selectedequipid=treeNode.id;
    selectedMark(treeNode);
}

function change2Map(){
    setMarkOnMap();
    var eqloc=getEqloc(selectedequipid);
    if(eqloc)
        selectedMark(eqloc);
    changeMode();
}

function change2List(id){
    showEquipmentInfo(id);
    changeMode();
    selectNode();
}

function getEqloc(eid){
    var equipmentloc;
    $.each(equipmentLocations, function (n, eqloc) {
        if(eqloc.id==eid) {
            equipmentloc = eqloc;
        }
    });
    return equipmentloc;
}

function selectNode(){
    if(selectedequipid) {
        var treeObj = $.fn.zTree.getZTreeObj("treeCity");
        var devNodes = treeObj.getNodesByParam("id", selectedequipid, null);
        treeObj.selectNode(devNodes[0]);
        treeObj.expandAll(true);
    }
}