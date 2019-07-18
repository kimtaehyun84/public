myApp.controller('loginCtrl', [
    '$rootScope',
    '$scope',
    '$q',
    '$http',
    '$window',
    'CommonService',
    'SessionService',
    'LoginService',
    function ($rootScope, $scope, $q,$http, $window,  CommonService, SessionService, LoginService) {
        var contextPath = CommonService.getContextPath();
        $scope.login = {};
        $scope.login.userId;
        $scope.login.userPwd;
        $scope.login.kind = 1;
        $scope.loadingView = false;

        if (LoginService.checkCookie()) {
            $scope.login.userId = LoginService.getCookie();
            $('#login.password').focus();
        } else {
            $('#login.userId').focus();
        }

        $scope.login = function () {
            if (!CommonService.validateLength($scope.login.userId)) {
                alert('Input User ID');
                $('#login.userId').focus();
                return;
            }
            if (!CommonService.validateLength($scope.login.userPwd)) {
                alert('Input Password');
                $('#login.password').focus();
                return;
            }
            var inputParam;
            inputParam = {
                userId: $scope.login.userId,
                userPwd: encryptSha256($scope.login.userPwd)
            };
            $scope.loadingView = true;
            CommonService.post('/login', inputParam)
            .then(
                function onSuccess(response) {
                    $scope.loadingView = false;
                    let data = response.data;

                    if (data.status == "SUCCESS") {
                        SessionService.set('userId', data.body.userId);
                        SessionService.set('userName', data.body.userName);
                        SessionService.set('userGroupNo', data.body.userGroupNo);
                        $window.location.href = contextPath + '/goMain';
                    } else {
                        alert(data.msg);
                        if (data.msg == "The password has expired\nPlease change password") {
                            $scope.changePassword.userId = $scope.login.userId;
                            $('#changePasswordPopOpen').trigger('click');
                        } else {
                            $scope.login.userId = '';
                            $scope.login.userPwd = '';
                            $scope.login.kind = inputParam.kind;
                            $('#login.userId').focus();
                        }
                    }
                },
                function onError(response) {
                    let data = response.data;
                    console.log(data);
                    $scope.login.userId = '';
                    $scope.login.userPwd = '';
                    $scope.loadingView = false;
                    $scope.login.kind = inputParam.kind;
                    $scope.loadingView = false;
                    alert("Network Error Please Contact Manager");
                    $('#login.userId').focus();
                }
            )
        }
        $scope.changePassword = function () {
            var inputParam = {
                userId: $scope.changePassword.userId,
                curPwd: encryptSha256($scope.changePassword.userPwd),
                newPwd: encryptSha256($scope.changePassword.newPwd)
            }

            CommonService.post('/user/changePassword', inputParam)
            .then(
                function onSuccess(response) {
                    let data = response.data;
                    console.log(data);
                    if (data.status == "SUCCESS") {
                        SessionService.set('userId', data.body.userId);
                        SessionService.set('userName', data.body.userName);
                        $window.location.href = contextPath + '/goMain';
                        $scope.loadingView = false;
                    } else {
                        alert(data.msg);
                        if (data.msg == "The password has expired\nPlease change password") {
                            $scope.changePassword.userId = $scope.login.userId;
                            $('#changePasswordPopOpen').trigger('click');
                        } else {
                            $scope.login.userId = '';
                            $scope.login.userPwd = '';
                            $scope.loadingView = false;
                            $scope.login.kind = inputParam.kind;
                            $('#login.userId').focus();
                        }
                    }
                },
                function onError(response) {
                    let data = response.data;
                    console.log(data);
                    $scope.login.userId = '';
                    $scope.login.userPwd = '';
                    $scope.loadingView = false;
                    $scope.login.kind = inputParam.kind;
                    $scope.loadingView = false;
                    alert("Network Error Please Contact Manager");
                    $('#login.userId').focus();
                }
            )
        }
    }
])