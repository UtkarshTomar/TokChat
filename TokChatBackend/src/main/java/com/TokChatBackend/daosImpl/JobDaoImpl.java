package com.TokChatBackend.daosImpl;

import java.util.List;








import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.TokChatBackend.daos.JobDao;
import com.TokChatBackend.models.ApplyJob;
import com.TokChatBackend.models.Job;




@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean addJob(Job job) {
		try{
			Session session =sessionFactory.getCurrentSession();
			session.save(job);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return false;
	}
	

	@Override
	public boolean deleteJob(Job job) {
		try{
			Session session =sessionFactory.getCurrentSession();
			session.delete(job);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return false;
	}

	@Override
	public boolean updateJob(Job job) {
		try{
			Session session =sessionFactory.getCurrentSession();
			session.update(job);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return false;
	}

	@Override
	public Job getJob(int jobId) {
		try{
			Session session =sessionFactory.getCurrentSession();
			Job jobobj=(Job)session.get(Job.class, jobId);
			return jobobj;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

	@Override
	public List<Job> listAlljobs() {
		try{
			
			Session session =sessionFactory.getCurrentSession();
			Query query=session.createQuery("from Job");
			return query.list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

	@Override
	public boolean applyJob(ApplyJob applyJob) {
		try{
			Session session =sessionFactory.getCurrentSession();
			session.saveOrUpdate(applyJob);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return false;
	}

	@Override
	public List<ApplyJob> getAllAppliedjobsDetails() {
		try{
			Session session =sessionFactory.getCurrentSession();
			session.createQuery("from ApplyJob");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		return null;
	}

}
