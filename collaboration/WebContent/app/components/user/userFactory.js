app.factory('userFactory', ['$http','$q', function($http,$q){
	var address  =  'http://localhost:8080/CollaborationWebsiteBackend/users/';

	return {
		getAllUsers:getAllUsers	
	};


	function getAllUsers(){
		var deferred = $q.defer();
		$http.get(address).
		then(function(response){
			deferred.resolve(response);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
}])