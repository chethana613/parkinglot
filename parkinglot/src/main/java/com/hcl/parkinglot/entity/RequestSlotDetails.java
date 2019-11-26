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
@Table(name="request_slot")
@Getter
@Setter
@NoArgsConstructor
public class RequestSlotDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer requestId;
	private Integer slotId;
	private LocalDate slotNeededOn;
	private Long userId;
	
}
