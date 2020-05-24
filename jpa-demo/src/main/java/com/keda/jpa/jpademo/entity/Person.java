package com.keda.jpa.jpademo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author keda
 * 关于自动注入值参考
 * https://www.cnblogs.com/520playboy/p/7552141.html
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String name;

    private Integer age;

    @Column(name = "my_address", nullable = false, length = 22)
    private String myAddress;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String modifiedBy;

    @Transient
    private String phone;

    /**
     * 通过外键的方式
     * person是关系的维护端，当删除people，会级联删除address
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    /**
     * 通过关联表的方式来保存一对一的关系
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "people_address",
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "address1_id"))
    private Address address1;

}
