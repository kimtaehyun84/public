myApp.controller('loginCtrl', [
    '$rootScope',
    '$scope',
    '$q',
    '$http',
    '$window',
    'CommonService',
    'SessionService',
    'MenuService',
    function ($rootScope, $scope, $q,$http, $window,  CommonService, SessionService, MenuService ) {
       var contextPath = CommonService.getContextPath();
       $scope.loadingView = false;
       $scope.menuList;



       $scope.getMenuData = function(){
           $scope.loadingView = true;
           var userId = SessionService.get("userId");
           var userGroupNo = SessionService.get("userGroupNo");

           if(userId == null || userGroupNo == null){
               $window.location.href= contextPath + '/goMain';
           }

           var inputParam;
           inputParam = {
               userId : userId,
               userGroupNo : userGroupNo
           }
           CommonService.post('/menu/getMenuList', inputParam)
               .then(
                   function onSuccess(response){
                       $scope.loadingView = false;
                       let data = response.data;
                       if(data.status == "SUCCESS"){
                           $scope.menuList = data.body;
                       }
                       else{
                           alert(data.msg);
                       }

                   },
                   function onError(response){
                       let data = response.data;
                       $scope.loadingView = false;
                       alert("Network Error Please Contact Manager");
                   }
               )
       }


    }
])