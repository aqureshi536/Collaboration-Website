app.controller('friendController', ['friendFactory','$rootScope', function(friendFactory,$rootScope){

	var self = this;
	self.friends = [];

	function fetchAllFriends()
	{
		self.loading = true;
		friendFactory.fetchAllFriends($rootScope.client.userId)
		.then(function(data){
			self.friends = data;
			self.loading = false;
		},function(errResponse){
			console.error(errResponse);
			self.loading = false;
		});
	}
	fetchAllFriends();



	
}])