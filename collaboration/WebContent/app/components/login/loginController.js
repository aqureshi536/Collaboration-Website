app.controller('loginController', ['loginFactory','authenticationFactory','$rootScope','$location','$scope','$localStorage',function(loginFactory,authenticationFactory,$rootScope,$location,$scope,$localStorage){
	var self = this;
	self.user={registerEmail:'',registerName:'',registerRole:'',registerGender:'',registerPassword:'',conPass:''};
	self.error=false;
	self.client = {email:'',password:''};
	self.loginError=false;
	self.failed = false;

	//$rootScope.customUser= {data:'',status:''};
	/*$scope.$storage= $localStorage.$default({});

	(function () {
		$scope.$storage.$reset();
	})();
	$scope.$storage= $localStorage.$default({status:''});

	*/

	$scope.$storage= $localStorage.$default(JSON.parse(window.localStorage.getItem("ngStorage-client")));
	
	self.registerUser = function(){
		self.failed= false;
		self.loginError = false;
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

	function initController() {
            // reset login status
            authenticationFactory.clearCredentials();
        };


        initController();


        /*Method which log in the user*/

        self.login = function(){
        	self.process=true;
        	debugger;
		//debugger;
		//var form = new FormData();
	    //authInterceptor.username = self.client.email;
		//authInterceptor.password = self.client.password;
		//form.append('email',self.client.email);
		//form.append('password',self.client.password);
		//Credentials.setCredentials(self.client.email,self.client.password);

		/*console.log(form);*/
		//loginUser(form);

		authenticationFactory.login(self.client.email,self.client.password,function(data,error){
			if(data!=null){
				authenticationFactory.setCredentials(self.client.email,self.client.password);
				

				$rootScope.client = data;
				$scope.$storage.client = data;
				console.log($rootScope.client);
				self.loginError=error;
				self.process = false;
				self.failed = false;
				$location.path("/");
			}
			else{
				self.process = false;
				if(error==404){
					self.loginError=error;
				}
				else{
					self.failed = true;
				}
			}
		});
		resetLoginFields();
	}


	/*method to logout user*/

	self.logout=function(){
		//debugger;
		loginFactory.logout($rootScope.client.userId);
		$localStorage.$reset();
		delete $rootScope.client;
		$location.path("/");
 /*document.cookie = 'globals';


		function createCookie(name,value,days) {
			if (days) {
				var date = new Date();
				date.setTime(date.getTime()+(days*24*60*60*1000));
				var expires = "; expires="+date.toGMTString();
			}
			else var expires = "";
			document.cookie = name+"="+value+expires+"; path=/";
		}


		function eraseCookie(name) {
			createCookie(name,"",-1);
		}


		var cookies = document.cookie.split(";");
		for (var i = 0; i < cookies.length; i++){
			eraseCookie(cookies[i].split("=")[0]);
		}*/

	}


	function reset(){
		self.user={registerEmail:'',registerName:'',registerRole:'',registerGender:'',registerPassword:'',conPass:''};
	};

	function resetLoginFields(){
		self.client={email:'',password:''};
	};

}]);

//function loginUser(client){
	//debugger;
	/*var values = {email:email,password:password}*/
//	loginFactory.loginUser(client).
//	then(function(data){
//		console.log("login successful");
//		console.log(data);
		//$rootScope.customUser.data = data;
		//$rootScope.customUser.status= true;
		//$location.path('/blog/');

		
//		$scope.$storage.client = data;
		//$scope.$storage.status = true;

		//console.log($scope.$storage.client);
		//delete $scope.$storage.data;
//	},function(errResponse){
//		console.error(errResponse.status);
//	});
//	resetLoginFields();
//}




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




