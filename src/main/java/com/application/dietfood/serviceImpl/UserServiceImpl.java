package com.application.dietfood.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.dietfood.Dto.ResponseUser;
import com.application.dietfood.Dto.SocialUser;
import com.application.dietfood.Dto.UserDTO;
import com.application.dietfood.entityClass.User;
import com.application.dietfood.repository.UserRepository;
import com.application.dietfood.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseUser registerUser(User user) {
		ResponseUser responseUser = new ResponseUser();
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
		responseUser.setStatus("success");
		responseUser.setUsername(user.getUsername());
		return responseUser;
	}

	
	@Override
	public ResponseUser validateUser(UserDTO userLogin) {
	    ResponseUser responseUser = new ResponseUser();
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    // Fetch user from the repository by username
	    User user = userRepository.findByUsername(userLogin.getUsername());
	    if (user == null) {
	        // User not found
	        responseUser.setStatus("user not found");
	        return responseUser;
	    }
	    // Retrieve the encoded password from the database
	    String storedPasswordHash = user.getPassword();
	    // Check if the entered password matches the stored encoded password
	    if (passwordEncoder.matches(userLogin.getPassword(), storedPasswordHash)) {
	        // Password matches
	        responseUser.setStatus("success");
	    } else {
	        // Password does not match
	        responseUser.setStatus("invalid password");
	    }

	    return responseUser;
	}


	@Override
    public ResponseUser registerGoogleUser(SocialUser socialUser) {
        ResponseUser responseUser = new ResponseUser();
        // Assuming `SocialUser` has fields like `email`, `name`, and `id`
        User user = new User();
        user.setEmail(socialUser.getEmail());
        user.setUsername(socialUser.getName()); // Or use another suitable field
        // Generate a default password or use OAuth token for authentication
        user.setPassword(""); // Handle this according to your application's needs
        user.setStatus("New"); // Or any other default status
        userRepository.save(user);
        responseUser.setStatus("success");
        responseUser.setUsername(user.getUsername());
        return responseUser;
    }

}
