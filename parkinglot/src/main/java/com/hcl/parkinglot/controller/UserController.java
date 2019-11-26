package com.hcl.parkinglot.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parkinglot.constants.UserConstants;
import com.hcl.parkinglot.dto.LoginRequestdto;
import com.hcl.parkinglot.dto.LoginResponsedto;
import com.hcl.parkinglot.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userController")
@Slf4j
public class UserController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("")
	public ResponseEntity<Optional<LoginResponsedto>> login(@RequestBody LoginRequestdto  loginRequestdto){
		log.info("Entering into login method of UserController");
		Optional<LoginResponsedto> response= loginService.login(loginRequestdto);
		response.get().setMessage(UserConstants.SUCCESS);
		response.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
