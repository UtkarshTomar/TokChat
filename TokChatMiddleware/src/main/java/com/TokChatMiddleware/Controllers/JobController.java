package com.TokChatMiddleware.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RestController;

import com.TokChatBackend.daos.JobDao;
import com.TokChatBackend.models.Job;

@RestController
public class JobController {
	
	@Autowired
	JobDao jobDao;
	
	@Autowired
	HttpSession session;
	
	@PostMapping(value="/addJob")
	public ResponseEntity<String> addJob(@RequestBody Job job)
	{
		if(jobDao.addJob(job)){
			return new ResponseEntity<String>("Job added succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in adding Job",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@GetMapping(value="/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable int jobId){
		
		Job job=jobDao.getJob(jobId);
		if(jobDao.deleteJob(job)){
			return new ResponseEntity<String>("Job Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Job",HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/updateJob")
	public ResponseEntity<String> updateJob(@RequestBody Job job){
		System.out.println(job);
	if(jobDao.updateJob(job)){
			return new ResponseEntity<String>("Job Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating job",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable int jobId){
		
		Job job=jobDao.getJob(jobId);
		if(job==null){
			System.out.println("Blog Not Found");
			return new ResponseEntity<Job>(job,HttpStatus.NOT_FOUND);
		}
		else{
			System.out.println("Job Found "+job.getJobDesignation());
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	}
	@GetMapping(value="/listJobs")
	public ResponseEntity<List<Job>> getListBlogs(HttpSession session){
		List<Job> listJobs=null;
		//User user=(User)session.getAttribute("userObj");
		
			listJobs=jobDao.listAlljobs();
			if(listJobs.size()>0){
				return new ResponseEntity<List<Job>>(listJobs,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<Job>>(listJobs,HttpStatus.NOT_FOUND);
			}
		
	}
	
}

