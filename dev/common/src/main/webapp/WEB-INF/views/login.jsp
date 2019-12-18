<%--
  Created by IntelliJ IDEA.
  User: Taehyun Kim
  Date: 2019-04-08
  Time: 오전 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="now" value="<%=new java.util.Date()%>"/>
<fmt:formatDate var="datetime" pattern="yyyyMMDDHHmmss" value="${now}"/>
<%
    String publicExponent = request.getAttribute("publicExponent").toString();
    String publicModulus = request.getAttribute("publicModulus").toString();

%>

<!DOCTYPE html>
<html>
<head>
    <!-- 현재 webpage의 context path 자동 설정 -->
    <script type="text/javascript" charset="utf-8">
        sessionStorage.setItem("contextPath", "${pageContext.request.contextPath}");
        sessionStorage.setItem("publicModulus", "${publicModulus}");
        sessionStorage.setItem("publicExponent","${publicExponent}");
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/session/service/SessionService.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/CommonService.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath()%>/js/login/service/LoginService.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/login/controller/LoginController.js"></script>

    <!-- utill -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/service/UtilService.js"></script>

    <!-- Util beans -->
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/jsbn.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/prng4.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/rng.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/rsa/rsa.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/util/bean/hashing/sha256.js"></script>

    <style>
        body.login-page.modal-open .modal-backdrop.fade.in {
            z-index: 11;
        }
    </style>
</head>
<body ng-app="myApp" class="hold-transition login-page" ng-controller="loginCtrl">
    <div class="login-box">
        <div class="login-box-head">
            <div class="login-logo">
                <%--<img src="../images/MoniPortal_Applications.png" alt="MoniSLA" width="240">--%>
                <span style="color: #fff;">MoniSLA</span>
                <span class="subtitle">ATM total management system</span>
            </div>
        </div>
        <div class="login-box-body">
            <h3 class="this-mg mg-t5">
                <div class="login-logo">
                    <img src="<%=request.getContextPath()%>/images/SBI_logo.png" width="100" alt="">
                </div>
            </h3>
            <div class="input-user-option mg-b10">
                <input id="login.userId" type="text" class="user-control" placeholder="USER ID" ng-model="login.userId">
                <span class="fa fa-user user-control-icon"></span>
            </div>
            <div class="input-user-option">
                <input id="login.password" type="password" class="user-control" placeholder="Password" ng-model="login.userPwd" ui-keypress="{enter: 'login()'}">
                <span class="fa fa-lock user-control-icon"></span>
            </div>
            <div class="login-spacing"></div>
            <div class="row">
                <div class="col-xs-12 col-sm-12">
                    <button type="submit" class="btn bg-blue2 login-btn" ng-click="login()"><span>LOGIN</span>
                    </button>
                </div>
            </div>
            <p class="login-box-msg"><span>Copyright © HYOSUNG TNS.</span> All rights reserved.</p>
        </div>
    </div>

    <!-- loading -->

     <div class="loading" ng-show="loadingView">
        <div class="loadingCss" ng-show="loadingView">
            <div class="loader"></div>
            <span class="loadingtxt">Loading...</span>
        </div>
    </div>

    <!-- //loading -->

    <button type="button" id="changePasswordPopOpen" data-toggle="modal" data-target="#changePasswordPop" hidden="true"></button>

    <!-- changePassword pop modal -->
    <%--
    <div id="changePasswordPop" class="modal fade pop_watch_tech" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="top: 20%; width: 600px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" data-dismiss="modal" class="close">×</button>
                    <h4 class="modal-title" id="exampleModalLabel"> Change password</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="Branch" class="col-sm-3 control-label-popup">Current Password</label>
                            <div class="col-sm-9">
                                <input id="changePwOldPw" ng-init="oldPw='';" type="password" maxlength="20" ng-hide="showpassword" class="form-control" ng-model="oldPw">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Branch" class="col-sm-3 control-label-popup">New Password<span class="asterisk"></span></label>
                            <div class="col-sm-9">
                                <input id="chagePwNewPw" ng-init="newPw='';" type="password" maxlength="20" ng-hide="showpassword" class="form-control" ng-model="newPw">
                            </div>
                        </div>
                        <div class="row">
                            <label for="" class="col-sm-3"></label>
                            <div class="col-sm-9">
                                <span class="pwmessage">Password should be over 8, be mixed letter and number and include over 1 digit capital letter</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="Branch" class="col-sm-3 control-label-popup">Confirm Password<span class="asterisk"></span></label>
                            <div class="col-sm-9">
                                <input id="changePwConPw" ng-init="conPw='';" type="password" maxlength="20" ng-hide="showpassword" class="form-control" ng-model="conPw">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="changePasswordPopClose" class="btn btn-danger btn-sm" data-dismiss="modal"><i class="fa fa-close fa-1_5x"></i> close </button>
                    <button type="button" class="btn btn-info btn-popup btn-sm" ng-click="changePw();"><i class="fa fa-save fa-1_5x"></i> Save </button>
                </div>
            </div>
        </div>
    </div>
    --%>
    <!--//  changePassword pop modal -->

</body>


</html>
