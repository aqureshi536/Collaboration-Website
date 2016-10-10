app.factory('chatFactory', ['$q','$timeout','$rootScope', function($q,$timeout,$rootScope){
	var factory = {},listener = $q.defer(),socket= {
		client : null,
		stomp :null
	};


	factory.RECONNECT_TIMEOUT = 30000;
	factory.SOCKET_URL = ' http://localhost:8080/CollaborationWebsiteBackend/chat';
	factory.CHAT_TOPIC = '/topic/message';
	factory.CHAT_BROKER = '/app/chat';


	factory.recieve = function(){
		return listener.promise;
	};

	factory.send = function(message){
		socket.stomp.send(factory.CHAT_BROKER,{
			priority : 9
		},
		JSON.stringify({message:message}))
		console.log(message);
	};


	var reconnect = function(){
		$timeout(function(){
			initialize();
		},this,RECONNECT_TIMEOUT);
	};

	var getMessage = function(data){
		var message  = JSON.parse(data),out = {};
		out.message = message.message;
		out.time = new Date(message.time);
		out.email = $rootScope.client.email;
		/*if(_.contains(mess))*/ /*May cause an error*/
		return out;
	};

	var startListener =  function () {
		socket.stomp.subscribe(factory.CHAT_TOPIC,function(data){
			listener.notify(getMessage(data.body));
		});
	}

	var initialize = function(){
		socket.client = new SockJS(factory.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({},startListener);
		socket.stomp.onclose = reconnect;
	};


		initialize();
		return factory;

}]);