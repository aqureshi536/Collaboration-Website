app.config(['$routeProvider','$locationProvider',function($routeProvider,$locationProvider) {
	$routeProvider.
	when('/',{
		templateUrl:'./app/components/pages/homeView.html'
		/*controller:HomeController,
		controllerAs:homeCtrl*/
	}).
	when('/blog/',{
		templateUrl:'./app/components/blog/blogView.html'
		/*controller:BlogController,
		controllerAs:blgCtrl*/
	}).
	when('/forum/',{
		templateUrl:'app/components/forum/forumView.html'
		/*controller:ForumController,
		controllerAs:frmCtrl*/
	})
	.otherwise({
		redirectTo:'/home/	'
	})
}])