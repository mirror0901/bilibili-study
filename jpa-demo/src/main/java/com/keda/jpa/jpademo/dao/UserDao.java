package com.keda.jpa.jpademo.dao;

import com.keda.jpa.jpademo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Long> {

}
