package com.hcl.parkinglot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parkinglot.constants.UserConstants;
import com.hcl.parkinglot.dto.AvailableSlotsResponsedto;
import com.hcl.parkinglot.entity.AvailableSlot;
import com.hcl.parkinglot.service.AvailableSlotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userSlot")
@Slf4j
public class AvailableSlotController {
	
	@Autowired
	AvailableSlotService availableSlotService;
	
	@GetMapping("/availableSlots")
	public ResponseEntity<AvailableSlotsResponsedto> getAllAvailableSlots(){
		log.info("Entering into getAllAvailableSlots of UserSlotController");
		Optional<List<AvailableSlot>> response=availableSlotService.getAllAvailableSlots();
		AvailableSlotsResponsedto availableSlotsResponsedto= new AvailableSlotsResponsedto();
		if(response.get().isEmpty()) {
			availableSlotsResponsedto.setMessage("No Available Slots");
			availableSlotsResponsedto.setStatusCode(HttpStatus.NOT_FOUND.value());	
		}
		else {
			availableSlotsResponsedto.setMessage(UserConstants.SUCCESS);
			availableSlotsResponsedto.setStatusCode(HttpStatus.OK.value());	
			availableSlotsResponsedto.setAvailableSlots(response.get());
		}
		return new ResponseEntity<>(availableSlotsResponsedto,HttpStatus.OK);
		
	}
	
}
