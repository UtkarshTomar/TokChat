package com.TokChatBackend.daosImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.TokChatBackend.daos.ForumDao;
import com.TokChatBackend.models.Forum;
import com.TokChatBackend.models.ForumComment;


@Repository("forumDao")
@Transactional
public class ForumDaoImpl implements ForumDao {
	
	@Autowired
	SessionFactory sessionfactory;

	@Override
	public boolean addForum(Forum forum) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.saveOrUpdate(forum);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	

	@Override
	public boolean deleteForum(Forum forum) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.delete(forum);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateForum(Forum forum) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.update(forum);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public Forum getForum(int forumId) {
		try{
			Session session=sessionfactory.getCurrentSession();
		Forum forum =(Forum)session.get(Forum.class, forumId);	
		return forum; 
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean approveForum(Forum forum) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.update(forum);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean rejectForum(Forum forum) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.update(forum);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Forum> listForums(String userName, String role) {
		try{
			Session session=sessionfactory.getCurrentSession();
			Query querry=session.createQuery("from Forum where userName=:abc and role:xyz");
			querry.setParameter("abc",userName);
			querry.setParameter("xyz",role);
			List<Forum> forumList=querry.list();
			return forumList;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addForumComment(ForumComment ForumComment) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.saveOrUpdate(ForumComment);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteForumComment(ForumComment ForumComment) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.delete(ForumComment);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public ForumComment getForumComment(int commentId) {
		try{
			Session session=sessionfactory.getCurrentSession();
			ForumComment forumcom=(ForumComment)session.get(ForumComment.class, commentId);
			return forumcom;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ForumComment> listForumComments(int ForumId) {
		try{
			Session session=sessionfactory.getCurrentSession();
			Query querry=session.createQuery("from ForumComment");
			List<ForumComment> forumCommentList=querry.list();
			return forumCommentList;
		
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	return null;
	}

}
