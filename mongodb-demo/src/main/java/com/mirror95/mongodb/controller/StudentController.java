/**
 * Copyright (C), 2019-2022, 海安秩明网络科技工作室
 * FileName: StudentController
 * Author:   HYJ
 * Date:     2019/3/11 1:56
 * Description:
 * History:
 * <author>          <微信号>          <version>          <工作室官网>
 * mirror_huang     wwwmirror95top      1.0.0            http://www.mirror95.top
 * 海安秩明网络科技工作室致力于企业网站设计、制作、源码出售、SEO推广，爬虫，微信公众号、小程序开发。
 * 希望为中小微型企业、个体户拓宽销售渠道，紧追互联网+潮流，实现合作共赢。
 * 秩明网络愿与您合作，共创美好生活。
 */
package com.mirror95.mongodb.controller;

import com.mirror95.mongodb.entity.Student;
import com.mirror95.mongodb.service.StudentService;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: mirror_huang
 * @date: 2019/3/11 01:56
 * @qq: 1755496180
 * @description:
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("add")
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping("update")
    public UpdateResult update(@RequestBody Student student) {
        return studentService.update(student);
    }

    @GetMapping("findByName")
    public Student findByName(String name) {
        return studentService.findByName(name);
    }

    @GetMapping("findBySex")
    public List<Student> findBySex(String sex) {
        return studentService.findBySex(sex);
    }

    @DeleteMapping("delete")
    public String delete(String id) {
        studentService.delete(id);
        return "success";
    }

}
