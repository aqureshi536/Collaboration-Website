app.factory('profileFactory', ['$http','$q', function($http,$q){

	var address = 'http://localhost:8080/CollaborationWebsiteBackend/user/'

	return {
		getUser:getUser,
		updateStatus:updateStatus,
		uploadImage:uploadImage
	};


	function getUser(userId){
		var deferred = $q.defer();
		$http.get(address+userId).
		then(function (response) {
			deferred.resolve(response.data);
		},
		function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}


	function updateStatus(userId,status){
	//debugger;
	var deferred = $q.defer();
	$http.put(address+userId,status).
	then(function(response){
		deferred.resolve(response.data);
	},function(errResponse){
		deferred.reject(errResponse);
	});
	return deferred.promise;
}


function uploadImage(formData,userId) {
	debugger;
	var deferred = $q.defer();
	$http.post(address+"upload/"+userId,formData,{
		transformRequest : angular.identity,
		headers:{
			'Content-Type':undefined
		}

}).then(function(response){
	deferred.resolve(response.data);
},
function(errResponse){
	deferred.reject(errResponse)
});
return deferred.promise;
}


}])