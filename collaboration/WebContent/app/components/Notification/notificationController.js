app.controller('notificationController', ['notificationFactory','$rootScope', function(notificationFactory,$rootScope){
	
	var self =this;
	self.friendNotification  = [];
	getAllFriendNotification();
	function getAllFriendNotification() {
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
	};

	self.rejectFriendRequest = function(userId){
		var value = {user1:userId,friendUser:$rootScope.client.userId};
		/*var form = new FormData();
		form.append('user1',userId);
		form.append('user2',$rootScope.client.userId);*/
		console.log(value);
		notificationFactory.rejectFriendRequest(value).
		then(function(data){
			console.log('rejected request');
			getAllFriendNotification();
		},function(errResponse){
			console.error(errResponse)
		})
	}

	self.acceptFriendRequest = function(userId){
		var value = {"user1":userId,"friendUser":$rootScope.client.userId};
		console.log(value);
		notificationFactory.acceptFriendRequest(value).
		then(function(data){
			console.log('accepted request');
			getAllFriendNotification();
		},function(errResponse){
			console.error(errResponse)
		})
	}



}])