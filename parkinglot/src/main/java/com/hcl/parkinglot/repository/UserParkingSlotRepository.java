package com.hcl.parkinglot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parkinglot.entity.SlotDetails;
import com.hcl.parkinglot.entity.User;
import com.hcl.parkinglot.entity.UserParkingSlot;

@Repository
public interface UserParkingSlotRepository extends JpaRepository<UserParkingSlot, Integer>{
	Optional<UserParkingSlot> findBySlotAndUser(SlotDetails slotId,User user); 
}
