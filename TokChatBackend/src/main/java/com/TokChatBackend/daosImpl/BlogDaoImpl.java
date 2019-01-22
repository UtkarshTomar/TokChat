package com.TokChatBackend.daosImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.TokChatBackend.daos.BlogDao;
import com.TokChatBackend.models.Blog;
import com.TokChatBackend.models.BlogComment;



@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
	
	@Autowired
	SessionFactory sessionfactory;
	

	@Override
	public boolean addBlog(Blog blogobj) {
		try{
			System.out.println(blogobj.getBlogName()+" "+blogobj.getBlogContext());
			Session session=sessionfactory.getCurrentSession();
			session.saveOrUpdate(blogobj);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.delete(blog);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBlog(Blog blog) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.update(blog);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public Blog getBlog(int blogId) {
		try{
			Session session=sessionfactory.getCurrentSession();
		Blog blog =(Blog)session.get(Blog.class, blogId);	
		return blog; 
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean approveBlog(Blog blog) {
		try{
			Session session=sessionfactory.getCurrentSession();
			blog.setStatus("Approved");
			session.update(blog);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean rejectBlog(Blog blog) {
		try{
			Session session=sessionfactory.getCurrentSession();
			blog.setStatus("Rejected");
			session.update(blog);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Blog> listBlogs(String email,String role) {
		try{
			Session session=sessionfactory.getCurrentSession();
			Query querry=null;
			if(role.equals("Role_User")){
			querry=session.createQuery("from Blog where email=:abc");
			querry.setParameter("abc",email);
			}
			else {
			querry=session.createQuery("from Blog");	
			}
			List<Blog> blogList=querry.list();
			return blogList;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Blog> listAllApprovedBlogs() {
		try{
			Session session=sessionfactory.getCurrentSession();
			Query querry=session.createQuery("from Blog where status='Approved'");
			List<Blog> blogList=querry.list();
			return blogList;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Blog> listPendingBlogs() {
		try{
			Session session=sessionfactory.getCurrentSession();
			Query querry=session.createQuery("from Blog where status='Pending'");
			List<Blog> blogList=querry.list();
			return blogList;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean incrementLikes(Blog blog) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.update(blog);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addBlogComment(BlogComment blogComment) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.saveOrUpdate(blogComment);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBlogComment(BlogComment blogComment) {
		try{
			Session session=sessionfactory.getCurrentSession();
			session.delete(blogComment);
			return true;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
	}

	@Override
	public BlogComment getBlogComment(int commentId) {
		try{
			Session session=sessionfactory.getCurrentSession();
			BlogComment blogcom=(BlogComment)session.get(BlogComment.class, commentId);
			return blogcom;
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BlogComment> listBlogComments(int blogId) {
		try{
				Session session=sessionfactory.getCurrentSession();
				Query querry=session.createQuery("from BlogComment");
				List<BlogComment> blogCommentList=querry.list();
				return blogCommentList;
			
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return null;
	}

}
