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
		templateUrl:'app/components/forum/forumView.html',
		controller:'forumController',
		controllerAs:'forumCtrl'
	}).
	when('/blog/view/:blogId',{
		templateUrl:'app/components/blog/singleBlogView.html',
		controller:'singleBlogController',
		controllerAs:'blogCommCtrl'
	}).when('/forum/view',{
		templateUrl:'app/components/forum/singleForumView.html',
		controller:'singleForumController',
		controllerAs:'sforumCtrl'
	}).
	when('/event/',{
		templateUrl:'app/components/event/eventView.html',
		
	})
	.otherwise({
		redirectTo:'/home/	'
	})
}])