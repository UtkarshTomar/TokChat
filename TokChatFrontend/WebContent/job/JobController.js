myApp.controller("JobController",function($scope,$http,$window,$location
		,$cookieStore,$rootScope){
	
	$scope.job={jobId:'',jobDesignation:'',company:'',salary:'',location:'',jobDesc:''};
	
	
	$scope.addJob=function(){
		console.log('Add Job function');
		console.log($scope.job)
		$http.post("http://localhost:2563/TokChatMiddleware/addJob",$scope.job).
		then(function(response){
					console.log('Job Not Added')
										
				},
				function(response){
					alert('Job Added Succesfully');
					$window.location.reload();
					$location.path('viewJob');
				})
	};
	
	$scope.fetchAllJobs=function(){
		console.log('fetchAllJobs');
		$http.get("http://localhost:2563/TokChatMiddleware/listJobs")
		.then(function(response)
				{
						console.log('Second');
						$scope.allJobs=response.data;
						console.log($scope.allJobs);
				},
				function(error){
					console.log("No Job found...");
					$scope.allBlogs=[];
					$scope.viewmessage="No Job Found!!!"
				});
		};
	
		$scope.applyJob=function(jid){
		console.log('applly Job '+jid);
		$http.get("http://localhost:2563/BloggersMiddleware/applyJob/"+jid)
		.then(function(response)
				{
			},
			function(error){
				alert('Applied for Job');
				$window.location.reload();
				$location.path('viewJob');
			});

	};

});
