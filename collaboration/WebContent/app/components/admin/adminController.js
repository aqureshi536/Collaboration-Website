app.controller('adminBlogController', ['adminFactory', function(adminFactory){

var self= this;
self.blogs=[];

self.loading = false;


 function getUnapprovedBlogs(){
 	self.loading =true;
 	adminFactory.getUnapprovedBlogs().then(function(data){
 		self.blogs = data;
 	},function(errResponse){
 		console.error(errResponse);
 	});
 	self.loading = false;
 }
 getUnapprovedBlogs();
		


}]);


app.controller('adminForumController', ['adminFactory', function(adminFactory){
	var self = this;
	self.forums = [];
	self.loading = false;


	function getApprovedForums() {
		self.loading = true;
	adminFactory.getUnapprovedForums().then(function(data){
		self.forums = data
	},function(errResponse){
		console.error(errResponse)
	});
	self.loading = false;
}
getApprovedForums();

}]);