package com.keda.jpa.jpademo.dao;

import com.keda.jpa.jpademo.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityDao extends JpaRepository<Authority, Long> {
}
