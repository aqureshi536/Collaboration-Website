app.factory('loginFactory', ['$http','$q', function($http,$q){
var address = 'http://localhost:8080/CollaborationWebsiteBackend/register'
return {
	registerUser:registerUser
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

}])

