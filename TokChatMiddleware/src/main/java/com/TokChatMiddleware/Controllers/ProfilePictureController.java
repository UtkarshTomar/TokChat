package com.TokChatMiddleware.Controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.TokChatBackend.daos.ProfilePictureDao;
import com.TokChatBackend.models.ProfilePicture;
import com.TokChatBackend.models.User;

	@RestController
	public class ProfilePictureController {

		@Autowired
		private ProfilePictureDao profilePictureDao;
		
		@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
		public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
			User user=(User) session.getAttribute("userObj");
			String email=user.getEmail();
			if(email==null)
			{
				Error error=new Error("Unauthorised Access..");
				return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);  
			}	
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setEmail(email);
			profilePicture.setImage(image.getBytes());
			profilePictureDao.uploadProfilePicture(profilePicture);
			return new ResponseEntity<ProfilePicture>(profilePicture,HttpStatus.OK);  
		}
		
		@RequestMapping(value="/getimage/{email:.+}",method=RequestMethod.GET)
		public @ResponseBody byte[] getImage(@PathVariable String email,HttpSession session){
		
			ProfilePicture profilePicture=profilePictureDao.getImage(email);
			if(profilePicture==null){
				return null;
			}
			else
				return profilePicture.getImage();
		}
	}
