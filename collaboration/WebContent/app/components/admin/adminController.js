app.controller('adminBlogController', ['adminFactory','$rootScope','$location', function(adminFactory,$rootScope,$location){

	var self= this;
	self.blogs=[];
	self.none = false;


(function(){
	if($rootScope.client==undefined || $rootScope.client == null || $rootScope.client.role!=0 )
	{
$location.path("/");
	}
})();

	function getUnapprovedBlogs(){
		debugger;
		self.loading =true;
		adminFactory.getUnapprovedBlogs().
		then(function(data){
			
			self.blogs = data;
			if(self.blogs.length<=0){
				self.none = true;
			}
			self.loading = false;
			self.failed = false;
		},function(errResponse){
			console.error(errResponse);
			self.loading = false;
			self.process = false;
			self.failed = true;
		});
		
	}

	getUnapprovedBlogs();


	self.approveBlog = function(blogId){
		self.process = true;
		adminFactory.approveBlog(blogId)
		.then(function (data){
			var length = self.blogs.length;
			for(var i=0;i<length;i++){
				if(self.blogs[i].blog.blogId == blogId){
					self.blogs.splice(i, 1);
				}
			}
			if(self.blogs.length<=0){
				self.none = true;
			}
			self.process = false;
			self.failed = false;
			console.log('approval successful')
		},function(errResponse){
			console.error(errResponse);
			self.process = false;
			self.failed = true;
		});
		
	}
	self.disapproveBlog = function (blogId) {
		self.process = true;
		adminFactory.disapproveBlog(blogId)
		.then(function(data){
			var length = self.blogs.length; 
			for(var i=0;i<length;i++){
				if(self.blogs[i].blog.blogId == blogId){
					self.blogs.splice(i,1);	
					console.log('Blog disapproved');
				}

			}
			if(self.blogs.length<=0){
				self.none = true;
			}
			self.process = false;
			self.failed = false;
		},function(errResponse){
			console.error(errResponse);
			self.process = false;
			self.failed = true;
		});
	}



}]);


app.controller('adminForumController', ['adminFactory', function(adminFactory){
	var self = this;
	self.forums = [];	
	self.none = false;
	

	function getApprovedForums() {		
		debugger;
		self.loading = true;
		adminFactory.getUnapprovedForums()
		.then(function(data){			
			self.forums = data;
			if(self.forums.length<=0){
				self.none = true;
			}
			self.loading = false;
			self.failed = false;
		},function(errResponse){
			console.error(errResponse);			
			self.failed = true;
			self.loading = false;
		});
		
	}

	getApprovedForums();


	self.approveForum = function(forumId){
		self.process=true;
		adminFactory.approveForum(forumId).then(function(data){
			var length = self.forums.length;
			for(var i = 0;i<length;i++){
				if(self.forums[i].forum.forumId==forumId){
					self.forums.splice(i,1);
					console.log('approved forum' + forumId);
				}
			}
			if(self.forums.length<=0){
				self.none = true;
			}
			self.process =false;
			self.failed = false;
		},function(errResponse){
			console.error(errResponse);
			self.process = false;
			self.failed = true ;
		});
		
	}

	self.disapproveForum = function(forumId){
		self.process = true;
		adminFactory.disapproveForum(forumId).then(function(data){
			var length = self.forums.length;
			for(var i = 0;i<length;i++){
				if(self.forums[i].forum.forumId==forumId){
					self.forums.splice(i,1);
					console.log('disapproved forum' + forumId);
				}
			}
			if(self.forums.length<=0){
				self.none = true;
			}
			self.process =false;
			self.failed = false;
		},
		function(errResponse){
			console.error(errResponse);
			self.process = false;
			self.failed = true;
		});
		
	}

}]);