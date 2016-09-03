/// <reference path="././assets/js/angular.js" />
/// <reference path="././assets/js/angular-route.js" />

var app = angular.module('commonApp', []).directive("aheader", function() {
	return {
		restrict : 'A',
		replace : true,
		templateUrl : './app/shared/navbar.html',
		/*link : function() {
			alert("hello")
		}*/
	}
}).directive("asidebar", function() {
	return {
		restrict : 'A',
		replace : true,
		templateUrl : './app/shared/sidebar.html',
		/*link : function() {
			alert("sidebar")
		}*/
	}
});
