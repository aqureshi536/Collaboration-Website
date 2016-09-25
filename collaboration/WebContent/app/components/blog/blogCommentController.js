app.controller('singleBlogController', ['blogFactory','$log' ,function(blogFactory,$log){
	
var self = this;
self.singleBlog={};



/*################  to fetch a single blog   ###############*/


	self.getBlog =	function (blogId){
		debugger;
		$log.info(self.singleBlog);
		blogFactory.getBlog(blogId)
		.then(function(data){
			$log.info(self.singleBlog);
			self.singleBlog=data;
			$log.info(self.singleBlog);
		},
		function(errResponse){
			console.error("Error getting blog: "+blogId);
		});
	}

	/*########################################################################*/

function resetField(){
self.blogComment = {blogCommentContent:''};
};


}]);