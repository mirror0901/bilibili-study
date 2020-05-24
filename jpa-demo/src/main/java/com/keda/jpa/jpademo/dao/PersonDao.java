package com.keda.jpa.jpademo.dao;

import com.keda.jpa.jpademo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonDao extends JpaRepository<Person, Long> {

    @Query
    List<Person> findPersonByName(String name);

}
