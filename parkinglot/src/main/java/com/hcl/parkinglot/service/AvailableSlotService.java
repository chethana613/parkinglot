package com.hcl.parkinglot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.parkinglot.entity.AvailableSlot;

@Service
public interface AvailableSlotService {
	public Optional<List<AvailableSlot>> getAllAvailableSlots();
}
