app.factory('adminFactory', ['$http','$q', function($http,$q){
	var domain = 'http://localhost:8080/CollaborationWebsiteBackend/';
 
	var blogAddress =  domain+'manage/blog/';
	var forumAddress = domain+'manage/forum/';

	return {
		getUnapprovedBlogs:getUnapprovedBlogs,
		getUnapprovedForums:getUnapprovedForums,
		approveBlog:approveBlog,
		disapproveBlog:disapproveBlog,
		approveForum:approveForum,
		disapproveForum:disapproveForum
	};

function getUnapprovedBlogs(){
	var deferred = $q.defer();
		$http.get(domain+'admin/blogs/').then(function (response) {
			deferred.resolve(response.data);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
}



function getUnapprovedForums(){
	var deferred = $q.defer();
		$http.get(domain+'admin/forums/').then(function (response) {
			deferred.resolve(response.data);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
}


	function approveBlog (blogId) {
		var deferred = $q.defer();
		$http.put(blogAddress+'approve/'+blogId).then(function (response) {
			deferred.resolve(response.data);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	function disapproveBlog(blogId){
		var deferred = $q.defer();
		$http.put(blogAddress+'disapprove/'+blogId).then(function (response) {
			deferred.resolve(response.data);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}


	function approveForum(forumId){
		var deferred = $q.defer();
		$http.put(forumAddress+'approve/'+forumId).then(function (response) {
			deferred.resolve(response.data);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}


	function disapproveForum(forumId){
		var deferred = $q.defer();
		$http.put(forumAddress+'disapprove/'+forumId).then(function (response) {
			deferred.resolve(response.data);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}



}])