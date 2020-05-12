myApp.factory('CommonService',
    function ($rootScope, $http) {
        var contextPath = sessionStorage.getItem("contextPath");

        var result = {

            getContextPath: function () {
                return contextPath;
            },
            validateLength: function (value) {
                return (value == null || value == '' || value.length == 0) ? false : true;

            },
            getPattern: function (type) {
                switch (type) {
                    case numeric :
                        return "/\d/";
                        break;
                    case alphabet:
                        return "/[a-zA-Z]+/";
                        break;
                    case alphanumeric:
                        return "/\w/";
                        break;
                    case alphanumric & whitespace:
                        return "/[\w\s]+/";
                }
            },
            post: function (url, inputParam) {
                console.log(inputParam);
                var plainParam = {
                    isEncrypt: 0,
                    param: JSON.stringify(inputParam)
                }
                return $http.post(contextPath + url, plainParam);
            },

            securePost: function (url, inputParam) {
                console.log(inputParam);
                var config = {
                    method: 'POST',
                    url: contextPath + url,
                    data: encryptByRSA(JSON.stringify(inputParam)),
                    headers: {
                        isEncrypted: true
                    }

                }
                return $http(config);
            }
        }
        return result;
    }
);
