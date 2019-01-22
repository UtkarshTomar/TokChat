package com.TokChatBackend.daos;

import com.TokChatBackend.models.ProfilePicture;


	public interface ProfilePictureDao {

		void uploadProfilePicture(ProfilePicture profilePicture);

		ProfilePicture getImage(String email);
	}
