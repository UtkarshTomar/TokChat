myApp.controller("blogController",function($scope,$http,$window,$location
		,$cookieStore,$rootScope){
	
$scope.blog={blogId:'',blogName:'',blogContext:'',createDate:'',email:'',status:'',likes:''};
$scope.comment={commentText:'',email:'',blogId:'',commentDate:''};	

$scope.addBlog=function(){
		console.log('Add Blog function');
		console.log($scope.blog)
		$http.post("http://localhost:2563/TokChatMiddleware/addBlog",$scope.blog).
		then(function(response){
					console.log('Blog Not Added')
										
				},
				function(response){
					alert('Blog Added Succesfully');
					$window.location.reload();
				})
	};
	
	$scope.fetchAllBlogs=function(){
	$http.get("http://localhost:2563/TokChatMiddleware/listBlogs")
	.then(function(response)
			{
					console.log('Second');
					$scope.allBlogs=response.data;
					console.log($scope.allBlogs);
			},
			function(error){
				console.log("No Blogs found...");
				$scope.allBlogs=[];
				$scope.viewmessage="No Blogs Found!!!"
			});
	};
	
	$scope.fetchAllApprovedBlogs=function(){
		$http.get("http://localhost:2563/TokChatMiddleware/listApprovedBlogs")
		.then(function(response)
				{
						console.log('Second');
						$scope.allBlogs=response.data;
						console.log($scope.allBlogs);
				},
				function(error){
					console.log("No Blogs found...");
					$scope.allBlogs=[];
					$scope.viewmessage="No Blogs Found!!!"
				});
		};

	
	$scope.approveBlog=function(bid){
		console.log('approve Blog '+bid);
		$http.get("http://localhost:2563/TokChatMiddleware/approveBlog/"+bid)
		.then(function(response)
				{
				},
				function(error){
					alert('Blog Approved');
					$window.location.reload();
					$location.path('viewBlogs');
		});
	};
	$scope.rejectBlog=function(bid){
		console.log('reject Blog '+bid);
		$http.get("http://localhost:2563/TokChatMiddleware/rejectBlog/"+bid)
		.then(function(response)
				{
				},
				function(error){
					alert('Blog Rejected');
					$window.location.reload();
					$location.path('viewBlogs');
		});
	};
	
	$scope.incrementLikes=function(bid){
		console.log("increment likes called "+bid);
		$http.get("http://localhost:2563/TokChatMiddleware/incrementLikes/"+bid)
		.then(function(response)
				{
				},
				function(error){
					$window.location.reload();
					$location.path('viewApprovedBlogs');
		});
	};
	
	$scope.addComment=function(blog,commentTxt){
		console.log('Add Comment function : '+blog.blogId+' '+blog.blogName+' '+commentTxt);
		
		$scope.comment.commentText=commentTxt;
		$scope.comment.blogId=blog.blogId;
		
		$http.post("http://localhost:2563/TokChatMiddleware/addBlogComment",$scope.comment).
		then(function(response){
					console.log('Comment Added')
										
				},
				function(response){
					alert('To need to Login to do the comment');
					$location.path("/login");
					
				})
	}
	
});