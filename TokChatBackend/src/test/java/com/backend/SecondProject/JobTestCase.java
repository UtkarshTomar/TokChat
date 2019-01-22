package com.backend.SecondProject;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.TokChatBackend.daos.JobDao;
import com.TokChatBackend.models.Job;

public class JobTestCase {
	
static JobDao jobdao;
	
	@BeforeClass
	public static void init(){
	AnnotationConfigApplicationContext context=new 	AnnotationConfigApplicationContext();
	context.scan("com.TokChatBackend");	
	context.refresh();
	
	jobdao=context.getBean("jobDao",JobDao.class);
	}
	
	@Test
	@Ignore
	public void addJob(){
		try{
		 Job job =new Job();
		 job.setCompany("BPl");
		 job.setJobDesc("sql devlopment");
		 job.setJobDesignation("sql Devleoper");
		 job.setLocation("Noida");
		 job.setSalary(44000);
		 
		 String dateToApply="20-09-2018";
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		 job.setLastDateApply(sdf.parse(dateToApply));
		 assertEquals("Job Added Succesfully",true,jobdao.addJob(job));
		 System.out.println("added succesfully");
	}
		catch(Exception e)
		{
			e.printStackTrace();
			}
		}
	
	

}
