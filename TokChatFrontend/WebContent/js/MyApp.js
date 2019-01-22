var myApp = angular.module("myApp", ["ngRoute",,"ngCookies"]);
myApp.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "main.html"
    })
    .when("/AboutUs", {
        templateUrl : "AboutUs.html"
    })
    .when("/ContactUs", {
        templateUrl : "ContactUs.html"
    })
    .when("/signup", {
        templateUrl : "user//SignUp.html"
    })
     .when("/login", {
        templateUrl : "user//Login.html"
    })
    .when("/homePage", {
        templateUrl : "HomePage.html"
    })
    
    .when("/logout", {
        templateUrl : "main.html"
    })
    
    .when("/addBlog", {
        templateUrl : "blog//BlogForm.html"
    })
    
    .when("/viewBlog", {
        templateUrl : "blog//ViewBlogs.html"
    })
    .when("/viewApprovedBlogs", {
        templateUrl : "blog//ViewApprovedBlogs.html"
    })
    
    .when("/addJob", {
        templateUrl : "job//JobForm.html"
    })
    
     .when("/viewJob", {
        templateUrl : "job//ViewJobs.html"
    })
    .when("/uploadprofilepic", {
        templateUrl : "user//UploadProfilePic.html"
    })
    .when("/viewProfile", {
        templateUrl : "user//viewProfile.html"
    })
    .when("/updateProfile", {
        templateUrl : "user//UpdateProfile.html"
    })
    
    .when("/viewComments", {
        templateUrl : "blog//ViewBlogComments.html"
    

})
.when("/suggestedusers",{
	templateUrl:'user/suggestedUsers.html'
	
})
.when('/pendingrequests',{
	templateUrl:'user/PendingRequests.html'
	
})
.when('/friends',{
	templateUrl:'user/FriendsList.html'
	
})
    .when('/chat',{
		templateUrl:'user/chat.html',
		controller:'chatCtrl'
		
	})
	
    
});

myApp.run(function($rootScope,$cookieStore){
	console.log('I m in run function');
	console.log($rootScope.currentUser);
	
	
	if($rootScope.currentUser==undefined){
		console.log('current User is undefined');
		$rootScope.currentUser=$cookieStore.get('userDetails');
	}
	else {
		console.log($rootScope.currentUser.email);
		console.log($rootScope.currentUser.role);
	}
	
});





















