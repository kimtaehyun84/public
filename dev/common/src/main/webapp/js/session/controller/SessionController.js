myApp.controller('sessionCtrl', [
    '$rootScope',
    '$scope',
    '$q',
    '$http',
    '$window',
    'CommonService',
    'SessionService',
     function ($rootScope, $scope, $q,$http, $window,  CommonService, SessionService, LoginService) {
        var contextPath = CommonService.getContextPath();

        $scope.logout = function(){
            var inputParam = {
                userId : SessionService.get("userId")
            }
            $scope.loadingView = true;
            CommonService.post('/logout', inputParam)
            .then(
                function onSuccess(response){
                    let data = response.data;
                    if(data.status="SUCCESS"){
                        SessionService.destroy();
                        $window.location.href = contextPath;
                    }
                    else{
                        alert(data.msg);
                    }
                },
                function onError(response){
                    let data = response.data;
                    console.log(data);
                    alert("Network Error Please Contact Manager");
                }
            )
        }

        $scope.getSession = function(key){
            return SessionService.get(key);
        }

        $scope.setSession = function(key, value){
            return SessionService.set(key, value);
        }

        $scope.destroySession = function(){
            return SessionService.destroy();
        }
    }
])