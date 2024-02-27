package com.example.gdsc.data.repository;


import com.example.gdsc.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User getByUid(String uid);

}