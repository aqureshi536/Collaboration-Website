app.controller('loginController', ['loginFactory','$rootScope','$location', function(loginFactory,$rootScope,$location){
	var self = this;
	self.user={registerEmail:'',registerName:'',registerRole:'',registerGender:'',registerPassword:''};
	self.error=false;
	self.client = {email:'',password:''};
	//$rootScope.customUser= {data:'',status:''};

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


	/*######################  For registering user ###################################*/



	self.login = function(){
		debugger;
		//var form = new FormData();
	    //authInterceptor.username = self.client.email;
		//authInterceptor.password = self.client.password;
		//form.append('email',self.client.email);
		//form.append('password',self.client.password);
		//Credentials.setCredentials(self.client.email,self.client.password);

		/*console.log(form);*/
		//loginUser(form);
		loginUser(self.client);
}

function loginUser(client){
	debugger;
	/*var values = {email:email,password:password}*/
	loginFactory.loginUser(client).
	then(function(data){
		console.log("login successful");
		console.log(data);
		//$rootScope.customUser.data = data;
		//$rootScope.customUser.status= true;
		//$location.path('/blog/');
	},function(errResponse){
		console.error(errResponse.status);
	});
	resetLoginFields();
}



self.logout=function(){
//$rootScope.customUser = '';
//$rootScope.customUser.status= false;
};

	/*function loginUser(form){
		loginFactory.loginUser(form).
		then(function(data){
			console.log("login successful");
			//$location.path('/blog/');
		},function(errResponse){
			console.error(errResponse.status);
		});
		resetLoginFields();
	}*/



	/*###################### Basic authenctication   ############*/




	/*################  Code for oauth2 authentication  ########################*/

/*self.login = function(){
	$rootScope.username = self.client.email;
	$rootScope.password = self.client.password;
	console.log($rootScope);
	loginFactory.firstRequest().
	then(function(data){
		console.log(data);
		$rootScope.token = data;
	},
	function(errResponse){
		console.log('error getting token');
	});
	resetLoginFields();
}*/



/*#############################################################################*/




function reset(){
	self.user={registerEmail:'',registerName:'',registerRole:'',registerGender:'',registerPassword:''};
};

function resetLoginFields(){
	self.client={email:'',password:''};
};

}])