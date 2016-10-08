/// <reference path="resources/js/angular.js"/>
var app = angular.module('module',[]);

/*app.config(['$httpProvider',function($httpProvider){
	$httpProvider.interceptors.push('authInterceptor');
}]);*/

/*app.run(['$http','$rootScope',function($http,$rootScope){
	var value = $rootScope.username+":"+$rootScope.password;		
	$http.defaults.headers.common['Authorization'] ='Basic '+btoa(value);
	console.log(value);
}]);

app.config(['$httpProvider','$rootScope',function($httpProvider,$rootScope){	
	var value = $rootScope.username+":"+$rootScope.password;		
	$http.defaults.headers.common['Authorization'] ='Basic '+btoa(value);
}]);*/