package com.TokChatBackend.daosImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TokChatBackend.daos.UserDao;
import com.TokChatBackend.models.User;



@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Autowired
	SessionFactory sessionfactory;
	
	
	
	@Override
	public boolean registrerUser(User userObj) {
		try
		{
		Session session=sessionfactory.getCurrentSession();
		session.save(userObj);
		return true;
		}		
		catch(Exception e)
		{
		e.printStackTrace();
		return false;
		}
	}

	@Override
	public boolean checkLogin(User userObj) {
		try{
			Session session=sessionfactory.getCurrentSession();
			Query querry=session.createQuery("from User where email=:a and password=:b");
			querry.setParameter("a", userObj.getEmail());
			querry.setParameter("b", userObj.getPassword());
			
			List<User> userlist=querry.list();
			if(userlist.size() !=0)
			{
			return true;
			}
		}
		catch(Exception e)
			{
			e.printStackTrace();
			}
			return false;
	}
	

	@Override
	public boolean updateOnlineStatus(String status, String email) {
		try{
		Session session=sessionfactory.getCurrentSession();
		User userobj=(User)session.get(User.class,email);
		userobj.setOnlineStatus(status);
		return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User userObj) {
		try{
		Session session=sessionfactory.getCurrentSession();
		session.update(userObj);
		return true;
		}
		catch(Exception e){
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(User userObj) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.delete(userObj);
			return true;
			}
			catch(Exception e){
			e.printStackTrace();
			}
			return false;
	}

	@Override
	public User getUser(String email) {
		try{
		Session session=sessionfactory.getCurrentSession();
		User obj=(User)session.get(User.class,email);
		return obj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<User> getUsers() {
		try{
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createQuery("from User");
		return query.list();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
}
