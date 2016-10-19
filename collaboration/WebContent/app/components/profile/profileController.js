app.controller('profileController', ['profileFactory','$routeParams','$rootScope', function(profileFactory,$routeParams,$rootScope){
	var self =this;
	self.user = {};	
	self.error = false;
	self.status = {userStatus:''};
	self.loading=false;
	var random = (new Date()).toString();
	//	self.image= self.image + "?cb=" + random;
	self.image='./assets/images/users/'+$rootScope.client.userId+'.png'+ "?cb=" + random;
	

	function getUser(){
		self.dataLoaded=false;
		var userId = $routeParams.userId;
		profileFactory.getUser(userId).
		then(function (data) {
			console.log(data);
			self.user = data;
			self.dataLoaded=true;
		},
		function(errResponse){
			console.error(errResponse);
			self.error = true;
			self.dataLoaded=true;
		});
		
	}

	getUser();


	self.updateStatus= function () {
		self.loading= true;
		//debugger;
		profileFactory.updateStatus(self.user.userId,self.status).
		then(function(data){
			console.log(data);
			self.user.userStatus = data.userStatus;
			self.loading= false;
		},function(errResponse){
			console.error(errResponse);
			self.loading= false;
		});
		reset();
		
	}


	self.uploadImage = function () {
		self.loading= true;
		debugger;
		var form = new FormData();
		var file = self.userImage;
		console.log(file);
		var user = {userId:self.user.userId};
		form.append('image',file);
		form.append('userId',angular.toJson(user,true));
	//var data ={userId:self.user.userId,image:self.userImage};
	
	//form.append('userId',self.user.userId);
	
	
	profileFactory.uploadImage(form).
	then(function(event,data){
		/*var newImage = new Image();
		document.getElementById("userImage").src = newImage.src;
		newImage = new Image();*/
		//console.log(image);
		var random = (new Date()).toString();
		self.image= self.image + "?cb=" + random;
		
		/*if(!$scope.$$phase){
			$scope.$apply (function(){
				self.image = self.image;
			});
		}*/
		self.loading = false;
	},function(errResponse){
		console.error(errResponse);
		self.loading = false;
	});
}

function reset () {
	self.status = {userStatus:''};
}

}])