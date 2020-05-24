package com.keda.jpa.jpademo.service;

import com.keda.jpa.jpademo.dao.PersonDao;
import com.keda.jpa.jpademo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public Page<Person> findByPage(Integer pageOffset, Integer pageSize) {
        if (null == pageOffset) {
            pageOffset = 0;
        }
        if (null == pageSize) {
            pageSize = 10;
        }
        PageRequest pageAble = PageRequest.of(pageOffset, pageSize, Sort.Direction.DESC,"id");
        Page<Person> personPage = personDao.findAll(pageAble);
        return personPage;
    }

}
