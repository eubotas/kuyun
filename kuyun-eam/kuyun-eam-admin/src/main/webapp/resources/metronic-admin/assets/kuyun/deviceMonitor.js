    var showHeader = true;
    var menueName = 'sidebar-device';
    var menueName = menueName;

    var message ='';
    var equipname = '';
    var latitude;
    var longitude;
    var linechartoption=[];
    var historyType='line'; //历史数据显示方式 line为曲线 table为表格
    var lineType='最近10分钟';  //历史曲线title 时间部分
    var lineTab='温度曲线';  //历史曲线title 传感器名称
    var linevarstab=[];  //历史曲线tab的传感器数组
    var selectedlinetab={}; //选择展示曲线的传感器对象
    var selectedlindex=1; //默认选择最近10分钟的历史曲线
    var curve={startTime:0,endTime:0,setTime:'自定义时间'};  //历史曲线选择的开始和结束时间 var curve.startTime var curve.endTime
    var showAnalogTab = false; //控制模拟量tab的显示
    var showDigitalTab = false; //控制开关量tab的显示
    var lineLabel=lineType+lineTab;
    var echartValue = [];
    var empty = true;
    var isOnline = true;
    var uploaderUrl = "";//sharedataApi.getUploaderUrl();

    var checktab = function checktab(selecttab){
      selectedlinetab = selecttab;
      lineTab=selecttab.name;
      getHistoryData();
    };

    setHistoryTime = function setHistoryTime(index){
      selectedlindex = index;
      getHistoryData();
    };


    formatStateValue = function(state,unit) {
        var htmlStr="";
          if(unit == null){unit =''}
          if(state == null){state=''}
         if (state == 'OFF' || state == '停止' || state == 'False')
           htmlStr='<a href="javascritp:;" class="btn btn-warning">'+state+'</a>';
         else
         if (state == 'ON' || state == '开启' || state == 'True')
           htmlStr='<a href="javascritp:;" class="btn green btn-info">'+state+'</a>';
         else
           htmlStr='<a href="javascritp:;" class="btn green btn-info">'+state+unit+'</a>';
         return htmlStr;
    };
    formatStateValue2 = function(name) {
        var htmlStr2='<a href="javascritp:;" class="btn blue btn-outline">'+name+'</a>';
        return htmlStr2;
    };

    refreshData = function(){
     // if($state.current.name !='main.device.monitor'){
        if(true){
          clearInterval(timer);
      }else if(selectedequipid && selectedequipid!=null){
        getDataModelAndValues(selectedequipid);
      }
    };
    function setCurvetime() {
      var startDate = new Date(curve.startTime);
      var endDate = new Date(curve.endTime);
      console.log('timer',startDate,endDate);
      if(Date.parse(endDate)-Date.parse(startDate)<=0){
          swWarn( '开始时间必须早于结束时间');
      }else{
        curve.setTime=curve.startTime+'-'+curve.endTime;
          $('#curveTimeBtn').val(curve.setTime);
      }
    }

    function setFreeTime(){
      if(curve.setTime =='自定义时间'){
          swWarn('请先选择要查询的时间段');
      }else{
        setHistoryTime(4);
      }
    }

    function changemode(type){
      if(type=='line'){
        if(historyType=='table'){
          historyType='line';
          getHistoryData();
        }
      }else if(type=='table'){
        if(historyType=='line'){
          historyType='table';
          getHistoryData();
        }
      }
    }

    disalert = function(){
      $('#myModal_alert').modal('hide');
    };
    var linechart;
    player = new EZUIPlayer('myPlayer');
    player.on('play', function(){
      console.log('startPlayVedio');
    });
    player.on('pause', function(){
      console.log('pausePlayVedio');
    });

    if (location.href.indexOf('&guide=1') !== -1) {
        map.setStatus({
            scrollWheel: false
        })
    }

    var timer;
    Date.prototype.format = function(format) {
       var date = {
              "M+": this.getMonth() + 1,
              "d+": this.getDate(),
              "h+": this.getHours(),
              "m+": this.getMinutes(),
              "s+": this.getSeconds(),
              "q+": Math.floor((this.getMonth() + 3) / 3),
              "S+": this.getMilliseconds()
       };
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
       }
       for (var k in date) {
              if (new RegExp("(" + k + ")").test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
              }
       }
       return format;
     };

    function getHistoryData(){
      var newdate = new Date();
      var endtime = Date.parse(newdate);
      var starttime = 0;

      var index = selectedlindex;
      var tab = selectedlinetab;
      switch(index){
        case 1:
          lineType ='最近10分钟';
          starttime = endtime-10*60*1000;
          starttime = (new Date(starttime)).format('yyyy/MM/dd h:m:s');
          endtime = (new Date(endtime)).format('yyyy/MM/dd h:m:s');
        break;
        case 2:
          lineType ='最近24小时';
          starttime = endtime-24*60*60*1000;
          starttime = (new Date(starttime)).format('yyyy/MM/dd h:m:s');
          endtime = (new Date(endtime)).format('yyyy/MM/dd h:m:s');
        break;
        case 3:
          lineType ='最近7天';
          starttime = endtime-7*24*60*60*1000;
          starttime = (new Date(starttime)).format('yyyy/MM/dd h:m:s');
          endtime = (new Date(endtime)).format('yyyy/MM/dd h:m:s');
        break;
        case 4:
          lineType =curve.startTime+'~'+curve.endTime+' ';
          starttime = curve.startTime;
          endtime = curve.endTime;
        break;
        default:
        break;
      }
      $('#lineLabel').text(lineType+lineTab);

      if(historyType =='line'){
        //App.startPageLoading({animate: true});
       ajaxGet('/manage/sensor/data/list/?eId='+selectedequipid+'&sensorId='+tab.varid+'&startDate='+starttime+'&endDate='+endtime, function (result) {
              if(result.length>0 && result.value) {
                // console.log('gethistorydata',result.data);
                   var xdata=result.data.time;
                   var ydata=result.data.value;

                   for(var i=0; i<xdata.length;i++){
                     xdata[i]=(new Date(xdata[i])).format('yyyy/MM/dd h:m:s');
                   }
                   App.stopPageLoading();
                   if(ydata.length>0){
                     linechartoption={
                         tooltip: {
                             trigger: 'axis',
                             formatter: "{a} <br/>{b}: {c}"+tab.unit
                         },
                         grid: {
                             left: '3%',
                             right: '5%',
                             bottom: '3%',
                             containLabel: true
                         },
                         xAxis: {
                             type: 'category',
                             boundaryGap: false,
                             data: xdata
                         },
                         yAxis: {
                            type: 'value',
                            scale: true,
                            axisLabel : {
                                formatter: '{value}'+tab.unit
                            },
                         },
                         series: [
                             {
                                 name: tab.name,
                                 type: 'line',
                                 smooth: '1',
                                 data:  ydata,
                             }
                         ]
                     };
                     linechart.setOption(linechartoption);　
                   }else{
                     resetlineoption();
                   }
              }else {
                //App.stopPageLoading();
              }
          });
      }else if(historyType == 'table'){
        //App.startPageLoading({animate: true});
        //获取表格信息
        ajaxGet('/manage/sensor/data/list/?eId='+selectedequipid+'&sensorId='+tab.varid+'&startDate='+starttime+'&endDate='+endtime, function (result) {
              if(result.length>0 && result.data) {
                  for(var i=0;i<result.data.length;i++){
                    result.data[i].updateTime = changeTimeFormat2(result.data[i].updateTime);
                  }
                  //tableParams = new NgTableParams({}, { dataset: result.data});
                  // App.stopPageLoading();
              }else {
                //tableParams = new NgTableParams({}, { dataset: null});
                //App.stopPageLoading();
              }
          });
      }

    }

    function formatEchartValue(origindata) {
      // console.log('formatValee',origindata);
      echartValue = [];
      if(origindata.length>0){
        for(var i=0; i<origindata.length; i++) {
          if(origindata[i].value == null){
            origindata[i].value = '';
          }else{
            origindata[i].value = Math.floor(origindata[i].value*100)/100;
          }
          if(origindata[i].unit == null){
            origindata[i].unit = '';
          }
          switch(origindata[i].showtype) {
            case 'pie': {
              var tempoption = {
                widgetName:origindata[i].name,
                widgetType: 'pie',
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series: [{
                        name: origindata[i].name,
                        type: 'pie',
                        selectedMode: 'single',
                        radius: [0, '70%'],
                        center: ['50%', '50%'],
                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        color: ['#36d7b7', '#cdcdcd'],
                        data: [{
                                value: origindata[i].value,
                                name: origindata[i].name + origindata[i].value +origindata[i].unit,
                                selected: true
                            },
                            {
                                value: 100-origindata[i].value,
                                name: ''
                            }
                        ]
                    }
                ]
              };
              echartValue.push(tempoption);
            }
            break;
            case 'guage':{
              var tempoption = {
                widgetName:origindata[i].name,
                widgetType: 'gauge',
                tooltip : {
                    formatter: "{a} <br/>{b} : {c}%"
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name:origindata[i].name,
                        type:'gauge',
                        splitNumber: 10,       // 分割段数，默认为5
                        axisLine: {            // 坐标轴线
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']],
                                width: 8
                            }
                        },
                        axisTick: {            // 坐标轴小标记
                            splitNumber: 10,   // 每份split细分多少段
                            length :12,        // 属性length控制线长
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: 'auto'
                            }
                        },
                        axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                color: 'auto'
                            }
                        },
                        splitLine: {           // 分隔线
                            show: true,        // 默认显示，属性show控制显示与否
                            length :30,         // 属性length控制线长
                            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                                color: 'auto'
                            }
                        },
                        pointer : {
                            width : 5
                        },
                        title : {
                            show : true,
                            offsetCenter: [0, '-40%'],       // x, y，单位px
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                fontWeight: 'bolder'
                            }
                        },
                        detail : {
                            formatter:'{value}',
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                color: 'auto',
                                fontWeight: 'bolder'
                            }
                        },
                        data:[{value: origindata[i].value, name: origindata[i].unit}]
                    }
                ]
              };
              echartValue.push(tempoption);

            }
            break;
            case 'led': {
              var tempoption = {
                widgetName:origindata[i].name,
                widgetType: 'led',
                value: origindata[i].value,
                unit: origindata[i].unit,
              }
              echartValue.push(tempoption);
            }
            break;
            default:
            break;
          }
        }
      }
    };
    function resetlineoption(){
      var xdata=[];
      var ydata=[];
      linechartoption={
          tooltip: {
              trigger: 'axis'
          },
          grid: {
              left: '3%',
              right: '5%',
              bottom: '3%',
              containLabel: true
          },
          xAxis: {
              type: 'category',
              boundaryGap: false,
              data: xdata
          },
          yAxis: {
              type: 'value'
          },
          series: [
              {
                  name: '',
                  type: 'line',
                  smooth: '1',
                  data:  ydata
              }
          ]
      };
      linechart.setOption(linechartoption);　
    };


    $(document).ready(function(){

      $('.nav-tabs li a').click(function() {　
          var _id = $(this).attr('href');　　
          //$('.tab-content').find(_id).addClass('active').siblings().removeClass('active');
          switch (_id) {　　　　
              case "#equipment":
                player.pause();
                clearInterval(timer);
                break;　　　　
              case "#run":
                player.pause();
                clearInterval(timer);
                if(isOnline==true){
                  refreshData();
                  timer = setInterval(refreshData,10000);
                  var resizeTimeout;
                  window.onresize=function(){
                    clearTimeout(resizeTimeout); //防止onresize连续调用两次
                    resizeTimeout = setTimeout(function(){
                        refreshData();
                        clearInterval(timer);
                        timer = setInterval(refreshData,10000);
                    },500)
                  };
                }

                break;

              case "#histroyData":
                  {
                    clearInterval(timer);
                    player.pause();
                    $('.start_date').datetimepicker({
                        language: 'zh-CN',
                        weekStart: 1,
                        todayBtn: 1,
                        autoclose: 1,
                        startView: 2,
                        forceParse: 0,
                        // minView:'day',
                        format: 'yyyy/mm/dd hh:ii',
                        todayHighlight: true,
                        }).on('hide', function (e) {
                          var $this = $(this);
                          var _this = this;
                            curve.startTime = _this.value;
                      });
                      $('.end_date').datetimepicker({
                          language: 'zh-CN',
                          weekStart: 1,
                          todayBtn: 1,
                          autoclose: 1,
                          startView: 2,
                          forceParse: 0,
                          // minView:'day',
                          format: 'yyyy/mm/dd hh:ii',
                          todayHighlight: true,
                          }).on('hide', function (e) {
                            var $this = $(this);
                            var _this = this;
                            curve.endTime = _this.value;
                        });

                    if ($("#echarts_line").length > 0) {
                        var mychartContainer = document.getElementById('echarts_line');
                        mychartContainer.style.width=$('#navContainer').width()-20+'px';
                        linechart = echarts.init(mychartContainer);
                        resetlineoption();
                        linechart.setOption(linechartoption);　
                        if(selectedlinetab.name){
                            getHistoryData();
                        }
                    }
                    window.onresize=function(){
                      mychartContainer.style.width=$('#navContainer').width()-20+'px';
                      linechart.resize();
                    };
                  }
                  　　　　　
                  break;
              case "#video":
                    clearInterval(timer);
                  　　　　　　break;
              case "#runData":
                    clearInterval(timer);
                    if(isOnline==true){
                      refreshData();
                      timer = setInterval(refreshData,10000);
                    }
                  　　　　　　break;
              case "#startStop":
                    clearInterval(timer);
                    if(isOnline==true){
                      refreshData();
                      timer = setInterval(refreshData,10000);
                    }
                  　　　　　　break;　　　　
              default:
                  　clearInterval(timer);　　　　　　　　　
                  break;　　
          }
      });
    });

    Date.prototype.format = function(format) {
      var date = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),
            "S+": this.getMilliseconds()
      };
      if (/(y+)/i.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
      }
      for (var k in date) {
            if (new RegExp("(" + k + ")").test(format)) {
                   format = format.replace(RegExp.$1, RegExp.$1.length == 1
                          ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
            }
      }
      return format;
    }

    function changeTimeFormat(timestamp) {
      var newDate = new Date();
      newDate.setTime(timestamp);
      return newDate.format('yyyy-MM-dd');
    }
    function changeTimeFormat2(timestamp) {
      var newDate = new Date();
      newDate.setTime(timestamp);
      return newDate.format('yyyy-MM-dd hh:mm:ss');
    }

   function showHistorySelect2(list){
       var ops=[],op;
       if(list) {
           $.each(list, function(i, val) {
               op={"DESCFIELD":val.name,"VALUEFIELD":val.name};
               ops.push(op);
           });
           addOptionToHtmlSelect(null, "equipmentModelType", ops);
       }
   }

   function resetRunDataList(){
        $("#runDataList").html("");
   }

    function addRunDataList(html){
        $("#runDataList").append(html);
    }

    function onlineHideShow(val){
        if(val == true)
            showIsonline();
        else
            hideIsonline();
    }
    function hideIsonline(){
        $(".isOnline").hide();
        $(".notOnline").show();
    }

    function showIsonline(){
        $(".isOnline").show();
        $(".notOnline").hide();
    }

    function generateRunHtml() {
        var html="";
        $.each(echartValue, function(i, val) {
            html= html+generateBtnHtml(val.widgetName,val.widgetType,val.value+val.unit);
        });
        $('#runDataList').html(html);
    }

    function generateRunPieHtml(widgetName, widgetType, items){
        var html= '<div class="col-md-4 isOnline" >  <div class="portlet light portlet-fit bordered">'+
            '<div class="portlet-title"> <div class="caption"> <i class=" icon-layers font-green"></i>'+
            '<span class="caption-subject font-green bold uppercase">'+widgetName+'</span></div></div>'+
            '<div class="portlet-body">'+
            '<div  widget e-data="'+items+'" e-type="'+widgetType+'" style="height:300px"></div>'+
            '</div> </div> </div>';
        return html;
    }

    function removeActiveTab(){
        $("#navContainer").find("[id^='tab_']").removeClass("active");
    }