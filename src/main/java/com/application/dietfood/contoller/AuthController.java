package com.application.dietfood.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.application.dietfood.Dto.ResponseUser;
import com.application.dietfood.Dto.SocialUser;
import com.application.dietfood.Dto.UserDTO;
import com.application.dietfood.entityClass.User;
import com.application.dietfood.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseUser register(@RequestBody User user) {
    	ResponseUser responseUser = userService.registerUser(user);
        return responseUser;
    }
    
    @PostMapping("/register-google")
    public ResponseUser registerGoogleUser(@RequestBody SocialUser socialUser) {
        ResponseUser responseUser = userService.registerGoogleUser(socialUser);
        return responseUser;
    }
    
    @PostMapping("/login")
    public ResponseUser login(@RequestBody UserDTO user) {
    	ResponseUser responseLogin = new ResponseUser();
    	responseLogin = userService.validateUser(user);
        return responseLogin;
    }
    
    
}

