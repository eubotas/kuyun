<%@ page contentType="text/html; charset=utf-8" %>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script src="http://webapi.amap.com/maps?v=1.4.5&key=706c38ea4b86152fb9862ed09bd23ff8"></script>

    <style>
        .amap-logo{
            display: none;
        }
        .amap-copyright{
            display: none!important;
        }
    </style>


<script type="text/javascript">
    var map, marker;

    function changeCity(obj){
        marker = null;
        var sectionId=obj.id;
        if(sectionId && sectionId.startsWith("add_"))
            mapInit('add');
        else
            mapInit('edit');
    }

    function mapInit(mode){
         var citys = $('#'+mode+'_city').select2('data');
         var city=(citys.length >0)?citys[0].text:"";
         var provinces = $('#'+mode+"_province").select2('data');
         var province=provinces.length >0? provinces[0].text:"";
         if(province && province.endsWith('市'))
             city =province;

        locationCity(mode, city);
    }

    function locationCity(mode, city) {
        map = new AMap.Map(mode+'_mapContainer', {resizeEnable: true,zoom:13});
        map.plugin(["AMap.ToolBar"], function() {
            map.addControl(new AMap.ToolBar());
        });
        if(location.href.indexOf('&guide=1')!==-1){
            map.setStatus({scrollWheel:false})
        }
        map.on('complete', function() {
            var clickEventListener2 = map.on('click', function (e) {
                if(!marker) {
                    var longitude = e.lnglat.getLng(), latitude = e.lnglat.getLat();
                    addMarker(mode, longitude, latitude);
                    setMapInfo(mode, longitude, latitude);
                }
            });
        });
        if(!city) {
            map.plugin(["AMap.CitySearch"], function () {
                var citysearch = new AMap.CitySearch();
                //自动获取用户IP，返回当前城市
                citysearch.getLocalCity();
                AMap.event.addListener(citysearch, "complete", function (result) {
                    if (result && result.city && result.bounds) {
                        var cityName = result.city;
                        var citybounds = result.bounds;
                        //地图显示当前城市
                        map.setBounds(citybounds);
                        map.setCity(cityName);
                        map.setZoom(18);
                    }
                });
                AMap.event.addListener(citysearch, "error", function (result) {
                    alert(result.info);
                });
            });
        }else{
            map.setCity(city);
        }
    }

    function addMarker(mode, longitude, latitude) {
        marker = new AMap.Marker({
            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
            position: [longitude, latitude],
            draggable: true,
            offset: new AMap.Pixel(-9, 0),
            cursor: 'move',
            raiseOnDrag: true
        });

        AMap.event.addListener(marker, 'dragend', function(e){
            var lat = e.lnglat.lat,
                lng = e.lnglat.lng;
            marker.setPosition(new AMap.LngLat(lng,lat));
            setMapInfo(mode, lng, lat);
        });
        marker.setMap(map);
    }

    function setMapInfo(mode, longitude, latitude){
        document.getElementById(mode + "_longitude").value = longitude;
        document.getElementById(mode + "_latitude").value = latitude;
        setCity(mode);
        getAddress(longitude, latitude, mode);
    }

    function getAddress(longitude, latitude, mode) {
        var lnglatXY=[longitude, latitude], geocoder;
        AMap.service('AMap.Geocoder', function () {
            geocoder = new AMap.Geocoder({
                //city: "010"//城市，默认：“全国”
            });
            geocoder.getAddress(lnglatXY, function (status, result) {
                if (status === 'complete' && result.info === 'OK') {
                    var addr= result.regeocode.formattedAddress;
                    $('#'+mode+'_detailAddr').text(addr);
                }
            });
        })
    }

    function setCity(mode) {
        map.getCity(function(data) {
            if (data['province'] && typeof data['province'] === 'string') {
                var provinceCode=getProvinceCode(data['province']);
                $('#'+mode+"_province").val(provinceCode).select2();
                getProCity(provinceCode);
                initCityOptions(mode);
                $('#'+mode+'_city').val(getCityCode(data['city'])).select2();
            }
        });
    }

    function getProvinceCode(name){
        for(var pos=0;pos <provinceList.length;pos++){
            if(provinceList[pos].text == name){
                return provinceList[pos].id;
            }
        }
    }

    function getCityCode(name){
        for(var pos=0;pos <cityList.length;pos++){
            if(cityList[pos].text == name){
                return cityList[pos].id;
            }
        }
        return (cityList.length>0)?cityList[0].id : "";
    }
</script>


