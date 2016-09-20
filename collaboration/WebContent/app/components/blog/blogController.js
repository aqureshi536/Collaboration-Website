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
self.blogs= [];

fetchAllBlogs();

function fetchAllBlogs(){
	self.dataLoaded = false;
	blogFactory.fetchAllBlogs()
	.then(function(data){
		self.blogs= data;
		self.dataLoaded = true;
	},function(errResponse){
		console.error("Error fetching blogs");
	});
}
	
}]);

