app.controller('forumCtrl', function(){
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
	var buttonToggle=$('#buttonForumToggle'+id);
	var frmTable=$('#frmTable'+id);
        frmTable.is(":visible") ? buttonToggle.text("View") : buttonToggle.text("Hide");
        frmTable.toggle(100);
    
	

};
	
});