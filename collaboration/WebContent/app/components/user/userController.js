app.controller('userController', ['userFactory','$rootScope',function(userFactory,$rootScope){
	
	var self  = this;
	self.users = [];
	self.user= {};
	self.loading = false;
	self.process = false;
	self.failed = false;

getAllUsers();
	function getAllUsers(){
		debugger;
		self.loading = true;
		userFactory.getAllUsers($rootScope.client.userId).
		then(function(data){
			console.log(data);
			self.users = data;
			self.loading = false;
			self.failed = false;
		},function(errResponse){
			self.failed = true;
			console.error(errResponse);
			self.loading = false;
		});
	}


	self.sendFriendRequest = function (userId) {
		self.confirm = false;
		debugger;
		console.log(userId)
		var form = new FormData();
		form.append('user1',$rootScope.client.userId);
		form.append('user2',userId);

		var user  = {user1 :$rootScope.client.userId,user2 : userId };
		console.log(user);
		userFactory.sendFriendRequest(user).
		then(function(data){
			console.log(data);
			getAllUsers();
			self.confirm = true;
			self.error = false;
		},function(errResponse){
			console.error(errResponse);
			self.error = true;
		});
	}



}])