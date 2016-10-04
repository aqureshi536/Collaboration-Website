app.factory('blogFactory', ['$http','$q', '$log', function($http,$q, $log){
	
	var address  = "http://localhost:8080/CollaborationWebsiteBackend/blogs/";
	var addressForComment = "http://localhost:8080/CollaborationWebsiteBackend/blogcomments/";
	var factory= {
		fetchAllBlogs:fetchAllBlogs,
		createBlog:createBlog,
		updateBlog:updateBlog,
		deleteBlog:deleteBlog,
		getBlogComments:getBlogComments,
		createBlogComment:createBlogComment,
		getBlog:getBlog
	};

	return factory;


	/*##################    Blog Section  #############################*/


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


	function getBlog(blogId){

		var deferred = $q.defer();
		debugger;
		$log.info($q);
		$http.get(address+blogId)
		.then(function(response){
			$log.info(response);
			deferred.resolve(response.data);
		},
		function(errResponse){
			console.error('Error getting blog');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	function createBlog(blog){
		
		var deferred = $q.defer();
		debugger;

		$http.post(address,blog,{
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).then(
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

		return deferred.promise;
	}


	function deleteBlog(blogId){
		var deferred = $q.defer();
		$http.delete(address+blogId)
		.then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}



	/*############  blog comment section    ######################################################################*/


	/*For fetching all blog comments   */
	function getBlogComments(blogId){
		var deferred = $q.defer();
		$http.get(addressForComment+blogId).
		then(function(response){
			deferred.resolve(response.data);
			console.log('fetched blogs comments');
		},function(errResponse){
			deferred.reject(errResponse);
			console.error('error fetching blogcomments');
		});
	}


	/*==================================================================================================*/



	/*for commenting on a blog*/

	function createBlogComment(bc){
		var deferred = $q.defer();
		$http.post(addressForComment,bc).
		then(function(response){
			deferred.resolve(response.data);
			console.log('created blog comment');
		},function(errResponse){
			deferred.reject(errResponse);
			console.error('error creating blogcomments');
		});
		return deferred.promise;
	}




}]);