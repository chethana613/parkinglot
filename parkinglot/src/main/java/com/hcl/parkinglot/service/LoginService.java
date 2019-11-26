package com.hcl.parkinglot.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.parkinglot.dto.LoginRequestdto;
import com.hcl.parkinglot.dto.LoginResponsedto;

@Service
public interface LoginService {
	public Optional<LoginResponsedto> login(LoginRequestdto loginRequestdto);
}
