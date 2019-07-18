myApp.factory('CommonService',
    function($rootScope, $http) {
        var contextPath = sessionStorage.getItem("contextPath");

        var result = {

            getContextPath: function () {
                return contextPath;
            },
            validateLength: function (value) {
                return (value == null || value == '' || value.length == 0 )? false : true;

            },
            getPattern : function(type){
              switch(type){
                  case numeric :
                      return "/\d/";
                      break;
                  case alphabet:
                      return "/[a-zA-Z]+/";
                      break;
                  case alphanumeric:
                      return "/\w/";
                      break;
                  case alphanumric&whitespace:
                      return "/[\w\s]+/";
              }
            },
            post: function (url, inputParam) {
                return $http.post(contextPath + url, inputParam);
            }
        }
        return result;
    }
);