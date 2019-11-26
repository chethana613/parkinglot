package com.hcl.parkinglot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.parkinglot.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUserMailAndPass(String userMail,String pass);
	Optional<User> findByUserId(Long userId);
}
