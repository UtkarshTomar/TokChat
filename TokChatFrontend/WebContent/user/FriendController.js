myApp.controller("friendController",function($scope,$http,$window,$location
        ,$cookieStore,$rootScope){
     
    $scope.suggestedUsers=function(){
        console.log('fetching Suggested Users');
        $http.get("http://localhost:2563/TokChatMiddleware/suggestedUsers").
        then(function(response){
                    $scope.suggestedUsers=response.data;
                    console.log($scope.suggestedUsers);
        });
    };
     
    $scope.addFriend=function(user){
        console.log('add Friend function');
        $http.post("http://localhost:2563/TokChatMiddleware/addfriend",user).
        then(function(response){
                    alert("Friend Request sent");
                    $window.location.reload();
        },
        function(error){
            alert("Error in sending friend request try again");
             
        });
    };
     
    $scope.pendingRequests=function(){
        console.log('fetching Pending Requests');
        $http.get("http://localhost:2563/TokChatMiddleware/pendingrequests").
        then(function(response){
                    $scope.pendingRequests=response.data;
                    console.log($scope.pendingRequests);
        });
    };
     
    $scope.acceptRequest=function(request){
        console.log("Accepting friend Request");
        $http.put("http://localhost:2563/TokChatMiddleware/acceptrequest",request).
        then(function(response){
                    alert("Friend Request Accepted");
                    $window.location.reload();
        });
    };
 
    $scope.deleteRequest=function(request){
        console.log("Deleting friend Request");
        $http.put("http://localhost:2563/TokChatMiddleware/deleterequest",request).
        then(function(response){
                    alert("Friend Request Deleted");
                    $window.location.reload();
        });
    };
     
    $scope.friendsList=function(){
        console.log('fetching Friend List');
        $http.get("http://localhost:2563/TokChatMiddleware/friends").
        then(function(response){
                    $scope.friends=response.data;
                    console.log($scope.friends);
        });
    };
});