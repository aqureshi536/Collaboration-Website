app.config(['$routeProvider','$locationProvider',function($routeProvider,$locationProvider) {
	$routeProvider.
	when('/',{
		templateUrl:'./app/components/pages/homeView.html'
		/*controller:HomeController,
		controllerAs:homeCtrl*/
	}).
	when('/blog/',{
		templateUrl:'./app/components/blog/blogView.html',
		controller:'blogController',
		controllerAs:'blogCtrl'
	}).
	when('/forum/',{
		templateUrl:'./app/components/forum/forumView.html',
		controller:'forumController',
		controllerAs:'forumCtrl'
	}).
	when('/blog/view/:blogId',{
		templateUrl:'./app/components/blog/singleBlogView.html',
		controller:'singleBlogController',
		controllerAs:'blogCommCtrl'
	}).when('/forum/view',{
		templateUrl:'./app/components/forum/singleForumView.html',
		controller:'singleForumController',
		controllerAs:'sforumCtrl'
	}).
	when('/login',{
		templateUrl:'./app/components/login/login.html',
		controller:'loginController',
		controllerAs:'loginCtrl'
	}).
	when('/event/',{
		templateUrl:'./app/components/event/eventView.html',
		controller:'eventController',
		controllerAs:'eventCtrl'
		
	}).
	when('/chat/',{
		templateUrl:'./app/components/chat/chatView.html',
		controller:'chatController',
		controllerAs:'chatCtrl'
	}).
	when('/users/',{
		templateUrl:'./app/components/user/usersView.html',
		controller:'userController',
		controllerAs:'userCtrl'
	}).
	when('/user/edit/:userId',{
		templateUrl:'./app/components/profile/profileView.html',
		controller:'profileController',
		controllerAs:'profileCtrl'
	}).
	when('/admin/dashboard',{
		templateUrl:'./app/components/admin/adminView.html'
	}).
	when('/user/notification/',{
		templateUrl:'./app/components/notification/notificationView.html',
		controller:'notificationController',
		controllerAs:'notificationCtrl'
	}).
	when('/user/friends/',{
		templateUrl:'./app/components/friend/friendView.html',
		controller:'friendController',
		controllerAs:'friendCtrl'
	})
	.otherwise({
		redirectTo:'/'
	})
}])