package com.hcl.parkinglot.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReleaseSlotResponsedto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5831771139383258904L;
	private String message;
	private Integer statusCode;
}
