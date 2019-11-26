package com.hcl.parkinglot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.parkinglot.entity.AvailableSlot;
import com.hcl.parkinglot.repository.AvailableSlotsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AvailableSlotServiceImpl implements AvailableSlotService{
	
	@Autowired
	AvailableSlotsRepository availableSlotsRepository;
	
	public Optional<List<AvailableSlot>> getAllAvailableSlots(){
		log.info("Entering into getAllAvailableSlots of AvailableSlotServiceImpl");
		 List<AvailableSlot> availableSlotList= availableSlotsRepository.findAll();
		 return Optional.of(availableSlotList); 
	}

}
