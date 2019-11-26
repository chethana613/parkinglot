package com.hcl.parkinglot.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parkinglot.entity.RequestSlotDetails;



@Repository
public interface RequestSlotRepository extends JpaRepository<RequestSlotDetails, Integer>{
  List<RequestSlotDetails> findBySlotId(int slotId);
  Optional<RequestSlotDetails> findBySlotIdAndSlotNeededOnAndUserId(Integer slotId,LocalDate neededOn,Long userId);
}
