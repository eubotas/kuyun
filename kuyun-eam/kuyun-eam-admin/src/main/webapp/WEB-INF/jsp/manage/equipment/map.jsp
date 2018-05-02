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
    var map;

    function mapInit(mode){
         var city = $('#'+mode+'_city').val();//此处是城市名称
         var province = $(mode+"_province").val();
         if(province && province.endsWith('市'))
             city =province;
        // if(city) {
        //     var partition = new AMap.Partition();
        //     partition.byCity(city, mode+'ByCity_CallBack');
        // }else{
        //     locationCity(mode);
        // }
        locationCity(mode);
    }

    function locationCity(mode) {
        map = new AMap.Map(mode+'_mapContainer', {resizeEnable: true,zoom:11});
        map.plugin(["AMap.ToolBar"], function() {
            map.addControl(new AMap.ToolBar());
        });
        if(location.href.indexOf('&guide=1')!==-1){
            map.setStatus({scrollWheel:false})
        }
        map.on('complete', function() {
            var clickEventListener2 = map.on('click', function (e) {
                document.getElementById(mode+"_longitude").value = e.lnglat.getLng();
                document.getElementById(mode+"_latitude").value = e.lnglat.getLat();
            });
        });
        map.plugin(["AMap.CitySearch"], function() {
            var citysearch = new AMap.CitySearch();
            //自动获取用户IP，返回当前城市
            citysearch.getLocalCity();
            AMap.event.addListener(citysearch, "complete", function(result){
                if(result && result.city && result.bounds) {
                    var cityName = result.city;
                    var citybounds = result.bounds;
                    //地图显示当前城市
                    map.setBounds(citybounds);
                    map.setCity(cityName);
                }
            });
            AMap.event.addListener(citysearch, "error", function(result){alert(result.info);});
        });
    }
</script>


