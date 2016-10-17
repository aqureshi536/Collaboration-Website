app.controller('notificationController', ['notificationFactory','$rootScope', function(notificationFactory,$rootScope){
	
	var self =this;
	self.friendNotification  = [];

	(function () {
		self.process = true;
		notificationFactory.getAllFriendNotification($rootScope.client.userId).
		then(function(data){
			self.friendNotifications = data;
			self.process = false;
		},
		function(errResponse){
			console.error(errResponse);
			self.process = false;
		});
	})();

	self.rejectFriendRequest = function(userId){
		var value = {user1:$rootScope.client.userId,user2:userId};
		console.log(value)
		userFactory.rejectFriendRequest(value).
		then(function(data){
			console.log('rejected request');
		},function(errResponse){
			console.error(errResponse)
		})
	}



}])