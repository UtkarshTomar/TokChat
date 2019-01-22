package com.backend.SecondProject;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.TokChatBackend.daos.BlogDao;
import com.TokChatBackend.daosImpl.BlogDaoImpl;
import com.TokChatBackend.models.Blog;
import com.TokChatBackend.models.BlogComment;
import com.TokChatBackend.models.User;

public class BlogTestCase {
static BlogDao blogdao;
	
	@BeforeClass
	public static void init(){
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext();
		app.scan("com.TokChatBackend");
		app.refresh();
		blogdao=app.getBean("blogDao",BlogDao.class);
	}
	
	@Test
	@Ignore
	public void addBlog(){
		Blog blog=new Blog();
		blog.setBlogName("MYblog");
		blog.setBlogContext("qwerty");
		
		Date d=new Date();
		blog.setCreateDate(d);
		blog.setLikes(0);
		blog.setStatus("offline");
		blog.setEmail("rahul");
		
		
		assertTrue("Blog Not Added",blogdao.addBlog(blog));
		
	}
	
	@Test
	@Ignore
	public void deleteBlog(){
		Blog blog=blogdao.getBlog(50);
		boolean r=blogdao.deleteBlog(blog);
         assertTrue("Problem in deleting Blog",r);
        }
         
         
	
        @Test
        @Ignore
        public void updateBlog(){
        	Blog blog=blogdao.getBlog(100);
            blog.setBlogName("Test Name");
            blog.setBlogContext("Test Desc");
            boolean r=blogdao.updateBlog(blog);
             
            assertTrue("Problem in updating Blog",r);
         
        }
    
        
        @Test
        @Ignore
        public void approveBlog(){
        	Blog blog=blogdao.getBlog(100);
        	blog.setStatus("Approved");
        	boolean r=blogdao.approveBlog(blog);
        	
        	assertTrue("cannot be approved",r);
        	
        }
        @Test
        @Ignore
        public void rejectBlog(){
        	Blog blog=blogdao.getBlog(100);
        	blog.setStatus("Rejected");
        	boolean r=blogdao.approveBlog(blog);
        	
        	assertTrue("not found in database",r);
        	
        }
        
        @Test
        @Ignore
        public void incrementLikes(){
        	Blog blog=blogdao.getBlog(100);
        	blog.setLikes(blog.getLikes()+1);
        	boolean r=blogdao.incrementLikes(blog);
        	
        	assertTrue("blog not found",r);
        }
        
        
        
        @Test
        @Ignore
        public void getBlog(){
        	Blog blog=blogdao.getBlog(101);
            assertNotNull("Blog Not Found", blog);
        }
        
        @Test
        @Ignore
        public void getAllApprovedBlogs(){
        	List<Blog> blogs=blogdao.listAllApprovedBlogs();
        	for(Blog blog:blogs)
        	{
        		System.out.println(blog.getBlogContext()+ " " +blog.getStatus());
        	}
        	
        	assertTrue("Approved Blogs Not Found",blogs.size()>0);
        } 
        
        @Test
        @Ignore
        public void getAllPendingBlogs(){
        	List<Blog> blogs=blogdao.listPendingBlogs();
        	for(Blog blog:blogs)
        	{
        		System.out.println(blog.getBlogContext());
        	}
        	
        	assertTrue("Pending Blogs Not Found",blogs.size()>0);
        } 
        @Test
    	@Ignore
    	public void addBlogComment(){
    		BlogComment blogComment=new BlogComment();
    		blogComment.setCommentText("Impressive");
    		blogComment.setEmail("rahul@gmail.com");
    		blogComment.setBlogId(1050);
    		Date d=new Date();
    		blogComment.setCommentDate(d);
    		
    		assertTrue("Blog Comment Not Added",blogdao.addBlogComment(blogComment));
    		
    	}
        @Test
    	@Ignore
    	public void deleteBlogComment(){
    		BlogComment blogComment=blogdao.getBlogComment(50);
    		boolean r=blogdao.deleteBlogComment(blogComment);
             assertTrue("Problem in deleting Comment",r);
            }
        
        @Test
        @Ignore
        public void getBlogComment(){
        	BlogComment blogComment=blogdao.getBlogComment(101);
            assertNotNull("Blog Comment Not Found", blogComment);
        }
        
         
        @Test
		@Ignore
		public void listBlog ()
		{
			List<Blog> blogList=blogdao.listBlogs("Blogtest1@gmail.com","Role_User");
			for(Blog blog:blogList){
				System.out.println(blog.getBlogName()+" "+blog.getBlogContext());
			}
			assertTrue("Blogs Not Found", blogList.size()>0);
			System.out.println("list working....");
			
		}
        
    }
 




