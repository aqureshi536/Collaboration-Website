app.controller('singleBlogController', ['blogFactory','$log','$routeParams' ,function(blogFactory,$log,$routeParams){
	
	var self = this;
	self.singleBlog={};



	/*################  to fetch a single blog   ###############*/

	
		(function getBlog(blogId){
			debugger;
			$log.info(self.singleBlog);
			var getBlogId=$routeParams.blogId
			blogFactory.getBlog(getBlogId)
			.then(function(data){
				$log.info(self.singleBlog);
				self.singleBlog=data;
				$log.info(self.singleBlog);
			},
			function(errResponse){
				console.error("Error getting blog: "+blogId);
			});
		})();


		/*########################################################################*/

		self.comment=function(blogId,blogComment){
			self.process=true;
			var bc = {blogId:blogId,blogComment:blogComment};
			blogFactory.createBlogComment(bc).
			then(function(data){
				self.singleBlog.blogComments=self.singleBlog.blogComments.concat(data);
				self.process=false;
			},function (errResponse) {
				console.error(errResponse);
				self.process=false;
			});
			resetField();
		}

		function resetField(){
			self.blogComment = {blogCommentContent:''};
		};


	}]);