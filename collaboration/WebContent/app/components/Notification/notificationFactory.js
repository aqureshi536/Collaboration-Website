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

	function rejectFriendRequest(){
		var deferred = $q.defer();
		$http.post(address+'reject/request/'+userId).
		then(function(response){
			deferred.resolve(response.data);
		},function(errResponse){
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
}])