
function selectedInit(menuSelectItem){
    var headerMenu="header_menu_equiOp";
    var leftMenu="";
    var selectedMenu="";
    if(menuSelectItem.startsWith("header_")){
        headerMenu = menuSelectItem;
        leftMenu=menuSelectItem.substring(menuSelectItem.indexOf("_")+1,menuSelectItem.length)
    }else if(menuSelectItem.startsWith("submenu_")){
        var leftSubmenu="";
        if(menuSelectItem.indexOf("system") > -1)
            headerMenu = "header_menu_system";
        else if(menuSelectItem.indexOf("assets") > -1) {
            headerMenu = "header_menu_assets";
            leftSubmenu= menuSelectItem.substring(0, menuSelectItem.length-1);
        }
        selectedMenu=menuSelectItem;
        leftMenu="menu_"+menuSelectItem.substring(8,14); //middle
        selectedColor(selectedMenu);
        $("#" + selectedMenu).addClass('m-menu__item--active');
    }
    selectedColor(headerMenu, 'border-bottom');
    $("#" + headerMenu).addClass('m-menu__item--active');
    $("#menu").append($("#menuDashboard").html()).append($("#"+leftMenu+"List").html());
    $("#" + leftMenu).show();
    $("#" + leftMenu).addClass('m-menu__item--open');
    if(leftSubmenu != ""){
        $("#" + leftSubmenu).show();
        $("#" + leftSubmenu).addClass('m-menu__item--open');
    }
}

function selectedColor(id, borderAlign){
    if(!borderAlign)
        borderAlign ='border-left';
    if(id.startsWith("submenu") || id.startsWith("menu"))
        $("#menu").find("li>a").css('border-left','solid 5px transparent');
    else if(id.startsWith("header"))
        $("#m_header_menu").find("li>a").css('border-bottom','solid 5px transparent');
    $("#"+id).find("[href]").css(borderAlign,'solid 5px rgba(85, 111, 237, 0.47)');
    $("#" + id).addClass('m-menu__item--active');
}

function setmenu(e){
    var selItemId=e.id;
    setmenuById(selItemId);
}

function setmenuById(selItemId){
    //header menu
    if(selItemId=='header_menu_equiOp'){
        if(!isMenuContainHml("menu_assetsTitle") && !isMenuContainHml("menu_systemTitle") && !isMenuContainHml("menu_equiOpTitle"))
            $("#menu").append($("#menuDashboard").html()).append($("#menu_equiOpList").html());
        else {
            $("#menu").html("");
            $("#menu").append($("#menuDashboard").html()).append($("#menu_equiOpList").html());
        }
        $("#submenu_equiOp").show();
        setTimeout(function(){
            setmenuById('submenu_equiOp_DataCollect');
            window.location = eamPath+'/manage/equipment/monitor/list';
        },300);
    }else if(selItemId=='header_menu_assets'){
        if(!isMenuContainHml("menu_assetsTitle") && !isMenuContainHml("menu_systemTitle") && !isMenuContainHml("menu_equiOpTitle"))
            $("#menu").append($("#menuDashboard").html()).append($("#menu_assetsList").html());
        else {
            $("#menu").html("");
            $("#menu").append($("#menuDashboard").html()).append($("#menu_assetsList").html());
        }
        setTimeout(function(){
            setmenuById('submenu_assetsEquipment1');
            window.location =eamPath+ '/manage/equipment/model/index';
        },300);
    }else if(selItemId=='header_menu_system'){
        if(!isMenuContainHml("menu_assetsTitle") && !isMenuContainHml("menu_systemTitle") && !isMenuContainHml("menu_equiOpTitle"))
            $("#menu").append($("#menuDashboard").html()).append($("#menu_systemList").html());
        else {
            $("#menu").html("");
            $("#menu").append($("#menuDashboard").html()).append($("#menu_systemList").html());
        }
        $("#submenu_system").show();
        setTimeout(function(){
            setmenuById('submenu_system2');
            window.location = upmsPath + '/manage/organization/index';
        },300);
    }

    if(selItemId.startsWith("header")) {
        $("li[id^='header_menu_']").each(function(){
            $(this).removeClass("m-menu__item--active");
        });
        selectedColor(selItemId, 'border-bottom');
    }else
        selectedColor(selItemId);

    if (selItemId != "") {
        menuSelectItem = selItemId;
        setCookie("kuyunMenuSelectItem", menuSelectItem);
    }
}

function isMenuContainHml(id){
    return isContainHml($("#menu").html(), "id=\""+id+"\"");
}

function isContainHml(html, subHtml){
    if(html.indexOf(subHtml) > -1)
        return true;
    else
        return false;
}


function setCookie(cname,cvalue,exdays){
    delCookie(cname);
    if(!exdays)
        exdays =30;
    var d = new Date();
    d.setTime(d.getTime()+(exdays*24*60*60*1000));
    var expires = "expires="+d.toGMTString();
    document.domain = siteDomain;
    document.cookie = cname + "=" + escape(cvalue) + ";path=/;"
        + expires;
}



function getCookie(cname){
    var name = cname + "=";
    var ca = document.cookie.split(';');
    var len=ca.length;
    if(len > 0) {
        for (var i = len-1; i > -1; i--) {
            var c = $.trim(ca[i]);
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
    }
    return "";
}

function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != "") {
        document.domain = siteDomain;
        document.cookie = name + "=" + cval + ";path=/;expires=" + exp.toGMTString();
    }
}