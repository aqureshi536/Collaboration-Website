app.factory('blogFactory', ['$http','$q', function($http,$q){
	
	var address  = "http://localhost:8080/CollaborationWebsiteBackend/blogs/"
	var factory= {
		fetchAllBlogs:fetchAllBlogs
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
	};
}])