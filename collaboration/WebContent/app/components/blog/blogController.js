/*      Dummy Code

app.controller('blogCtrl', ['$scope', function($scope){
	var blogs=[
	{"blogName":"First Blog",
	"blogDescription":"This is the first blog",	
	"blogComments":
					[
						{"blogComment":"This is first comment","userImage":"1"},
						{"blogComment":"This is second comment","userImage":"1"},
						{"blogComment":"This is third comment","userImage":"3"}
					]

},
{"blogName":"Second Blog",
	"blogDescription":"This is the Second blog",	
	"blogComments":
					[
						{"blogComment":"This is first comment","userImage":"1"},
						{"blogComment":"This is second comment","userImage":"1"},
						{"blogComment":"This is third comment","userImage":"3"}
					]

},
{"blogName":"Third Blog",
	"blogDescription":"This is the Third blog",	
	"blogComments":
					[
						{"blogComment":"This is first comment","userImage":"1"},
						{"blogComment":"This is second comment","userImage":"1"},
						{"blogComment":"This is third comment","userImage":"3"},
						{"blogComment":"This is fourth comment","userImage":"3"}
					]

},
]

$scope.blogs=blogs;

}]);
*/


app.controller('blogController', ['blogFactory','$log','$rootScope', function(blogFactory,$log,$rootScope){

	var self = this;
	self.blog={blogId:'',blogName:'',blogDescription:'',userId:''};
	self.blogs= [];
	self.singleBlog={};
	self.form = false;
	fetchAllBlogs();



	/*#######################################################################################*/
	/* fetching all blogs*/


	function fetchAllBlogs(){
		self.dataLoaded = false;
		blogFactory.fetchAllBlogs()
		.then(function(data){
			self.blogs= data;
			self.dataLoaded = true;
			self.failed=false;
		},function(errResponse){
			console.error("Error fetching blogs");
			self.failed=true;
		});
	}


	/*################  to fetch a single blog   ###############*/


/*	self.getBlog =	function (blogId){
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
	}*/

	/*########################################################################*/


	/*creating blog*/



	function createBlog(blog){
		self.process=true;
		//debugger;
		console.log(blog);
		blogFactory.createBlog(blog)
		.then(
			function(data){
				self.blogs=self.blogs.concat(data);
				self.process=false;
			},
			function(errResponse){
				console.error("Error creating blog");
				self.process=false;

			});
	}
	

	self.submitBlog = function (){
		//alert("in create blog");
		debugger;
		if(self.blog.blogId === '' || self.blog.blogId === undefined){
			var formData = new FormData();
			var file = self.blogImage;
			console.dir(file);			
			formData.append('blogImage',file);
			self.blog.userId = $rootScope.client.userId;
			formData.append('blog',angular.toJson(self.blog,true));
			console.log(self.blog);
			console.log(formData);
			createBlog(formData);
			//debugger;
		}  /*if blog id doesnt exist from begning save it*/
		
		else{
			updateBlog(self.blog,self.blog.blogId);
		}	/*else if data or id exist go to update method and update the blog*/
		resetFields();
	}


	/*#################################################################################*/
	/*Update Blog*/


	
	function updateBlog(blog ,blogId){
		self.process=true;
		//debugger;
		blogFactory.updateBlog(blog,blogId)
		.then(function(data){
			for(var i=0;i<self.blogs.length;i++){
				if(self.blogs[i].blogId===blogId){
					self.blogs.splice(i, 1, data);
					break;
				}
			}
			self.process=false;
		},
		function(errResponse){
			console.log("Error updating blog");
			self.process=false;
		});
		self.form = false;
	}


	self.editBlog = function(blogId){
		self.form = true;
		console.log('blogid to edit : ',blogId);
		//debugger;
		for(var i=0;i<self.blogs.length;i++){
			var n = self.blogs[i].blogId.localeCompare(blogId)
			if(n==0){
			//	alert("blogId");
			self.blog = angular.copy(self.blogs[i]);
			break;
		}

	}


}


/*###################################################################################################*/
/*Delete blog*/


self.deleteBlog=function(blogId){
	self.process=true;
		//alert('In delete blog');
		if(self.blog.blogId === blogId) {//clean form if the user to be deleted is shown there.
			resetFields()
		}
		blogFactory.deleteBlog(blogId).
		then(function(){
			for(var i=0;i<self.blogs.length;i++){
				if(self.blogs[i].blogId===blogId){
					self.blogs.splice(i, 1);
					break;
				}
			}
			self.process=false;
		},function(errResponse){
			console.error("Error deleting blog");
			self.process=false;
		});

	}

	function resetFields(){
		self.blog={blogId:'',blogName:'',blogDescription:''};
		self.comment='';
		self.blogImage='';
	}


	/*#############################   Blog Comment Section   ######################################################*/

	/*self.getBlogComments= function(blogId){
		for(var i=0;i<self.blogs.length;i++){
			var n = self.blogs[i].blogId.localeCompare(blogId)
			if(n==0){
			//	alert("blogId");
			self.blog = angular.copy(self.blogs[i]);
			break;
			blogFactory.getBlogComments().
			then()
		}
		*/


/*self.createBlogComment = function(blog,self.comment){

}*/

}]);


