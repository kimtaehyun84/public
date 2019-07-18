<%--
  Created by IntelliJ IDEA.
  User: Taehyun Kim
  Date: 2019-04-08
  Time: 오전 10:07
  To change this template use File | Settings | File Templates.
--%>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="datetime" pattern="yyyyMM" value="${now}"/>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" charset="utf-8">
        sessionStorage.setItem("contextPath", "${pageContext.request.contextPath}");
    </script>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>Please Insert Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/animate.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/skins/_all-skins.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/SLAmenu.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/dist/css/SLAadd.css">

    <!-- fonts -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/fontello-icon-file.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/font-themify.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/materialdesignicons.min.css">

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="<%=request.getContextPath()%>/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="<%=request.getContextPath()%>/dist/js/SLAapp.js"></script>

    <!-- angular js -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/lib/angularjs/angular.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/lib/angularjs/angular-route.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/lib/angularjs/angular-cookies.js"></script>
    <!-- [User AngularJS Files] -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/App.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/session/controller/SessionController.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/session/service/SessionService.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/CommonService.js"></script>


    <!-- utill -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/service/UtilService.js"></script>

    <!-- Util beans -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/jsbn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/prng4.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/rng.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/rsa.js"></script>

    <style>
        .selected {
            background-color:#fffcde !important;
        }
        body.skin-v2.fixed.ng-scope.modal-open.changePasswordPop .modal-backdrop.fade.in{
            z-index: 1020;
        }
    </style>

</head>

<body ng-app="myApp" class="skin-v2 fixed">
<div class="wrapper">

    <!-- HEADER -->
    <header class="main-header" ng-controller = "sessionCtrl">
        <div class="nav-left">
            <span class="logo" style="width:200px;"><img src="<%=request.getContextPath()%>/images/SBI_Logo_h.png"  alt="Logo"></span>
            <a href="" class="menu menu-panel-toggle">
                <span class="menu-img"></span>
            </a>
            <a href="" class="dashboard" ng-click="tabOpen();"><i class="fa fa-dashboard"></i> Dashboard</a>
        </div>
        <nav class="navbar" role="navigation">
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav navbar-top-links">
                    <li class="dropdown">
                        <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#">
                            <b class="hidden-xs">{{getSession('userName')}}[{{getSession('userId')}}]</b>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li>
                                <div class="u-text">
                                    <h4>{{getSession('userName')}}</h4>
                                    <p class="text-muted">{{getSession('userId')}}</p>
                                </div>
                            </li>
                            <li class="divider" style="margin-top:0"></li>
                            <li>
                                <a data-toggle="modal" data-target="#changePasswordPop" ><i class="ti-settings"></i> Change Password</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a ng-click="logout();"><i class="fa fa-power-off"></i>&nbsp; Logout</a>
                            </li>
                            <li class="divider" style="margin-bottom:0"></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a style="cursor:pointer; font-size:20px" data-toggle="modal" data-target=".infomodal"><i class="mdi mdi-information-outline"></i></a>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- change password pop btn-->
       <%--<button type="button" id="changePasswordPopOpen" data-toggle="modal" data-target="#changePasswordPop" hidden="true"></button>--%>
        <!-- //change password pop btn-->
        <ng-include src="'html/user/ChangePasswordPopUp.html'"></ng-include>

    </header>
    <!--// HEADER -->

    <!-- LNB -->
    <ng-include src="'html/menu/MenuTree.html'"></ng-include>


    <!-- CONTENTS -->
    <div class="content-wrapper">
        <section class="content">
            <div role="tabpanel">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li id="li_1" class="tabLi active">
                        <a id="Dashboard" class="tabLink" href="#contact_Home" aria-controls="contact_Home" role="tab" data-toggle="tab">Dashboard</a>
                        <!--                   	<span id="HomeClose">x</span> -->
                    </li>
                </ul>


                <div class="tab-content">
                    <div class="tab-pane active" id="contact_Home" role="tabpanel" >
                        <div ng-include="'html/dashBoard_sla.html'"></div>
                    </div>
                </div>

            </div>
        </section>
    </div>



    <!--// CONTENTS -->

    <!-- FOOTER -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0
        </div>
        <strong>Copyright © 2018.</strong> All rights reserved.
    </footer>
    <!--// FOOTER -->

    <!-- add_process modal -->
    <div class="modal fade add_process" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-md">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel"><i class="fa fa-file-text"> Add Process</i></h4>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th scope="row"><label for="Type">Vendor Name</label></th>
                                <td colspan="2">
                                    <select class="form-control" id="Type">
                                        <option>All</option>
                                        <option>search-title002</option>
                                        <option>search-title003</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><label for="Customer">Interval Point</label></th>
                                <td>
                                    <select class="form-control">
                                        <option>Oct</option>
                                        <option>search-title002</option>
                                        <option>search-title003</option>
                                    </select>
                                </td>
                                <td>
                                    <select class="form-control">
                                        <option>2017</option>
                                        <option>search-title002</option>
                                        <option>search-title003</option>
                                    </select>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary btn-sm"><i class="fa fa-check fa-fw"></i>SAVE</button>
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close fa-fw"></i>CLOSE</button>
                </div>
            </div>
        </div>
    </div>
    <!--// add_process modal -->

    <!-- info pop modal -->
    <div class="infomodal modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div ref="modalBox" class="modal-content">
                <div class="modal-header">
                    <button type="button" data-dismiss="modal" class="close" click="close()">
                        <i class="fa fa-close"></i>
                    </button>
                    <h4 class="modal-title">SLA</h4>
                </div>
                <div class="modal-body modal-no-scroll">
                    <div class="modal-body-inner">
                        <div class="about-modal">
                            <!-- <div class="logo"><img src="@/assets/images/mm_logo_Origin.png" alt="MoniManager"></div> -->
                            <p class="text-blue"><br><b> HYOSUNG INC.</b></p>
                            <p class="text-red">Version : V01.00.01.00</p>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group mb-0">
                        <button type="button" class="btn btn-close waves-effect" data-dismiss="modal" click="close()">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- //info pop modal -->


