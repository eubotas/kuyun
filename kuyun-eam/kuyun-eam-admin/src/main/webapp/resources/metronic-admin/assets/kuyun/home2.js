
var json = [${statusSummary}];
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
            formatter: "{a} <br/>{b} : {c} 台 ({d}%)"
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
    $('#maintainplanTable').bootstrapTable({
        url: '${basePath}/manage/maintainPlan/list',
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
            {field: 'planId', title: 'ID', sortable: true, align: 'center'},
            {field: 'equipmentCategoryName', title: '设备目录'},
            {field: 'equipmentName', title: '设备名称'},
            {field: 'workContent', title: '工单内容'},
            {field: 'orgName', title: '负责部门'},
            {field: 'createTime', title: '创建时间', formatter: 'timeFormatter'}
        ]
    });

    $('#allTicketTable').bootstrapTable({
        url: '/manage/ticket/list?category=all',
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
            {field: 'priority', title: '优先级', sortable: true, align: 'center', searchable: true},
            {field: 'ticketType.name', title: '工单类型', sortable: true, align: 'center', searchable: true},
            {field: 'serviceman', title: '维修人', sortable: true, align: 'center', searchable: true},
            {field: 'servicePhone', title: '维修人电话', sortable: true, align: 'center', searchable: true},
            {field: 'customerContacts', title: '提报人', sortable: true, align: 'center', searchable: true},
            {field: 'customerPhone', title: '提报人电话', sortable: true, align: 'center', searchable: true},
            {field: 'status', title: '当前状态', sortable: true, align: 'center', searchable: true, formatter: 'formatStatus'},
            {field: 'description', title: '工单描述', searchable: true}
        ]
    });

    $('#completedTicketTable').bootstrapTable({
        url: '/manage/ticket/list?category=resolved',
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
            {field: 'priority', title: '优先级', sortable: true, align: 'center', searchable: true},
            {field: 'ticketType.name', title: '工单类型', sortable: true, align: 'center', searchable: true},
            {field: 'serviceman', title: '维修人', sortable: true, align: 'center', searchable: true},
            {field: 'servicePhone', title: '维修人电话', sortable: true, align: 'center', searchable: true},
            {field: 'customerContacts', title: '提报人', sortable: true, align: 'center', searchable: true},
            {field: 'customerPhone', title: '提报人电话', sortable: true, align: 'center', searchable: true},
            {field: 'description', title: '工单描述', searchable: true}
        ]
    });

    $('#initTicketTable').bootstrapTable({
        url: '/manage/ticket/list?category=init',
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
            {field: 'priority', title: '优先级', sortable: true, align: 'center', searchable: true},
            {field: 'ticketType.name', title: '工单类型', sortable: true, align: 'center', searchable: true},
            {field: 'customerContacts', title: '提报人', sortable: true, align: 'center', searchable: true},
            {field: 'customerPhone', title: '提报人电话', sortable: true, align: 'center', searchable: true},
            {field: 'description', title: '工单描述', searchable: true}
        ]
    });
});