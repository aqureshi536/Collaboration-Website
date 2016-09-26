
app.controller('singleForumController', ['forumFactory','$rootScope', function(forumFactory,$rootScope){
	var self = this;
	//self.forum={};
	self.post={forumPostContent:''};
	getForum($rootScope.forumId);

	function getForum(forumId){
		forumFactory.getForum(forumId)
		.then(function(data){
			
			self.forum=data;
		},function(errResponse){
			console.error('Error getting forum'+forumId);
		});
	}

	 self.postComment = function(forum,post) {
		//debugger;
		var fp = {forum:forum,post:post.forumPostContent};
		//forumFactory.postComment(forum,post).
		forumFactory.postComment(fp).
		then(function(data) {
			self.forum.forumPosts=self.forum.forumPosts.concat(data);
		},function (errResponse) {
			console.error("Error posting in forum");
		});
		reset();
	}

	function reset(){
		self.post={forumPostContent:''};
	}
	
	/*passForumFactory.setForum($rootScope.forumId);
	self.forum = passForumFactory.getForum();*/
	
	
}]);

