package com.hcl.parkinglot.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponsedto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2378437365341359709L;
	private String message;
	private Integer statusCode;
}
