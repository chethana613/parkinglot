package com.hcl.parkinglot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_parking_slot")
@Getter
@Setter
@NoArgsConstructor
public class UserParkingSlot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userParkingSlotId;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_Id", nullable = false)
	private User user;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "slot_Id", nullable = false)
	private SlotDetails slot;
}
