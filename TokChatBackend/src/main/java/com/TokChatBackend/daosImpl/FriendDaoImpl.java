package com.TokChatBackend.daosImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TokChatBackend.daos.FriendDao;
import com.TokChatBackend.models.friends;
import com.TokChatBackend.models.User;

@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<User> suggestedUsers(String email) {
		Session session=sessionFactory.getCurrentSession();
		String queryString="select * from user_tab where email in "
				+ "(select email from user_tab where email!=? "
				+ "minus "
				+ "(select toId_email from friend_tab where fromId_email=? "
				+ "union "
				+ "select fromId_email from friend_tab where toId_email=? )) and role='Role_User' ";
				
				
		SQLQuery query=session.createSQLQuery(queryString);
		query.setString(0, email);
		query.setString(1, email);
		query.setString(2, email);
		query.addEntity(User.class);
		List<User> suggestedUsers=query.list();
		return suggestedUsers;
	}

	public void addFriend(friends friend) {
		
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		
	}

	public List<friends> pendingRequests(String toIdEmail) {
		
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from friends where status=? and toId.email=?");
		query.setCharacter(0, 'P');
		query.setString(1, toIdEmail);
		List<friends> pendingRequests=query.list();
		return pendingRequests;
	}

	public void acceptRequest(friends request) {
		Session session=sessionFactory.getCurrentSession();
		request.setStatus('A');
		session.update(request);
		
	}

	public void deleteRequest(friends request) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(request);
		
	}

	public List<friends> listOfFriends(String email) {
		Session session=sessionFactory.getCurrentSession();
	
		Query query1=session.createQuery
	("select toId from friends f where f.fromId.email=? and f.status=?");
	
		query1.setString(0, email);
		query1.setCharacter(1, 'A');
		List<friends> friendList1=query1.list();
		
		Query query2=session.createQuery("select fromId from friends f where f.toId.email=? and f.status=?");
		query2.setString(0, email);
		query2.setCharacter(1, 'A');
		List<friends> friendList2=query2.list();
		
		friendList1.addAll(friendList2);
		return friendList1;
	}

}



