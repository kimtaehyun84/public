myApp.service('SessionService', function() {
    return {
        set:function(key, value) {
            return localStorage.setItem(key, value);
        },
        get:function(key) {
            return localStorage.getItem(key);
        },
        destroy:function() {
            delete localStorage.userId;
            delete localStorage.userName;
        }
    }
});