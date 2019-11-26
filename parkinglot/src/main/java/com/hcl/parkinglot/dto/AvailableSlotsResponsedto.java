package com.hcl.parkinglot.dto;

import java.util.List;

import com.hcl.parkinglot.entity.AvailableSlot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AvailableSlotsResponsedto {
	
	private String message;
	private Integer statusCode;
	private List<AvailableSlot> availableSlots;
}
