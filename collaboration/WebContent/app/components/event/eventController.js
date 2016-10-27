app.controller('eventController', ['eventFactory', function(eventFactory){
	
	var self = this;
	self.events = [];
	self.event = {eventId:'',place:'',content:''};
	self.calendar = {eventAt:'',hour:'',min:''}

	function fetchAllEvents(){
		eventFactory.fetchAllEvents().
		then(function (data) {
			self.events = data;
			console.log(data);
		},
		function (errResponse) {
			console.log(errResponse);
		});
	};

	fetchAllEvents();

	function createEvent(event) {
		debugger;
		eventFactory.createEvent(event).
		then(function (data) {
			self.events = self.events.concat(data);
			console.log(data + "Added");
		},
		function (errResponse) {
			console.log(errResponse);
		});
		reset();
	}


	function updateEvent (event,eventId) {
		eventFactory.updateEvent(event,eventId).
		then(function (data) {
			var eventLength = self.events.length;
			for(var i=0;i<eventLength;i++){
				if(self.events[i].eventId == eventId){
					self.events.splice(self.events[i],1,data);
				}
			}
			console.log(data + "Updated");
		},
		function (errResponse) {
			console.log(errResponse);
		});
		reset();
	}


	self.submit = function(){
		debugger;
		if(self.event.eventId ==''||self.event.eventId == undefined){
			//createEvent(self.event);
			var value = {event:self.event,calendar:self.calendar};
			createEvent(value);
			console.log(self.calendar);
		}
		else{
			updateEvent(self.event,self.event.eventId)
		}
	}


	function reset(){
		self.event = {eventId:'',place:'',content:''};
		self.calendar = {eventAt:'',hour:'',min:''}
	}
}])