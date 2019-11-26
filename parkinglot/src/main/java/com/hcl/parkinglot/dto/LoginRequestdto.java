package com.hcl.parkinglot.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequestdto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2871363915998616959L;
	private String userMail;
	private String pass;
}
