app.factory('eventFactory', ['$http','$q', function($http,$q){
	var address = 'http://localhost:8080/CollaborationWebsiteBackend/events/';
	return{
		fetchAllEvents:fetchAllEvents,
		createEvent:createEvent,
		updateEvent:updateEvent,
		deleteEvent:deleteEvent
	};




	function fetchAllEvents(){
		var deferred = $q.defer();
		$http.get(address).
		then(function (response) {
			deferred.resolve(response.data);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}


	function createEvent(event){
		var deferred = $q.defer();
		debugger;
		$http.post(address,event).
		then(function (response) {
			deferred.resolve(response);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}


	function  updateEvent(eventId,event) {
		var deferred = $q.defer();
		$http.put(address+eventId,event).
		then(function (response) {
			deferred.resolve(response);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise; 
	}
	

	function  deleteEvent(eventId) {
		var deferred = $q.defer();
		$http.put(address+eventId).
		then(function (response) {
			deferred.resolve(response);
		},function (errResponse) {
			deferred.reject(errResponse);
		});
		return deferred.promise;  
	}
}])