var echarts_world = echarts.init(document.getElementById('echarts_world'));
option = {
    title: {
        text:'全球设备分布情况 (2017)',
        left: 'center',
        top: 'top'
    },
    tooltip: {
         trigger: 'item',
         formatter: function (params) {
             var value = (params.value + '').split('.');
             if(value[0] == "NaN")
                 return '';
             value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,')
                     + '台';
             return params.seriesName + '<br/>' + params.name + ' : ' + value;
         }
     },

    visualMap: {
        min: 0,
        max: 10000,
        text:['High','Low'],
        realtime: false,
        calculable: true,
        color: ['orangered','yellow','lightskyblue']
    },
    series: [
        {
            name: '全球设备分布情况 (2017)',
            type: 'map',
            mapType: 'world',
            roam: true,
            itemStyle:{
                emphasis:{label:{show:true}}
            },
            data:[
                {name: 'United Arab Emirates', value: 10},
                {name: 'Armenia', value: 100},
                {name: 'Canada', value: 3},
                {name: 'China',value: 2000}

            ]
        }
    ]
};
echarts_world.setOption(option);


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
                            var colorList = ['rgb(164,205,238)','rgb(42,170,227)','rgb(25,46,94)','rgb(195,229,235)'];
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

var json = [
    {value:1980,name:'在线'},
    {value:1980,name:'在线2'},
    {value:1980,name:'在线3'},
    {value:240,name:'离线'}
];
createBar('echarts_line', json, '能耗图','销量','line');

var json = [
    {value:1980,name:'在线'},
    {value:1980,name:'在线2'},
    {value:1980,name:'在线3'},
    {value:240,name:'离线'}
];
createBar('echarts_bar1', json, '开机率','销量');

var json = [
    {value:1980,name:'在线'},
    {value:1980,name:'在线2'},
    {value:1980,name:'在线3'},
    {value:240,name:'离线'}
];
createBar('echarts_bar2', json, '合格率','销量');

function createBar(id, json, title, name, type) {
    var barType='bar';
    var showType= ['pie','line', 'funnel'];
    if(type) {
        barType = type;
        showType= ['pie','bar', 'funnel'];
    }
    if(!json instanceof Array)
        return;
    var names=new Array(), vals=new Array(), tatal=0;
    for(var p in json){
        names.push(json[p].name);
        vals.push(json[p].value);
        tatal =tatal+ json[p].value;
    }
    var echarts_bar1 = echarts.init(document.getElementById(id));
    var option = {
        title: {
            text: title
        },
        label: {
            normal: {
                show: true,
                position: 'inside'
            }
        },
        tooltip: {},
        legend: {
            data: name
        },
        toolbox: {
            show: true,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: showType,
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        xAxis: {
            data: names
        },
        yAxis: {},
        series: [{
            name: name,
            type: barType,
            data: vals,
            itemStyle: {
                //通常情况下：
                normal: {
                    //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                    color: function (params) {
                        var colorList = ['rgb(164,205,238)', 'rgb(42,170,227)', 'rgb(25,46,94)', 'rgb(195,229,235)'];
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
        }]
    };
    echarts_bar1.setOption(option);
}

// myChart.showLoading();
// $.get('data.json').done(function (data) {
//     myChart.hideLoading();
//     myChart.setOption(option);
// });