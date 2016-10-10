app.controller('userController', ['userFactory', function(userFactory){
	
	var self  = this;
	self.users = [];
	self.user= {};
	self.loading = false;
	self.process = false;
	self.failed = false;

getAllUsers();
	function getAllUsers(){
		self.loading = true;
		userFactory.getAllUsers().
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


}])