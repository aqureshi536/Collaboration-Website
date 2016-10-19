app.factory('friendFactory', ['$http','$q', function($http,$q){
	var address = 'http://localhost:8080/CollaborationWebsiteBackend/friends/';
	return {
		fetchAllFriends:fetchAllFriends
	};

	function fetchAllFriends(userId){
		var deferred = $q.defer();
		$http.get(address+userId).
		then(function(response)
		{
			deferred.resolve(response.data);
		},
		function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

}])