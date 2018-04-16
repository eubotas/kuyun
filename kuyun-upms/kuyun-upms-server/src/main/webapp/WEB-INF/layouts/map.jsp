<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>点标记</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <style>
        .marker {
            color: #ff6600;
            padding: 4px 10px;
            border: 1px solid #fff;
            white-space: nowrap;
            font-size: 12px;
            font-family: "";
        }
    </style>
    <script src="http://webapi.amap.com/maps?v=1.4.5&key=706c38ea4b86152fb9862ed09bd23ff8"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div id="container"></div>
<div  class="button-group">
    <input type="button" class="button" value="添加点标记覆盖物" id="addMarker"/>
    <input type="button" class="button" value="删除点标记覆盖物" id="clearMarker"/>
</div>
<script>
    var marker, map = new AMap.Map("container", {
        resizeEnable: false,
        center: [116.397428, 39.90923],
        zoom: 5
    });
    AMap.event.addDomListener(document.getElementById('addMarker'), 'click', function() {
        addMarker();
    }, false);
    AMap.event.addDomListener(document.getElementById('updateMarker'), 'click', function() {
        marker && updateMarker();
    }, false);
    AMap.event.addDomListener(document.getElementById('clearMarker'), 'click', function() {
        if (marker) {
            marker.setMap(null);
            marker = null;
        }
    }, false);

    // 实例化点标记
    function addMarker() {
        marker = new AMap.Marker({
            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: [116.405467, 39.907761]
        });
        marker.setMap(map);
        updateMarker();
    }

    function updateMarker() {
        // 自定义点标记内容
        var markerContent = document.createElement("div");

        // 点标记中的图标
        var markerImg = document.createElement("img");
        markerImg.className = "markerlnglat";
        markerImg.src = "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png";
        markerContent.appendChild(markerImg);

        // 点标记中的文本
        var markerSpan = document.createElement("span");
        markerSpan.className = 'marker';

        var markInfo='<div style="color: white;font-size: 14px;background-color: rgba(0,155,255,0.8);line-height: 26px; padding: 0px 0 0 6px; font-weight: lighter; varter-spacing: 1px">设备概况</div>' +
            '<div style="padding: 4px;line-height: 45px; width:200px; background-color:rgba(100, 119, 132, 0.8);">' +
            '<img style=" float: left; margin: 3px; width: 60px" src="http://122.112.237.243:9498/files/f46a4f9d-abcc-43bf-8020-c8d3677b5f16">' +
            '<a href="javascritp:void(0);" ng-click="selectNodefromMap()">测试</a><br>wlPQ5hKAIFSyzu5g<br></div>';

        markerSpan.innerHTML = markInfo;
        markerContent.appendChild(markerSpan);

        marker.setContent(markerContent); //更新点标记内容
        marker.setPosition([116.391467, 39.927761]); //更新点标记位置
    }
</script>
</body>
</html>