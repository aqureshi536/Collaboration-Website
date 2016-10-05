app.factory('loginFactory', ['$http','$q', function($http,$q){
	var address = 'http://localhost:8080/CollaborationWebsiteBackend/register';
	var addressForLogin = 'http://localhost:8080/CollaborationWebsiteBackend/j_spring_security_check';
	return {
		registerUser:registerUser,
		loginUser:loginUser
	};

	function registerUser(user){
		var deferred = $q.defer();
		$http.post(address,user).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}



	function loginUser(user){
		var deferred  = $q.defer();
		$http.post(addressForLogin,user).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

}]);

