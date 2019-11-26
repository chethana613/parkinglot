package com.hcl.parkinglot.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.parkinglot.dto.RequestSlotResponsedto;
import com.hcl.parkinglot.dto.RequestSlotdto;
import com.hcl.parkinglot.exception.SlotException;

@Service
public interface RequestSlotService {
	public Optional<RequestSlotResponsedto> requestSlot(RequestSlotdto requestSlotdto) throws SlotException;
}
