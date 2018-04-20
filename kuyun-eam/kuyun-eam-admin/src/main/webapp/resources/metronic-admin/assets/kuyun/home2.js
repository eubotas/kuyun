
var json = [
    {value:1980,name:'在线'},
    {value:240,name:'离线'}
];
createPie('echarts_pie1', json, '设备在线状态');

var json = [
    {value: 1980, name: '正常运行'},
    {value: 240, name: '故障停机'}
];
createPie('echarts_pie2', json, '设备运行状态');
function createPie(id, json, name) {
    if(!json instanceof Array)
        return;
    var echarts_pie2 = echarts.init(document.getElementById(id));
    var names=new Array(), tatal=0;
    for(var p in json){
        names.push(json[p].name);
        tatal =tatal+ json[p].value;
    }
    var option = {
        title: {
            text: '',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} 台"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: names
        },
        toolbox: {   },
        calculable: true,
        series: [
            {
                name: name,
                type: 'pie',
                radius: '55%',//饼图的半径大小
                center: ['50%', '60%'],//饼图的位置
                data: json,
                itemStyle: {
                    //通常情况下：
                    normal:{
                        //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                        color: function (params){
                            var colorList = ['#2ab4c0','#f36a5a'];
                            return colorList[params.dataIndex];
                        }
                    },
                    //鼠标悬停时：
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    echarts_pie2.setOption(option);
}


$(function() {
    // bootstrap table初始化
    $('#alarmTable').bootstrapTable({
        url: '${basePath}/manage/codeValue/list',
        striped: true,
        searchAlign: 'left',
        toolbarAlign: 'right',
        minimumCountColumns: 2,
        clickToSelect: true,
        detailView: true,
        detailFormatter: 'detailFormatter',
        pagination: true,
        paginationLoop: false,
        sidePagination: 'server',
        silentSort: false,
        smartDisplay: false,
        escape: true,
        maintainSelected: true,
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'id', title: 'ID', sortable: true, align: 'center'},
            {field: 'category', title: '类别'},
            {field: 'codeValue', title: 'Code'},
            {field: 'codeName', title: 'Code名称'},
            {field: 'description', title: '描述'},
            {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'}
        ]
    });

    $('#maintanceTable').bootstrapTable({
        url: '${basePath}/manage/codeValue/list',
        striped: true,
        searchAlign: 'left',
        toolbarAlign: 'right',
        minimumCountColumns: 2,
        clickToSelect: true,
        detailView: true,
        detailFormatter: 'detailFormatter',
        pagination: true,
        paginationLoop: false,
        sidePagination: 'server',
        silentSort: false,
        smartDisplay: false,
        escape: true,
        maintainSelected: true,
        columns: [
            {field: 'ck', checkbox: true},
            {field: 'id', title: 'ID', sortable: true, align: 'center'},
            {field: 'category', title: '类别'},
            {field: 'codeValue', title: 'Code'},
            {field: 'codeName', title: 'Code名称'},
            {field: 'description', title: '描述'},
            {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'}
        ]
    });
});