</div>

</body>


<!-- <body ng-app="myApp" class="sidebar-mini skin-purple fixed">
	<div class="wrapper">

		HEADER
		<header class="main-header" ng-controller="headCtrl">

			emergency_popup_bg
			<div id="emergencyPopShow" class="emergency_popup_bg" style="display:none">
				<div class="emergency_popup">
					<h1><img src="images/emergency_popup_icon.png" alt="" />EMERGENCY!!!</h1>
					<span class="txt_area">
						<span class="tit">Technician is in Danger!</span>

						<span>Call <span class="police">{{eAlarm_0}}</span> NOW!!</span><br>
						<span>{{eAlarm_7}}</span><br><br>

						ATM location <span class="customer">{{eAlarm_1}}</span><br>
						<span class="branch">{{eAlarm_2}}</span><br>
						<span class="address">{{eAlarm_3}}</span><br>
						<span class="City">{{eAlarm_4}}</span>, <span class="City">{{eAlarm_5}}</span> <span class="City">{{eAlarm_6}}</span><br>
					</span>
					<div class="modal-footer">
						<button type="button" class="btn btn-default btn-lg" data-dismiss="modal" ng-click="confirmEAlarmPop();"><i class="fa fa-pencil fa-fw"></i> Ok </button>
						<button type="button" class="btn btn-default btn-lg" data-dismiss="modal" ng-click="closeEAlarmPop();"><i class="fa fa-close fa-fw"></i> Close </button>
					</div>
				</div>
			</div>
			// emergency_popup_bg

			<a href="{{preUrl}}/goMain" ng-click="goHome()" class="logo">
				<span class="logo-mini"><img src="images/sub_Monilogo2.png" alt="MoniASSIST"/></span>
				<span class="logo-lg"><img src="images/sub_Monilogo1.png" alt="MoniASSIST"/></span>
			</a>
			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
					<span class="sr-only">Toggle navigation</span>
				</a>
				<span id="hamburgerMobile"><i class="fa fa-ellipsis-v"></i></span>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li><a href="#" data-toggle="tooltip" data-placement="bottom" title="{{custAuthTooltip}}" ng-click="addTab('myAccessTab', 'My Access Authority', '/html/admin/authorityMng/myAccessAuth.html')"><i class="fa fa-usd fa-fw"></i> Customer <span class="txtC_purple">[ {{custAuth}} ]</span></a></li>
						<li><a href="#" data-toggle="tooltip" data-placement="bottom" title="{{stateAuthTooltip}}" ng-click="addTab('myAccessTab', 'My Access Authority', '/html/admin/authorityMng/myAccessAuth.html')"><i class="fa fa-map fa-fw"></i> Region <span class="txtC_purple">[ {{stateAuth}} ]</span></a></li>
						<li><a href="#" data-toggle="tooltip" data-placement="bottom" title="{{branAuthTooltip}}" ng-click="addTab('myAccessTab', 'My Access Authority', '/html/admin/authorityMng/myAccessAuth.html')"><i class="fa fa-building-o fa-fw"></i> Branch <span class="txtC_purple">[ {{branAuth}} ]</span></a></li>
						<li><a href="#" data-toggle="tooltip" data-placement="bottom" title="{{siteAuthTooltip}}" ng-click="addTab('myAccessTab', 'My Access Authority', '/html/admin/authorityMng/myAccessAuth.html')"><i class="fa fa-building-o fa-fw"></i> Site <span class="txtC_purple">[ {{siteAuth}} ]</span></a></li>
						<li class="navbar_nav"><span style="color:#fff;">I</span></li>
						<li><a href="#" ng-click="dashboardPop();"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
						<li><a href="#"><i class="fa fa-user fa-fw"></i> {{getSession('userName')}}[{{getSession('userId')}}]</a></li>
						<li><a href="#" ng-click="logout();"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul>
				</div>
			</nav>
		</header>
		// HEADER

		LNB
		<ng-include src="'html/left.html'"></ng-include>
		<aside class="main-sidebar" ng-controller="menuCtrl">
			<section class="sidebar">
				<ul class="sidebar-menu">

					menu loop
					<li class="treeview" ng-repeat="d0 in menus">1 Depth
						<a href="#" ng-click="addTab(d0.tabId, d0.tabTitle, d0.url, d0.menuId);">
							<i class="fa {{d0.icon}}"></i> <span>{{d0.title}}</span>
							<i class="fa fa-angle-left pull-right"></i>
						</a>
						<ul class="treeview-menu">2 Depth
							<li ng-repeat="d1 in d0.dth1">
								<a href="" ng-click="addTab(d1.tabId, d1.tabTitle, d1.url)">
									<i class="fa {{d1.icon}}"></i>{{d1.title}}
									<i ng-show="d1.dth2.length != 0" class="fa fa-angle-left pull-right"></i>
								</a>
								<ul class="treeview-menu">3 Depth
									<li id="menu_{{d2.menuId}}" class="finalDepth" ng-repeat="d2 in d1.dth2">
										<a href="" ng-click="addTab(d2.tabId, d2.tabTitle, d2.url, d2.menuId)">
											<i class="fa {{d2.icon}}"></i>{{d2.title}}
											<i ng-show="d2.dth3.length != 0" class="fa fa-angle-left pull-right"></i>
										</a>
										<ul class="treeview-menu">4 Depth
											<li id="menu_{{d3.menuId}}" class="finalDepth" ng-repeat="d3 in d2.dth3">
												<a href="" ng-click="addTab(d3.tabId, d3.tabTitle, d3.url, d3.menuId)">
													<i class="fa {{d3.icon}}"></i>{{d3.title}}
												</a>
											</li>
										</ul>
									</li>
								</ul>
							</li>
						</ul>
					</li>// menu loop

					// sample
					<li class="treeview">
						<a href="#">
							<i class="fa fa-folder"></i> <span>SAMPLE</span>
						</a>
						<ul class="treeview-menu">
							<li>
								<a href="" ng-click="addTab('kendoUITab', 'Sample', '/html/grid.html');">
									<i class="fa fa-folder"></i> <span>Tree Grid Sample</span>
								</a>
							</li>
						</ul>
					</li>
					// sample

				<li class="header">HELP</li>

				<li><a id="noticeMenu" href="" ng-click="addTab('noticeTab', 'Notice', '/html/help/notice.html')"><i class="fa fa-circle-o text-green"></i> <span>NOTICE</span></a></li>
				<li><a id="myAccessMenu" href="" ng-click="addTab('myAccessTab', 'My Access Authority', '/html/admin/authorityMng/myAccessAuth.html')"><i class="fa fa-circle-o text-green"></i> <span>My Access Authority</span></a></li>
				<li><a id="accessMngMenu" href="" ng-click="addTab('myAccessMngTab', 'Access Management', '/html/admin/authorityMng/accessManagement.html')"><i class="fa fa-circle-o text-green"></i> <span>Access Management</span></a></li>
				<li class="header text-yellow" style="font-size:14px;"><i class="fa fa-star text-yellow fa-fw"></i> Favorites</li>
	            <li class="header">
	                <input type="text" class="form-control" style="margin-bottom:5px;" ng-bean="markName">
	                <button type="button" class="btn btn-default btn-xs" ng-click="addtBookmark();"><i class="fa fa-star fa-fw text-yellow"></i> Bookmark</button>
	                <button type="button" class="btn btn-default btn-xs pull-right" ng-click="delBookmark();"><i class="fa fa-minus-square fa-fw text-red"></i> DEL</button>
	            </li>
	            <li class="header" style="color:#999;">
	              <div class="radio" ng-repeat="val in bookMarkList"><label><input type="radio" ng-bean="bookMark.markName" value="{{val.MARK_NAME}}"/><a ng-click="addTab(val.MENU_CODE_ID, val.MARK_NAME, val.MARK_URL)"><i class="fa fa-star text-yellow fa-fw"></i> {{val.MARK_NAME}}</a></label></div>
	            </li>
			  </ul>
			</section>
		</aside>
		// LNB

		CONTENTS
		<div class="content-wrapper">
			<section class="content">
				<div ui-view></div>
			</section>
		</div>
		// CONTENTS

		FOOTER
		<footer ng-controller="footerCtrl">
			alarm list
			<div class="main-footer2">
				<div ng-show="alarmList.length > 0" class="navbar-left">
					<ul class="box_h40">
						<li ng-repeat="al in alarmList">{{al.SUBJECT}} {{al.MESSAGE}} {{al.LOG_DATE}}</li>
					</ul>
				</div>
				<button ng-show="alarmList.length > 0" type="button" class="btn btn-warning btn-xs" style="margin:13px 10px;" ng-click="updateAlarm();"><i class="fa fa-check fa-fw"></i> OK</button>
			</div>
			// alarm list
			<div class="main-footer">
				<div class="pull-right hidden-xs">
					<b>Version</b> 1.0
				</div>
				<strong>Copyright © 2016 NHA.</strong> All rights reserved.
			</div>
		</footer>
		// FOOTER

	</div>
</body>
 -->
</html>
