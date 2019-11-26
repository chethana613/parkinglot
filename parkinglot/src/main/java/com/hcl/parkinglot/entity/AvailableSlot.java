package com.hcl.parkinglot.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="available_slot")
@Getter
@Setter
@NoArgsConstructor
public class AvailableSlot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer availableSlotId;
	private Integer slotId;
	private LocalDate occupiedDate;
	private Long guestId;
	private String availableStatus;
	private Long ownerId;
	//private LocalDate validFrom;
	//private Integer availableDays;
}
