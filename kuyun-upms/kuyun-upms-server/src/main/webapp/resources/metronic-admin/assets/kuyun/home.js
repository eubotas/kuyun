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
             return params.seriesName + '<br/>' + params.cnName + ' : ' + value;
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
                {name: 'India',cnName:'印度', value: 9000},
                {name: 'United States',cnName:'美国', value: 9000},
                {name: 'Japan',cnName:'日本', value: 9000},
                {name: 'Canada',cnName:'加拿大', value: 3},
                {name: 'China',cnName:'中国',value: 8900}

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

var json = [
    {value:1980,name:'1月'},
    {value:1980,name:'2月'},
    {value:180,name:'3月'},
    {value:1980,name:'4月'},
    {value:180,name:'5月'},
    {value:980,name:'6月'},
    {value:1980,name:'7月'},
    {value:980,name:'8月'},
    {value:1980,name:'9月'},
    {value:980,name:'10月'},
    {value:1980,name:'11月'},
    {value:240,name:'12月'}
];
createBar('echarts_line', json, '能耗图','销量','line');

var json = [
    {value:50,name:'1月'},
    {value:80,name:'2月'},
    {value:80,name:'3月'},
    {value:90,name:'4月'},
    {value:10,name:'5月'},
    {value:80,name:'6月'},
    {value:10,name:'7月'},
    {value:80,name:'8月'},
    {value:10,name:'9月'},
    {value:90,name:'10月'},
    {value:10,name:'11月'},
    {value:20,name:'12月'}
];
createBar('echarts_bar1', json, '开机率','销量');

var json = [
    {value:50,name:'1月'},
    {value:80,name:'2月'},
    {value:80,name:'3月'},
    {value:90,name:'4月'},
    {value:10,name:'5月'},
    {value:80,name:'6月'},
    {value:10,name:'7月'},
    {value:80,name:'8月'},
    {value:10,name:'9月'},
    {value:90,name:'10月'},
    {value:10,name:'11月'},
    {value:20,name:'12月'}
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
                        var colorList = ['#2ab4c0','#f36a5a','#8e44ad','#e7505a','#2ab4c0','#26c281','#2ab4c0','#f36a5a','#8e44ad','#e7505a','#2ab4c0','#26c281'];
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