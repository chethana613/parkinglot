package com.hcl.parkinglot.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parkinglot.constants.UserConstants;
import com.hcl.parkinglot.dto.LoginRequestdto;
import com.hcl.parkinglot.dto.LoginResponsedto;
import com.hcl.parkinglot.entity.User;
import com.hcl.parkinglot.exception.UserException;
import com.hcl.parkinglot.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	UserRepository userRepository;
	
	public Optional<LoginResponsedto> login(LoginRequestdto loginRequestdto){
		log.info("Entered into login of LogiServiceImpl");
		Optional<User> loginResponse=userRepository.findByUserMailAndPass(loginRequestdto.getUserMail(),loginRequestdto.getPass());
		if(!loginResponse.isPresent()) {
			throw new UserException(UserConstants.INVALID_LOGIN);
		}
		LoginResponsedto response= new LoginResponsedto();
		BeanUtils.copyProperties(loginResponse.get(), response);
		return Optional.of(response);
		
	}

}
