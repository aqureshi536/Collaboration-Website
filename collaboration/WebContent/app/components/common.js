/// <reference path="././assets/js/angular.js" />
/// <reference path="././assets/js/angular-route.js" />

var app = angular.module('commonApp', []);

app.directive("aheader", function() {
	return {
		restrict : 'A',
		replace : true,
		templateUrl : './app/shared/navbar.html',
		/*link : function() {
			alert("hello")
		}*/
	}
});
app.directive("asidebar", function() {
	return {
		restrict : 'A',
		replace : true,
		templateUrl : './app/shared/sidebar.html',
		/*link : function() {
			alert("sidebar")
		}*/
	}
});
app.directive('forumCard', function(){
	// Runs during compile
	return {		
		 restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
		 replace: true,
		 /*scope:{
		 	page:'='
		 }*/
		 templateUrl: './app/components/forum/forumPost.html'
	}
});