package com.hcl.parkinglot.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.parkinglot.dto.ApproveSlotResponsedto;
import com.hcl.parkinglot.exception.SlotException;

@Service
public interface ApproveSlotService {
	public Optional<ApproveSlotResponsedto> approveSlot() throws SlotException;
}
