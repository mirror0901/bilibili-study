package com.keda.jpa.jpademo.controller;

import com.keda.jpa.jpademo.dao.PersonDao;
import com.keda.jpa.jpademo.entity.Person;
import com.keda.jpa.jpademo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    private PersonDao personDao;
    @Autowired
    private PersonService personService;

    @PostMapping(value = "addPerson")
    public Person addPerson(Person person) {
        Person personResult = personDao.save(person);
        return personResult;
    }

    @DeleteMapping(value = "deletePerson")
    public void deletePerson(Long id) {
        personDao.deleteById(id);
    }

    @GetMapping(value = "findByName")
    public List<Person> findByName(String name) {
        return personDao.findPersonByName(name);
    }

    @GetMapping(value = "findByPage")
    public Page<Person> findByPage(Integer pageOffset, Integer pageSize) {
        Page<Person> personPage = personService.findByPage(pageOffset, pageSize);
        return personPage;
    }

}
