app.factory('passForumFactory', ['$http','$q', function($http,$q){
	var self = this;
	
	var address = 'http://localhost:8080/CollaborationWebsiteBackend/forums/';
	return {
		getForum:function(){
			//console.log(self.data);
			return self.data;
		},
		setForum:function(forumId){
			returnForum(forumId).then(function(data){
				//console.log(data);
				self.data= data;
				//console.log(self.data);
			});
		}
	}


	

}])