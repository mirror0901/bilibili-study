/**
 * Copyright (C), 2019-2022, 海安秩明网络科技工作室
 * FileName: StudentService
 * Author:   HYJ
 * Date:     2019/3/11 1:50
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.mongodb.service;

import com.mirror95.mongodb.dao.StudentRepository;
import com.mirror95.mongodb.entity.Student;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: mirror_huang
 * @date: 2019/3/11 01:50
 * @qq: 1755496180
 * @description:
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Student add(Student student) {
        Student student1 = studentRepository.save(student);
        return student;
    }

    public UpdateResult update(Student student) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(Criteria.where("_id").is(student.getId()));
        String collectionsName = "student";
        Update update = new Update();
        update.set("_id", student.getId());
        UpdateResult result = mongoTemplate.updateFirst(query, update, collectionsName);
        return result;
    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }

    public Student findByName(String name) {
        return studentRepository.findStudentByName(name);
    }

    public List<Student> findBySex(String sex) {
        return studentRepository.findStudentsBySex(sex);
    }


}
