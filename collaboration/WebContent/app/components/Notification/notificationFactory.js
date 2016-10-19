app.factory('notificationFactory', ['$http','$q', function($http,$q){
	var address= 'http://localhost:8080/CollaborationWebsiteBackend/friends/user/notification/'


	return{
		getAllFriendNotification:getAllFriendNotification,
		rejectFriendRequest:rejectFriendRequest,
		acceptFriendRequest:acceptFriendRequest
	};



function getAllFriendNotification(userId){
	var deferred = $q.defer();
	$http.get(address+userId)
	.then(function (response) {
		deferred.resolve(response.data);
	},
	function (errResponse) {
		deferred.reject(errResponse);
	});
	return deferred.promise;
}


function acceptFriendRequest (user) {
		var deferred = $q.defer();
		$http.put(address+'accept',user).
	/*$http({
		method:"PUT",
		url:address+'accept',
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		transformRequest: function(obj) {
			var str = [];
			for(var p in obj)
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			return str.join("&");
		},
		data:user
	}).*/
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
}

	function rejectFriendRequest(user){
		var deferred = $q.defer();
		$http.put(address+'reject/request/',user).
	/*$http({
		method:"DELETE",
		url:address+'reject/request/',
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		transformRequest: function(obj) {
			var str = [];
			for(var p in obj)
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			return str.join("&");
		},
		data:user
	}).*/
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
}])