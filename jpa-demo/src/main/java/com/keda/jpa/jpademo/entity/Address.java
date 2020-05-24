package com.keda.jpa.jpademo.entity;

import javax.persistence.*;

@Entity
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phone", nullable = true, length = 11)
    private String phone;

    @Column(name = "zipcode", nullable = true, length = 6)
    private String zipCOde;

    @Column(name = "address", nullable = true, length = 100)
    private String address;

    /**
     * 如果不需要根据Address级联查询People，可以注释掉
     */
    @OneToOne(mappedBy = "address", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private Person person;

}
