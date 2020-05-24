package com.keda.jpa.jpademo.dao;

import com.keda.jpa.jpademo.entity.User;

import java.util.ArrayList;
import java.util.List;

import com.keda.jpa.jpademo.entity.Authority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthorityDao authorityDao;

    @Test
    public void saveAuthority() {
        Authority authority = new Authority();
        authority.setId(2L);
        authority.setName("ROLE_ADMIN");
        authorityDao.save(authority);
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("admin1");
        user.setPassword("123456");
        Authority authority = authorityDao.findById(2L).get();
        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(authority);
        user.setAuthorityList(authorityList);
        userDao.save(user);
    }

}
