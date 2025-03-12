package com.devsu.person.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Gender gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;

    enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}