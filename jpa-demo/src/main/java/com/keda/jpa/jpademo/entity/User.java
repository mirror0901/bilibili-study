package com.keda.jpa.jpademo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "账号不能为空")
    @Size(min = 3, max = 20)
    @Column(nullable = false, length = 20, unique = true)
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(max = 100)
    @Column(length = 100)
    private String password;

    /**
     * 1、关系维护端，负责多对多关系的绑定和解除
     * 2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，
     * 关联到关系维护端（User）
     * 3、inverseJoinColumns指定外键的名字，要关联的关系被维护端（Authority）
     * 4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
     * 即 user_authority
     * 关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
     * 关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
     * 主表就是关系维护端对应的表，从表就是关系被维护端对应的表
     */
    @ManyToMany
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<Authority> authorityList;
}
