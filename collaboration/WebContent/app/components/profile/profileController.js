app.controller('profileController', ['profileFactory','$routeParams','$rootScope', function(profileFactory,$routeParams,$rootScope){
	var self =this;
	self.user = {};	
	self.error = false;
	self.status = {userStatus:''};
	self.loading=false;
	self.dataLoaded=true;
	

	function getUser(){
		self.dataLoaded=false;
		var userId = $routeParams.userId;
		profileFactory.getUser(userId).
		then(function (data) {
			console.log(data);
			self.user = data;
		},
		function(errResponse){
			console.error(errResponse);
			self.error = true;
		});
		self.dataLoaded=true;
	}

	getUser();


	self.updateStatus= function () {
		self.loading= true;
		//debugger;
		profileFactory.updateStatus(self.user.userId,self.status).
		then(function(data){
			console.log(data);
			self.user.userStatus = data.userStatus;
		},function(errResponse){
			console.error(errResponse);
		});
		reset();
		self.loading= false;
	}


	self.uploadImage = function () {
		debugger;
		var form = new FormData();
		var file = self.userImage;
		console.log(file);
	form.append('image',file);
	
	//form.append('userId',self.user.userId);
	
	
	profileFactory.uploadImage(form,self.user.userId).
	then(function(data){
		
	},function(errResponse){
		console.error(errResponse);
	});
}

function reset () {
	self.status = {userStatus:''};
}

}])