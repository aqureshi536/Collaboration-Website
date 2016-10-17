app.factory('notificationFactory', ['$http','$q', function($http,$q){
	var address= 'http://localhost:8080/CollaborationWebsiteBackend/friends/user/notification/'


	return{
		getAllFriendNotification:getAllFriendNotification,
		rejectFriendRequest:rejectFriendRequest
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

	function rejectFriendRequest(user){
		var deferred = $q.defer();
	//	$http.post(address+'reject/request/',user).
	$http({
		method:"POST",
		url:address+'reject/request/',
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		transformRequest: function(obj) {
			var str = [];
			for(var p in obj)
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			return str.join("&");
		},
		data:user
	}).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
}])