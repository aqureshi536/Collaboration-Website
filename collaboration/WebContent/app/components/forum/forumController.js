/*app.controller('forumCtrl', function(){
	var me= this;
	var forums = [
					{
					"forumName":"ForumOne",
					"forumDescription":"This is a first forum",					
					"forumPosts":[
									{"forumPost":"This is a first Post","userImage":"1"},
									{"forumPost":"This is a Second Post","userImage":"1"},
									{"forumPost":"This is a Third Post","userImage":"3"},
									{"forumPost":"This is a Fourth Post","userImage":"3"}
								 ]
					},
					{
					"forumName":"ForumTwo",
					"forumDescription":"This is a Second forum",					
					"forumPosts":[
									{"forumPost":"This is a first Post","userImage":"1"},
									{"forumPost":"This is a Second Post","userImage":"1"},
									{"forumPost":"This is a Third Post","userImage":"3"}

								 ]
					}
				 ];
	me.forums=forums;
	me.toggleForum=function(id){
		/*alert(id);*/

		app.controller('forumController', ['forumFactory','$log','$rootScope', function(forumFactory,$log,$rootScope){
			var self=this;
			self.forums= [];
			self.forum={forumTitle:'',forumDescription:''};
			self.singleForum={};	
			self.form = false;

			fetchAllForums();
			
			function fetchAllForums(){
			//	debugger;
			self.dataLoaded= false;
			console.log("Success fetching forums");
			forumFactory.fetchAllForums().
			then(function(data){
			//	console.log(response);
			self.forums=data;
			//$log.info(self.forums);
			self.dataLoaded= true;
			self.failed=false;
		},function(errResponse){
			console.error(errResponse);
			self.failed=true;
			self.dataLoaded= true;
		});
		}

		function createForum(forum){
			self.process=true;
			forumFactory.createForum(forum).
			then(function(data){
				//$.extend( true, self.forums, data);
				/*self.addedForum=forumFactory.getAddedForum();*/
				
				//self.forums=angular.merge(self.forums,data);
				self.forums=self.forums.concat(data);// to refresh the data
				/*$log.info(self.forums);*/
				self.process=false;
			},
			function(errResponse){
				self.process=false;
				console.error(errResponse);
			});
			
		}

		self.submitForum = function(){
			//debugger;

			if(self.forum.forumId==''||self.forum.forumId==undefined){
				self.forum.userId = $rootScope.client.userId;
				createForum(this.forum);
			}
			else{
				updateForum(self.forum);
			}
			reset();
		}

		/*##################################################*/


		function updateForum(forum){
			self.process=true;
			forumFactory.updateForum(forum).
			then(function(data){

				self.editedForum=data;
				for(var i=0;i<self.forums.length;i++){
					if(self.forums[i].forumId===data.forumId){
						self.forums.splice(i,1,data);
					}
				}
				self.process=false;
			},function(errResponse){
				self.process=false;
				console.error(errResponse);
				console.error("Error updating forum : "+forumId);
			});
			self.form = false;
		}

		


		self.editForum = function(forumId){
			self.form = true;
			for(var i=0;i<self.forums.length;i++){
				if(self.forums[i].forumId===forumId){
					self.forum=angular.copy(self.forums[i])
					break;
				}
			}
		}


		self.deleteForum= function(forumId){
			//debugger;
			self.process=true;
			forumFactory.deleteForum(forumId).
			then(function(data){
				for(var i=0;i<self.forums.length;i++){
					if(self.forums[i].forumId===forumId){
						self.forums.splice(i,1);
						break;
					}
					console.log('Deleted Successfull : '+forumId)
				}
				self.process=false;
			},function(errResponse){
				self.process=false;
				console.error('Not Able to delete : '+forumId);
			});
		}

		function reset(){
			self.forum={forumTitle:'',forumDescription:''};
		}


		self.getForum=function(forumId){
			$rootScope.forumId = forumId;
		}

	}]);
