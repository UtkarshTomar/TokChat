package com.TokChatBackend.daos;

import java.util.List;

import com.TokChatBackend.models.friends;
import com.TokChatBackend.models.User;

public interface FriendDao {
	List<User> suggestedUsers(String email);

	void addFriend(friends friend);
	
	List<friends> pendingRequests(String toIdEmail);

	void acceptRequest(friends request);

	void deleteRequest(friends request);
	
	List<friends> listOfFriends(String email);


}

