package com.TokChatBackend.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
	
	@Entity
	@Table(name="BlogComment_Tab")
	@SequenceGenerator(name="blogcommentseq",sequenceName="blogcommentidseq")
	
	
	public class BlogComment {
		
		@Id
		@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="blogcommentseq")
		private int commentId;
		private String commentText;
		private String email;
		private Date commentDate;
		private int blogId;
		
		public int getCommentId() {
			return commentId;
		}
		public void setCommentId(int commentId) {
			this.commentId = commentId;
		}
		public String getCommentText() {
			return commentText;
		}
		public void setCommentText(String commentText) {
			this.commentText = commentText;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Date getCommentDate() {
			return commentDate;
		}
		public void setCommentDate(Date commentDate) {
			this.commentDate = commentDate;
		}
		public int getBlogId() {
			return blogId;
		}
		public void setBlogId(int blogId) {
			this.blogId = blogId;
		}

}
