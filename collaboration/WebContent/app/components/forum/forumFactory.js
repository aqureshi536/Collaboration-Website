app.factory('forumFactory', ['$http','$q', function($http,$q){
	var address = 'http://localhost:8080/CollaborationWebsiteBackend/forums/';	
	var addressPost = 'http://localhost:8080/CollaborationWebsiteBackend/forumPost/';

	var factory = {
		fetchAllForums:fetchAllForums,
		createForum:createForum,
		updateForum:updateForum,
		deleteForum:deleteForum,
		getForum:getForum,
		postComment:postComment
	};

	return factory;

	function fetchAllForums(){

		var deferred = $q.defer();
		/*debugger;*/
		$http.get(address).
		then(function(response){
			console.log("Success");
			//console.log(response);
			deferred.resolve(response.data);
		},function(errResponse){
			console.error('error fetching forums');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	function createForum(forum){
		var deferred = $q.defer();
		$http.post(address,forum).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			console.error('error fetching forums');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}


	function updateForum(forum){
		var deferred = $q.defer();
		$http.put(address+forum.forumId,forum).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	function deleteForum(forumId){
		var deferred = $q.defer();
		
		//debugger;
		$http.delete(address+forumId).
		then(function(response){
			deferred.resolve(response.data);
			console.log(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	function getForum(forumId){
		var deferred = $q.defer();
		$http.get(address+forumId).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}




	function postComment(fp) {
		var deferred = $q.defer();
		//debugger;
		$http.post(addressPost,fp).
		then(function(response){
			deferred.resolve(response.data);
		},function (errResponse){
			deferred.reject(errResponse);
			console.error("Error posting post in forum");			
		});
		return deferred.promise;
	}

}]);