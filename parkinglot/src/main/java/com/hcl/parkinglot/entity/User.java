package com.hcl.parkinglot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	private String userMail;
	private String pass;
	private Long phone;
	private Integer hclExperience;
	private Integer overallExperience;
	private Integer role;
	
	/*
	 * @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy =
	 * "user") private SlotDetails slotDetails;
	 */

}
