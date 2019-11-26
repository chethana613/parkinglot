package com.hcl.parkinglot.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parkinglot.constants.ParkingSlotConstants;
import com.hcl.parkinglot.constants.UserConstants;
import com.hcl.parkinglot.dto.ReleaseSlotRequestdto;
import com.hcl.parkinglot.dto.ReleaseSlotResponsedto;
import com.hcl.parkinglot.entity.AvailableSlot;
import com.hcl.parkinglot.entity.SlotDetails;
import com.hcl.parkinglot.entity.User;
import com.hcl.parkinglot.entity.UserParkingSlot;
import com.hcl.parkinglot.exception.SlotException;
import com.hcl.parkinglot.exception.UserException;
import com.hcl.parkinglot.repository.AvailableSlotsRepository;
import com.hcl.parkinglot.repository.SlotRepository;
import com.hcl.parkinglot.repository.UserParkingSlotRepository;
import com.hcl.parkinglot.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReleaseSlotServiceImpl implements ReleaseSlotService {
	
	@Autowired
	AvailableSlotsRepository availableSlotsRepository;
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	SlotRepository slotRepository;
	
	@Autowired
	UserParkingSlotRepository userParkingSlotRepository;
	
	public Optional<ReleaseSlotResponsedto> releaseSlot(ReleaseSlotRequestdto releaseSlotRequestdto) throws SlotException{
		log.info("Entered into releaseSlot of ReleaseSlotServiceImpl");
		
		Optional<User> userResponse=userRepository.findByUserId(releaseSlotRequestdto.getOwnerId());
		Optional<SlotDetails> slotResponse=slotRepository.findById(releaseSlotRequestdto.getSlotId());
		
		ReleaseSlotResponsedto releaseSlotResponsedto= new ReleaseSlotResponsedto();
		if(!userResponse.isPresent()) {
			throw new UserException(UserConstants.INVALID_USER);
		}
		if(!slotResponse.isPresent()) {
			throw new SlotException(ParkingSlotConstants.INVALID_SLOT);
		}
		Optional<UserParkingSlot> userParkingSlotResponse=userParkingSlotRepository.findBySlotAndUser(slotResponse.get(), userResponse.get());
		
		if(!userParkingSlotResponse.isPresent()) {
			throw new SlotException(ParkingSlotConstants.INVALID_USER_SLOT);
		}
		else {
			if(userResponse.get().getHclExperience() < UserConstants.HCL_EXPERIENCE_THRESHOLD ) {
				throw new UserException("Cannot Perform Operation:Insufficient HCL Threshold Experience");
			}
			else {
				LocalDate now=LocalDate.now();
				Integer availableDays=releaseSlotRequestdto.getAvailableDays();
				LocalDate toDate=releaseSlotRequestdto.getValidFrom().plusDays(releaseSlotRequestdto.getAvailableDays()-1);
				if(now.isBefore(toDate)) {			
					while(availableDays>0) {					
						LocalDate occupiedDate=releaseSlotRequestdto.getValidFrom().plusDays(availableDays-1);
						Optional<AvailableSlot> optionalSlot= availableSlotsRepository.findByslotIdAndOccupiedDate(releaseSlotRequestdto.getSlotId(), occupiedDate);
						AvailableSlot availableSlot= null;
						//Logic to check if the slots were already released for the date
						if(optionalSlot.isPresent()) {
							availableSlot=	optionalSlot.get();
						}
						else {
							availableSlot= new AvailableSlot();
							availableSlot.setAvailableStatus("vacant");
						}			
						availableSlot.setOccupiedDate(occupiedDate);
						availableSlot.setOwnerId(releaseSlotRequestdto.getOwnerId());
						availableSlot.setSlotId(releaseSlotRequestdto.getSlotId());
						availableSlotsRepository.save(availableSlot);
						availableDays--;
					}				
				}
				else {
					throw new UserException("Cannot Perform Operation:Release Date Expired");
				}
			}
			
		}		
		return Optional.of(releaseSlotResponsedto);
	}
	

}
