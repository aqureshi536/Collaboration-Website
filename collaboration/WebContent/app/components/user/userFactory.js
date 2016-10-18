app.factory('userFactory', ['$http','$q', function($http,$q){
	var address  =  'http://localhost:8080/CollaborationWebsiteBackend/friends/user/';

	return {
		getAllUsers:getAllUsers,
		sendFriendRequest:sendFriendRequest
		//rejectFriendRequest:rejectFriendRequest
	};


	function getAllUsers(userId){
		debugger;
		var deferred = $q.defer();
		$http.get(address+userId).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}



	function sendFriendRequest(friend){
		debugger;
		console.log(friend);
		var deferred = $q.defer();
		$http({
			method:'POST',
			url:address+'send/request/',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			transformRequest: function(obj) {
				var str = [];
				for(var p in obj)
					str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				return str.join("&");
			},
			data:friend 
		}).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}


	

	
}])