myApp.service('LoginService', [
    '$cookies',
    '$cookieStore',
    function($cookies, $cookieStore) {

        return {
            checkCookie : function () {
                if ($cookieStore.get('userId') != null && $cookieStore.get('userId') != null) {
                    return true;
                } else {
                    return false;
                }
            },

            getCookie : function () {
                return $cookieStore.get('userId');
            },

            setCookie : function (userId) {
                $cookieStore.put('userId', userId);
            }
        }
    }
])