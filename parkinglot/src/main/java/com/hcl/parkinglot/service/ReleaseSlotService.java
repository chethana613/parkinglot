package com.hcl.parkinglot.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.parkinglot.dto.ReleaseSlotRequestdto;
import com.hcl.parkinglot.dto.ReleaseSlotResponsedto;
import com.hcl.parkinglot.exception.SlotException;

@Service
public interface ReleaseSlotService {
	public Optional<ReleaseSlotResponsedto> releaseSlot(ReleaseSlotRequestdto releaseSlotRequestdto) throws SlotException;
}
