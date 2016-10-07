app.factory('authInterceptor', function(){


	var factory={
		'request': function(config){
			config.headers = config.headers || {};	
			var value = setCredentials.getCredentials();
			/*var encodedString = ;	*/	
			console.log('Basic  for '+btoa(value));
			config.headers.Authorization = 'Basic '+btoa(value);
			return config;
		}
	};
	return factory;

});

app.factory('Credentials',['$rootScope', function($rootScope){
	$rootScope.username = '';
	$rootScope.password = '';
	return {
		setCredentials:function  (username,password) {
			console.log(username + password);
			$rootScope.username = username;
			$rootScope.password = password;	
			var value = $rootScope.username+":"+$rootScope.password;		
			console.log('Basic '+btoa(value));
		},
		getCredentials:getCredentials
	};


	function getCredentials() {
		console.log($rootScope.username+":"+$rootScope.password);		
		var value = $rootScope.username+":"+$rootScope.password;
		
		return 'Basic '+btoa(value);
	}
}]);


	/*self.setCredentials= function  (username,password) {
		console.log(username + password);


		self.username = username;
		self.password = password;
		var value = self.username+":"+self.password;
		console.log('Basic '+btoa(value));
	}*/
/*	return {
		'request': function(config){
			config.headers = config.headers || {};	
			var value = self.username+":"+self.password;
			var encodedString = ;		
			console.log('Basic '+btoa(value));
			config.headers.Authorization = 'Basic '+btoa(value);
			return config;
		},
		username:self.username,
		password:self.password		
	};*/
