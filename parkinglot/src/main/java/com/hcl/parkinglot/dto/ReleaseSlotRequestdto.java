package com.hcl.parkinglot.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReleaseSlotRequestdto implements Serializable{
	private static final long serialVersionUID = 1L;
	private LocalDate validFrom;
	private Integer slotId;
	private Integer availableDays;
	private Long ownerId;
	
}
