package com.teknologiinformasi.restapi.user.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.user.model.User;
 

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}