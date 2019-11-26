package com.hcl.parkinglot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.parkinglot.constants.UserConstants;
import com.hcl.parkinglot.dto.RequestSlotResponsedto;
import com.hcl.parkinglot.dto.RequestSlotdto;
import com.hcl.parkinglot.exception.SlotException;
import com.hcl.parkinglot.service.RequestSlotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/requestSlotController")
@Slf4j
public class RequestSlotController {
	
	@Autowired
	RequestSlotService requestSlotService;

	@PostMapping("/request")
	public ResponseEntity<Optional<RequestSlotResponsedto>> requestSlot(@RequestBody RequestSlotdto requestSlotdto) throws SlotException{
		log.info("Entering into requestSlot of RequestSlotController");
		Optional<RequestSlotResponsedto> response= requestSlotService.requestSlot(requestSlotdto);
		response.get().setMessage(UserConstants.SUCCESS);
		response.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
