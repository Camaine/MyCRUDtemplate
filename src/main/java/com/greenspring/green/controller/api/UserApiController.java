package com.greenspring.green.controller.api;

import com.greenspring.green.model.TwtUser;
import com.greenspring.green.service.TwitterService;
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
	private TwitterService twitterService;

	@PostMapping("/auth/doJoin")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("Call UserApiController : save");
		userService.userRegister(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}

	@PostMapping("/auth/twtJoin")
	public ResponseDTO<Integer> twtSave(@RequestBody TwtUser twtUser) {
		System.out.println("Call UserApiController : twtsave");
		twitterService.twtUserRegister(twtUser);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(),1); // change Java Object to JSON
	}
}
 