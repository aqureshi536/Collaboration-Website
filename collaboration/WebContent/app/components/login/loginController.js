app.controller('loginController', ['loginFactory', function(loginFactory){
	var self = this;
	self.user={registerEmail:'',registerName:'',registerRole:'',registerGender:'',registerPassword:''};
	self.error=false;

	self.registerUser = function(){
		self.process=true;
		loginFactory.registerUser(self.user).
		then(function(data){	
			self.error=false;
			self.userexist=false;			
			console.log('registered user');	
			self.registered=true;
			self.process=false;
		},function(errResponse){
			console.log('error registering user');
			self.registered=false;
			if(errResponse.status==409){
				self.userexist=true;
				self.error=false;
			}
			else{
				self.error=true;
				self.userexist=false;
			}
			self.process=false;
			
		});
		reset();
	}


	function reset(){
		self.user={registerEmail:'',registerName:'',registerRole:'',registerGender:'',registerPassword:''};
	};


}])