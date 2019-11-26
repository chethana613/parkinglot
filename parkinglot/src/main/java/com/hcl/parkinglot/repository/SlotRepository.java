package com.hcl.parkinglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parkinglot.entity.SlotDetails;

@Repository
public interface SlotRepository extends JpaRepository<SlotDetails, Integer>{
	//Optional<SlotDetails> findByslotIdAndUser(Integer slotId,User user); 
}
