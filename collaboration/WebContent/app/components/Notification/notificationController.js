app.controller('notificationController', ['notificationFactory','$rootScope', function(notificationFactory,$rootScope){
	
	var self =this;
	self.friendNotification  = [];

	(function () {
		self.process = true;
		notificationFactory.getAllFriendNotification($rootScope.client.userId).
		then(function(data){
			self.friendNotification = data;
			self.process = false;
		},
		function(errResponse){
			console.error(errResponse);
			self.process = false;
		});
	})();

	self.rejectFriendRequest = function(userId){
		userFactory.rejectFriendRequest(userId).
		then(function(data){
			console.log('rejected request');
		},function(errResponse){
			console.error(errResponse)
		})
	}



}])