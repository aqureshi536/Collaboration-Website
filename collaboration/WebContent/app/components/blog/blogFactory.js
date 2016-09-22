app.factory('blogFactory', ['$http','$q', function($http,$q){
	
	var address  = "http://localhost:8080/CollaborationWebsiteBackend/blogs/"
	var factory= {
		fetchAllBlogs:fetchAllBlogs,
		createBlog:createBlog
	};

	return factory;



	function fetchAllBlogs(){
		//Create a deferred object
		var deferred = $q.defer();
		$http.get(address)
		.then(function(response){
			deferred.resolve(response.data);
		},
		function(errResponse){
			console.error('Error fetching blogs');
			deferred.reject(errResponse);
		});
		return deferred.promise;	 
	}


	function createBlog(blog){
		var deferred = $q.defer();
		$http.post(address,blog)
		.then(
			function (response) {
                deferred.resolve(response.data);
            },
			function(errResponse){
				console.error('Error adding blog');
				deferred.reject(errResponse);
			});
		return deferred.promise;
	}


function updateBlog(blog,blogId){
	var deferred = $q.defer();
	$http.put(address+blogId,blog)
	.then(function(response){
		deferred.resolve(response.data);
	},function(errResponse){
		deferred.reject(errResponse);
	});
}


function deleteBlog(blogId){
	var deferred = $q.defer();
	$http.delete(address+blogId)
	.then(function(response){
		deferred.resolve(response.data);
	},function(errResponse){
		deferred.reject(errResponse);
	});
}

}]);