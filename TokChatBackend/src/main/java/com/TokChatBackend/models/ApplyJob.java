package com.TokChatBackend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
@Table(name="ApplyJob_Tab")
@SequenceGenerator(name="jobapplicationidseq",sequenceName="job_applicationid_sequence")
public class ApplyJob {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="jobapplicationidseq")
	private int applicationId;
	private int jobId;
	private String email;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
	private Date applyDate;
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	@Override
	public String toString() {
		return "ApplyJob [applicationId=" + applicationId + ", jobId=" + jobId
				+ ", email=" + email + ", applyDate=" + applyDate + "]";
	}
	
	
}