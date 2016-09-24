app.config(['$routeProvider','$locationProvider',function($routeProvider,$locationProvider) {
	$routeProvider.
	when('/',{
		templateUrl:'app/components/pages/homeView.html'
		/*controller:HomeController,
		controllerAs:homeCtrl*/
	}).
	when('/blog/',{
		templateUrl:'app/components/blog/blogView.html',
		controller:'blogController',
		controllerAs:'blogCtrl'
	}).
	when('/forum/',{
		templateUrl:'app/components/forum/forumView.html'
		/*controller:ForumController,
		controllerAs:frmCtrl*/
	}).
	when('/blog/view',{
		templateUrl:'app/components/blog/singleBlogView.html',
		controller:'singleBlogController',
		controllerAs:'blogCommCtrl'
	})
	.otherwise({
		redirectTo:'/home/	'
	})
}])