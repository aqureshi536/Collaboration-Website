
app.controller('singleForumController', ['forumFactory','$rootScope', function(forumFactory,$rootScope){
	var self = this;
	
	getForum($rootScope.forumId);

	 function getForum(forumId){
		forumFactory.getForum(forumId)
		.then(function(data){
			
			self.forum=data;
		},function(errResponses){
			console.error('Error getting forum'+forumId);
		});
	}

	
	/*passForumFactory.setForum($rootScope.forumId);
	self.forum = passForumFactory.getForum();*/
	
	
}]);

