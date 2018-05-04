<%@ page contentType="text/html; charset=utf-8"%>
<!-- BEGIN: Horizontal Menu -->
<button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-dark " id="m_aside_header_menu_mobile_close_btn">
    <i class="la la-close"></i>
</button>
<div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-dark m-aside-header-menu-mobile--submenu-skin-dark ">
    <ul class="m-menu__nav  m-menu__nav--submenu-arrow ">
        <li id="header_menu_equiOp" onclick="setmenu(this);" class="m-menu__item  m-menu__item--submenu m-menu__item--rel" data-menu-submenu-toggle="click" data-redirect="true" aria-haspopup="true">
            <a href="#" class="m-menu__link m-menu__toggle">
                <i class="m-menu__link-icon flaticon-add"></i>
                <span class="m-menu__link-text">
												设备运营
											</span>
                <i class="m-menu__ver-arrow la la-angle-right"></i>
            </a>
        </li>
        <li id="header_menu_assets" onclick="setmenu(this);" class="m-menu__item  m-menu__item--submenu m-menu__item--rel" data-menu-submenu-toggle="click" data-redirect="true" aria-haspopup="true">
            <a href="#" class="m-menu__link m-menu__toggle">
                <i class="m-menu__link-icon flaticon-line-graph"></i>
                <span class="m-menu__link-text">
												资产管理
											</span>
                <i class="m-menu__ver-arrow la la-angle-right"></i>
            </a>
        </li>
        <li id="header_menu_system" onclick="setmenu(this);" class="m-menu__item  m-menu__item--submenu m-menu__item--rel" data-menu-submenu-toggle="click" data-redirect="true" aria-haspopup="true">
            <a href="#" class="m-menu__link m-menu__toggle">
                <i class="m-menu__link-icon flaticon-line-graph"></i>
                <span class="m-menu__link-title">
												<span class="m-menu__link-wrap">
													<span class="m-menu__link-text">
														 系统配置
													</span>
												</span>
											</span>
                <i class="m-menu__ver-arrow la la-angle-right"></i>
            </a>
        </li>
    </ul>
</div>
<!-- END: Horizontal Menu -->