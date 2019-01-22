package com.backend.SecondProject;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.TokChatBackend.daos.UserDao;
import com.TokChatBackend.models.User;



public class UserTestCase {
	
	static UserDao userDaoObj;
	
	@BeforeClass
	public static void init(){
	AnnotationConfigApplicationContext context=new 	AnnotationConfigApplicationContext();
	context.scan("com.TokChatBackend");	
	context.refresh();
	
	userDaoObj=context.getBean("userDao",UserDao.class);
	}
	
	@Test
	@Ignore
    public void addUserTest()
    {
		User user=new User();
		user.setEmail("anubhav@gmail.com");
		user.setPassword("anubhav123");
		user.setFirstName("Anubhav");
		user.setLastName("Bedi");
		user.setContactNumber(7878787878l);
		user.setOnlineStatus("offline");
		user.setRole("User");
		
		boolean r=userDaoObj.registrerUser(user);
		
        assertTrue("Not able to register User",r);
    }
	
	@Test
	@Ignore
	public void checkLoginTest(){
		
		User user=new User();
		user.setEmail("anubhav@gmail.com");
		user.setPassword("anubhav1235");
		
		boolean r=userDaoObj.checkLogin(user);
		assertTrue("Invalid Email or PAssword",r);
	}
	
	@Test
	@Ignore
	public void updateOnlineStatusTest(){
		boolean r=userDaoObj.updateOnlineStatus("online","animesh@gmail.com");
		assertTrue("Not able to update online status",r);
	}
	
	@Test
	@Ignore
	public void getUserTest(){
		User user=userDaoObj.getUser("animesh@gmail.com");
		assertNotNull("User not found",user);
	}
	
	@Test
	@Ignore
	public void getUsersTest(){
		List<User> list=userDaoObj.getUsers();
		for(User userObj:list){
			System.out.println(userObj.getEmail());
		}
		
		assertTrue("No users are available",list.size()>0);
	}
	
	@Test
	@Ignore
	public void deleteUserTest(){
		User userObj=userDaoObj.getUser("animesh@gmail.com");
		boolean r=userDaoObj.deleteUser(userObj);
		assertTrue("User not found to delete",r);
	}
	
	@Test
	@Ignore
	public void updateUserTest(){
		User userObj=userDaoObj.getUser("anubhav@gmail.com");
		userObj.setContactNumber(7896789696l);
		
		boolean r=userDaoObj.updateUser(userObj);
		assertTrue("User not found to update",r);
	}

}



