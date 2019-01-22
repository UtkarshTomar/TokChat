package com.TokChatMiddleware.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TokChatBackend.daos.FriendDao;
import com.TokChatBackend.daos.UserDao;
import com.TokChatBackend.models.friends;
import com.TokChatBackend.models.User;

@RestController
public class FriendController {
	
	@Autowired
	private FriendDao friendDao;

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/suggestedUsers",method=RequestMethod.GET)
	public ResponseEntity<?> suggestedUsers(HttpSession session){
		
		User user=(User) session.getAttribute("userObj");
		String email=user.getEmail();
		if(email==null)
		{ 
			Error error=new Error("Unauthorised Access..");
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);  
		}
		List<User> suggestedUsers=friendDao.suggestedUsers(email);
		return new ResponseEntity <List<User>>(suggestedUsers,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/addfriend",method=RequestMethod.POST)
	public ResponseEntity<?> addFriend(@RequestBody User toId,HttpSession session){
		
		User user=(User) session.getAttribute("userObj");
		String email=user.getEmail();
		if(email==null)
		{
			Error error=new Error("Unauthorised Access..");
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);  
		}
		User fromId=userDao.getUser(email);
		friends friend=new friends();
		friend.setFromId(fromId);
		friend.setToId(toId);
		friend.setStatus('P');
		friendDao.addFriend(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> pendingRequests(HttpSession session){
		
		User user=(User) session.getAttribute("userObj");
		String email=user.getEmail();
		if(email==null)
		{
			Error error=new Error("Unauthorised Access..");
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);  
		}
		List<friends> pendingRequests=friendDao.pendingRequests(email);
		return new ResponseEntity<List<friends>>(pendingRequests,HttpStatus.OK);
	}		
	
	@RequestMapping(value="/acceptrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> acceptRequest(@RequestBody friends request,HttpSession session){
		
		User user=(User) session.getAttribute("userObj");
		String email=user.getEmail();
		if(email==null)
		{
			Error error=new Error("Unauthorised Access..");
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);  
		}
		friendDao.acceptRequest(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/deleterequest",method=RequestMethod.PUT)
	public ResponseEntity<?> deleteRequest(@RequestBody friends request,HttpSession session){
		
		User user=(User) session.getAttribute("userObj");
		String email=user.getEmail();
		if(email==null)
		{
			Error error=new Error("Unauthorised Access..");
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);  
		}
		friendDao.deleteRequest(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/friends",method=RequestMethod.GET)
	public ResponseEntity<?> getAllFriends(HttpSession session){
		
		User user=(User) session.getAttribute("userObj");
		String email=user.getEmail();
		if(email==null)
		{
			Error error=new Error("Unauthorised Access..");
			return new ResponseEntity<Error>(HttpStatus.UNAUTHORIZED);  
		}
		List<friends> friends=friendDao.listOfFriends(email);
		return new ResponseEntity<List<friends>>(friends,HttpStatus.OK);
	}
}

