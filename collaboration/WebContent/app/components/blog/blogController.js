/*app.controller('blogCtrl', ['$scope', function($scope){
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


app.controller('blogController', ['$http','blogFactory', function($http,blogFactory){

	var self = this;
	self.blog={blogId:'',blogName:'',blogDescription:''};
	self.blogs= [];

	fetchAllBlogs();

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

	/*creating blog*/
	function createBlog(blog){
		console.log(blog);
		blogFactory.createBlog(blog)
		.then(
			fetchAllBlogs,
			function(errResponse){
				console.error("Error creating blog");

			});
	}
	

	self.submitBlog = function (){
		alert("in create blog");
		if(self.blog.blogId===''){createBlog(self.blog);}  /*if blog id doesnt exist from begning save it*/
		else{updateBlog(self.blog,self.blog.blogId);}	/*else if data or id exist go to update method and update the blog*/
		resetFields();
	}

	/*###############################################*/



	/*Update Blog*/
	function updateBlog(blog ,blogId){
		blogFactory.updateBlog(blog,blogId)
		.then(function(data){
			alert("Updated success");
			fetchAllBlogs();
		},
		function(errResponse){
			console.log("Error updating blog");
		});
		
	}


	self.editBlog = function(blogId){
		console.log('blogid to edit : ',blogId);
		for(var i=0;i<self.blogs.length;i++){
			self.blog = angular.copy(self.blogs[i]);
			break;	
		}
		
		
	}


	self.deleteBlog=function(blogId){
		alert('In delete blog');
		if(self.blog.blogId === blogId) {//clean form if the user to be deleted is shown there.
            resetFields()
        }
		blogFactory.deleteBlog(blogId).
		then(fetchAllBlogs,function(errResponse){
			console.error("Error deleting blog");
		});
		 
	}

	function resetFields(){
		self.blog={blogId:'',blogName:'',blogDescription:''};
	}
}]);

