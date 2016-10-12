app.factory('loginFactory', ['$http','$q','$rootScope',function($http,$q,$rootScope){
	var address = 'http://localhost:8080/CollaborationWebsiteBackend/register';
	var addressForLogin = 'http://localhost:8080/CollaborationWebsiteBackend/login';
	//var firstRequestUri = 'http://localhost:8080/CollaborationWebsiteBackend/'+
	//'oauth/token?grant_type=password&client_id=restapp&client_secret=restapp&username='
	//+$rootScope.username+'&password='+$rootScope.password;
//var addressForLogin = 'http://localhost:8080/CollaborationWebsiteBackend/j_spring_security_check';
//localhost:8080/SpringRestSecurityOauth/oauth/token?grant_type=password&client_id=restapp&client_secret=restapp&username=beingjavaguys&password=spring@java
return {
	registerUser:registerUser,
	loginUser:loginUser,
	logout:logout,
	firstRequest:firstRequest
};

function registerUser(user){
	var deferred = $q.defer();
	$http.post(address,user).
	then(function(response){
		deferred.resolve(response.data);
	},function(errResponse){
		deferred.reject(errResponse);
	});
	return deferred.promise;
}

/*  ###################   code used for soring security with normal process  ########################*/

/*function loginUser(email,password){
	var deferred  = $q.defer();
	$http({
		url:addressForLogin,
		method:'POST',
		transformRequest:function(obj) {
			var str = [];
			for(var p in obj)
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			return str.join("&");
		},		
		data:{email:email,password:password},
		headers:{'Content-Type': 'application/x-www-form-urlencoded'},

		/*headers: {
			'Content-Type': 'application/json'
		},*/
		
//	}).
//then(function(response){
//	deferred.resolve(response.data);
//},function(errResponse){
	//deferred.reject(errResponse);
//});
//return deferred.promise;	
//}*/





 function loginUser(values){

 	var deferred  = $q.defer();
 	//debugger;
 	$http.post(addressForLogin,values).
 	then(function(response){
 		deferred.resolve(response.data);
	},function(errResponse){
		deferred.reject(errResponse);
 	});
 	return deferred.promise;	
 }




/*#######################################################*/


/*#######################        Code for basic authentication    #########################*/

/*function loginUser(client){
	var deferred  = $q.defer();
	$http.post(addressForLogin,client).
	then(function(response){
		deferred.resolve(response.data);
	},function(errResponse){
		deferred.reject(errResponse);
	});
	return deferred.promise;	
}*/


function logout(userId){
	var deferred = $q.defer();
	$http.put('http://localhost:8080/CollaborationWebsiteBackend/logout/'+userId)
	.then(function (response) {
		deferred.resolve(response);
	},
	function(errResponse){
		deferred.reject(errResponse);
	});
	return deferred.promise;
}

/*########################################################################################*/

/*################  Code for oauth2 authentication  ########################*/

/*code for get the refresh as well as access token*/
function firstRequest() {
	var deferred = $q.defer();
	//$http.post('http://localhost:8080/CollaborationWebsiteBackend/'+
	//'oauth/token?grant_type=password&client_id=restapp&client_secret=restapp&username='
	//+$rootScope.username+'&password='+$rootScope.password).
	$http.post()
	then(function (response) {
		deferred.resolve(response.data);
	},
	function(errResponse){
		deferred.reject(errResponse);
	});
	return deferred.promise;
}


}]);





/*#############################################################################*/



/*transformRequest:function(obj) {
			var str = [];
			for(var p in obj)
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			return str.join("&");
		},*/