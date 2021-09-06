package com.greenspring.green.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greenspring.green.dto.ResponseDTO;
import com.greenspring.green.model.User;

@RestController
public class UserApiController {
	
	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("Call UserApiController");
		//In here insert DB and return value below at line
		return new ResponseDTO<Integer>(HttpStatus.OK,1); // change Java Object to JSON
	}
}
 