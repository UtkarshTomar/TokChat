package com.TokChatBackend.daos;

import java.util.List;

import com.TokChatBackend.models.Forum;
import com.TokChatBackend.models.ForumComment;

public interface ForumDao {

	
	public boolean addForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public boolean updateForum(Forum forum);
	public Forum getForum(int forumId);
	public boolean approveForum(Forum forum);
	public boolean rejectForum(Forum forum);
	public List<Forum> listForums(String userName,String role);

	
	
	public boolean addForumComment(ForumComment ForumComment);
	public boolean deleteForumComment(ForumComment ForumComment);
	public ForumComment getForumComment(int commentId);
	public List<ForumComment> listForumComments(int ForumId);
}

