package com.hcl.parkinglot.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestSlotdto implements Serializable{
	
	private static final long serialVersionUID = 2746778038929568433L;
	private Integer slotId;
	private LocalDate slotNeededFrom;
	private Integer neededDays;
	private Long userId;
	

}
