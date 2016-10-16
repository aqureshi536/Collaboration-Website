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


function uploadImage(data) {
	debugger;
	var deferred = $q.defer();
	


	/*$http({
		method:'POST',
		url:address+"upload/",
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		transformRequest: function(obj) {
			var str = [];
			for(var p in obj)
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			return str.join("&");
		},
		data:data

	})*/


	$http({
		method:'POST',
		url:address+"upload/",
		transformRequest : angular.identity,
		headers:{
			'Content-Type':undefined
		},
		data:data

	}).then(function(response){
		deferred.resolve(response.data);
		console.log('image uploaded');
	},
	function(errResponse){
		deferred.reject(errResponse)
	});
	return deferred.promise;
}


}])