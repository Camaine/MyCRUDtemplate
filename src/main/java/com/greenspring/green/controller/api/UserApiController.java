package com.greenspring.green.controller.api;

import com.greenspring.green.model.RoleType;
import com.greenspring.green.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greenspring.green.dto.ResponseDTO;
import com.greenspring.green.model.User;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("Call UserApiController : save");
		//In here insert DB and return value below at line
		user.setRole(RoleType.USER);
		userService.userRegister(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}

	@PostMapping("/api/user/login")
	public ResponseDTO<Integer> login(@RequestBody User user) {
		System.out.println("Call UserApiController : login");
		User principal = userService.userLogin(user);

		if(principal != null){
			session.setAttribute("principal", principal);
		}
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}
}
 