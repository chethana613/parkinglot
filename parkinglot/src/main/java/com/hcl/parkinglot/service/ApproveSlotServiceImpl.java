package com.hcl.parkinglot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hcl.parkinglot.constants.ParkingSlotConstants;
import com.hcl.parkinglot.dto.ApproveSlotResponsedto;
import com.hcl.parkinglot.entity.AvailableSlot;
import com.hcl.parkinglot.entity.RequestSlotDetails;
import com.hcl.parkinglot.exception.SlotException;
import com.hcl.parkinglot.exception.UserException;
import com.hcl.parkinglot.repository.AvailableSlotsRepository;
import com.hcl.parkinglot.repository.RequestSlotRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApproveSlotServiceImpl implements ApproveSlotService{

	@Autowired
	RequestSlotRepository requestSlotRepository;
	
	@Autowired
	AvailableSlotsRepository availableSlotsRepository;
	Random random = new Random();
	
	
	
	//@Scheduled(cron="0 0/2 * * * ?")//For every 5 minutes
	@Scheduled(cron="0 0 0/13 * * ?")
	public Optional<ApproveSlotResponsedto> approveSlot() throws SlotException{
		log.info("Entering into approveSlot of ApproveSlotServiceImpl");
		ApproveSlotResponsedto ApproveSlotResponsedto= new ApproveSlotResponsedto();
		List<RequestSlotDetails> requestedSlotList;
		List<Long> requestedUsersList = new ArrayList<>(); 
		LinkedHashSet<Long> requestedUsershashSet = new LinkedHashSet<>(requestedUsersList);//assigning into set to remove duplicates
		List<Long> requestedUsers = new ArrayList<>(requestedUsershashSet); 
		
		//Logic to get the slots for tomorrows available slot
		
		List<AvailableSlot> availableSlotOptional=availableSlotsRepository.findByOccupiedDate(LocalDate.now().plusDays(2));
		List<Integer> slotIds=new ArrayList<>();
		for(AvailableSlot index:availableSlotOptional) {
			slotIds.add(index.getSlotId());
			//Logic to check if any requests raised for 
			requestedSlotList=requestSlotRepository.findBySlotId(index.getSlotId());
			if(!requestedSlotList.isEmpty()) {
				for(RequestSlotDetails requestSlot:requestedSlotList) {
					requestedUsers.add(requestSlot.getUserId());
				}
			}			
		}		
		if(!requestedUsers.isEmpty()) {
			Long randomUser=requestedUsers.get(random.nextInt(requestedUsers.size()));
			
			for(Integer slotId:slotIds) {
			Optional<AvailableSlot> optionalSlot= availableSlotsRepository.findByslotIdAndOccupiedDate(slotId,LocalDate.now().plusDays(2));
			if(!optionalSlot.isPresent()) {
				throw new SlotException("Parking Slot is not Available for the Date");
			}
			AvailableSlot availableslot=optionalSlot.get();			
			availableslot.setGuestId(randomUser);
			availableslot.setAvailableStatus(ParkingSlotConstants.STATUS_OCCUPIED);
			requestedUsers.remove(randomUser);
			availableSlotsRepository.save(availableslot);
			
			}
		}
		else {
			throw new UserException("No User Requests for the available slot");
		}
				
		return Optional.of(ApproveSlotResponsedto);
		
	}
}
