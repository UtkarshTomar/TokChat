package com.TokChatBackend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
@SequenceGenerator(name="forumidseq",sequenceName="forumidsequence")
public class Forum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="forumidseq")
	private int forumId;
	
	private String forumName;
	private String forumContent;
	
	private Date createDate;
	private String email;
	private String status;
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getForumName() {
		return forumName;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
	public String getForumContent() {
		return forumContent;
	}
	public void setForumContent(String forumContent) {
		this.forumContent = forumContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Forum [forumId=" + forumId + ", forumName=" + forumName
				+ ", forumContent=" + forumContent + ", createDate="
				+ createDate + ", email=" + email + ", status=" + status + "]";
	}

}
