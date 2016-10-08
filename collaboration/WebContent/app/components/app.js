/// <reference path="resources/js/angular.js"/>
var app = angular.module('module',["ngRoute","ngStorage","ngCookies"]);

/*app.config(['$httpProvider',function($httpProvider) {
	$httpProvider.interceptors.push('authInterceptor')
}])*/

app.run(['$rootScope', '$location', '$cookieStore','$http',function($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
        $rootScope.client = JSON.parse(window.localStorage.getItem("ngStorage-client"));
        console.log($rootScope.client);

    }]);

