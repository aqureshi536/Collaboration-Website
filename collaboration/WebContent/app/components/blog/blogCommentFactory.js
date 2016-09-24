app.factory('blogCommentFactory', ['$http','$q', function($http,$q){
	
var address = "http://localhost:8080/CollaborationWebsiteBackend/blogcomments/"

var factory = {
	getBlogComments:getBlogComments,
	createBlogComment:createBlogComment
};

return factory;

/*================    This method is not useful as all comments are loaded  by spring one to many  ========   */
/*For fetching all blog comments   */
function getBlogComments(blogId){
	var deferred = $q.defer();
	$http.get(address+blogId).
	then(function(response){
		deferred.resolve(response.data);
		console.log('fetched blogs comments');
	},function(errResponse){
		deferred.reject(errResponse);
		console.error('error fetching blogcomments');
	});
}


/*==================================================================================================*/



/*for commenting on a blog*/

function createBlogComment(blogId){
	var deferred = $q.defer();
	$http.post(address+blogId).
	then(function(response){
		deferred.resolve(response.data);
		console.log('created blog comment');
	},function(errResponse){
		deferred.reject(errResponse);
		console.error('error creating blogcomments');
	});
}

}]);


