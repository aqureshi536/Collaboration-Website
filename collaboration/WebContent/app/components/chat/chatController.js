app.controller('chatController', ['chatFactory', function(chatFactory){
	var self = this;
	self.messages = [];
	self.message = "";


	self.addMessage = function(){
		chatFactory.send(self.message);
		self.message = "";
	};

	chatFactory.recieve().then(null,null,function (message) {
		self.messages.push(message);
	});

}])