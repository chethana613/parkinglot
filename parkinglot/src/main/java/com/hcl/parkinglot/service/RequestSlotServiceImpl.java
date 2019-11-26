package com.hcl.parkinglot.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parkinglot.constants.ParkingSlotConstants;
import com.hcl.parkinglot.dto.RequestSlotResponsedto;
import com.hcl.parkinglot.dto.RequestSlotdto;
import com.hcl.parkinglot.entity.AvailableSlot;
import com.hcl.parkinglot.entity.RequestSlotDetails;
import com.hcl.parkinglot.exception.SlotException;
import com.hcl.parkinglot.repository.AvailableSlotsRepository;
import com.hcl.parkinglot.repository.RequestSlotRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestSlotServiceImpl implements RequestSlotService{

	@Autowired
	RequestSlotRepository requestSlotRepository;
	
	@Autowired
	AvailableSlotsRepository availableSlotsRepository;

	
	
	public Optional<RequestSlotResponsedto> requestSlot(RequestSlotdto requestSlotdto) throws SlotException {

		log.info("Entering into requestSlot of RequestSlotServiceImpl");
		RequestSlotResponsedto requestSlotResponsedto= new RequestSlotResponsedto();
		if(LocalDate.now().isBefore(requestSlotdto.getSlotNeededFrom())) {
			
			Integer neededDays = requestSlotdto.getNeededDays();
			LocalDate fromDate = requestSlotdto.getSlotNeededFrom();
			LocalDate neededDate;
			while(neededDays>0) {
				neededDate=fromDate.plusDays(neededDays-1);
				Optional<AvailableSlot> availableSlot = availableSlotsRepository.findByslotIdAndOccupiedDateAndAvailableStatus(requestSlotdto.getSlotId(), neededDate, ParkingSlotConstants.STATUS_VACANT);
				
				//Before raising a request check if the slot is available for the requested date
				if(availableSlot.isPresent()) {
					
				Optional<RequestSlotDetails> requestSlot=requestSlotRepository.findBySlotIdAndSlotNeededOnAndUserId(requestSlotdto.getSlotId(), neededDate, requestSlotdto.getUserId());
				RequestSlotDetails requestSlotDetails= null;
				
				//Logic to check if the request is already raised.
				if(!requestSlot.isPresent()) {
				requestSlotDetails= new RequestSlotDetails();
				requestSlotDetails.setSlotId(requestSlotdto.getSlotId());
				requestSlotDetails.setSlotNeededOn(neededDate);
				requestSlotDetails.setUserId(requestSlotdto.getUserId());
				requestSlotRepository.save(requestSlotDetails);
				}
				
				}
				neededDays--;
			}
			
		}
		else {
			throw new SlotException("Your Requested Date is Expired");
		}
		
		return Optional.of(requestSlotResponsedto);
	}
	 
}
