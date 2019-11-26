package com.hcl.parkinglot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parkinglot.constants.UserConstants;
import com.hcl.parkinglot.dto.ApproveSlotResponsedto;
import com.hcl.parkinglot.exception.SlotException;
import com.hcl.parkinglot.service.ApproveSlotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/adminController")
@Slf4j
public class ApproveSlotController {
	
	@Autowired
	ApproveSlotService approveSlotService;
	
	@PutMapping("/approve/{slotId}")
	public ResponseEntity<Optional<ApproveSlotResponsedto>> approveSlot() throws SlotException{	
		log.info("Entering into approveSlot of ApproveSlotController");
		Optional<ApproveSlotResponsedto> response=approveSlotService.approveSlot();
		response.get().setMessage(UserConstants.SUCCESS);
		response.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}

}
