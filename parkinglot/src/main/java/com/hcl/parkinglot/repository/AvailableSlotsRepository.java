package com.hcl.parkinglot.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parkinglot.entity.AvailableSlot;

@Repository
public interface AvailableSlotsRepository extends JpaRepository<AvailableSlot, Integer> {
	Optional<AvailableSlot> findByslotId(Integer slotId);
	Optional<AvailableSlot> findByslotIdAndOccupiedDateAndAvailableStatus(int slotId,LocalDate neededDate,String status);
	Optional<AvailableSlot> findByslotIdAndOccupiedDate(Integer slotId,LocalDate occupiedDate);
	List<AvailableSlot> findByOccupiedDate(LocalDate occupiedDate);
}
