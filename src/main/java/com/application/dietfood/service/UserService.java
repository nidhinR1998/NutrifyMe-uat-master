package com.application.dietfood.service;

import com.application.dietfood.Dto.ResponseUser;
import com.application.dietfood.Dto.SocialUser;
import com.application.dietfood.Dto.UserDTO;
import com.application.dietfood.entityClass.User;


public interface UserService {

	ResponseUser registerUser(User user);

	ResponseUser validateUser(UserDTO user);

	ResponseUser registerGoogleUser(SocialUser socialUser);

//	User registerUser(User user);

}
