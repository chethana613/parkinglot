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
import com.hcl.parkinglot.dto.ReleaseSlotRequestdto;
import com.hcl.parkinglot.dto.ReleaseSlotResponsedto;
import com.hcl.parkinglot.exception.SlotException;
import com.hcl.parkinglot.service.ReleaseSlotService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/releaseSlotController")
@Slf4j
public class ReleaseSlotController {

	@Autowired
	ReleaseSlotService releaseSlotService;
	
	@PostMapping("/freeSlot")
	public ResponseEntity<Optional<ReleaseSlotResponsedto>> releaseSlot(@RequestBody ReleaseSlotRequestdto releaseSlotRequestdto) throws SlotException{
		log.info("Entering into releaseSlot of ReleaseSlotsController");
		Optional<ReleaseSlotResponsedto> response= releaseSlotService.releaseSlot(releaseSlotRequestdto);
		response.get().setMessage(UserConstants.SUCCESS);
		response.get().setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
