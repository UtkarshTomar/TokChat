myApp.controller("chatCtrl",function($scope,$http,$window,$location,ChatService,$rootScope){
    $scope.stompClient=ChatService.stompClient
    $scope.users=[];
    $scope.chats=[];
     
    $scope.$on('sockConnected',function(event,frame){
        alert('Successfully connected with WebSocket')
        $scope.userName=$rootScope.currentUser.firstName;
        alert($scope.userName + ' joined the chat room..')
         
        $scope.stompClient.subscribe("/app/join/"+$scope.userName,function(message){
            console.log('Helllo 5'+$scope.userName);
            console.log(message.body)
            alert(message.body)
            $scope.users=JSON.parse(message.body)
            $scope.$apply();
        })
         
        $scope.stompClient.subscribe("/topic/join",function(message){
            console.log('Hello 4');
            user=JSON.parse(message.body);
            if(user != $scope.userName && $.inArray(user, $scope.users) == -1){
                $scope.addUser(user);
                $scope.latestUser = user;
                $scope.$apply();
                alert($scope.latestUser + ' has joined the chat')
                $('#joinedChat').fadeIn(500).delay(10000).fadeOut(500);
            }
        })
    })
     
    $scope.addUser=function(user){
        $scope.users.push(user)
        $scope.$apply();
    }
     
    $scope.sendMessage=function(chat){
        console.log('Hello 3');
        chat.from=$scope.userName
        $scope.stompClient.send("/app/chat",{},JSON.stringify(chat)) //json.stringify converts jspn format to string
        $rootScope.$broadcast('sendingChat',chat)
        $scope.chat.message=''
    };
     
    $scope.$on('sockConnected',function(event,frame){
         
        $scope.userName=$rootScope.currentUser.firstName;
         
        $scope.stompClient.subscribe("/queue/chats",function(message){
            alert('HEllo 1 message ' + message.body)
            $scope.processIncomingMessage(message,true)
        });
         
        $scope.stompClient.subscribe("/queue/chats/"+$scope.userName,function(message){
            alert('HEllo 2 message is ' + message.body)
            $scope.processIncomingMessage(message,false)
        })
    })
     
    $scope.processIncomingMessage=function(message,broadcast){
        console.log('Bye 1');
        message=JSON.parse(message.body)//message.body is chat object
        message.direction='incoming'
            if(message.from!=$scope.userName){
                $scope.addChat(message);
                $scope.$apply();
            }
    }
     
    $scope.addChat=function(chat){
        console.log('Bye 2');
        $scope.chats.push(chat)
    };
 
    $scope.$on('sendingChat',function(event,sentChat){
        console.log('Bye 3');
        chat=angular.copy(sentChat)
        chat.from='Me'
        chat.direction='outgoing'
            $scope.addChat(chat)
    });
 
 
});