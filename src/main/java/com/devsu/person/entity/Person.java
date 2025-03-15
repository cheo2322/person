package com.devsu.person.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private Integer age;

  @Column(unique = true)
  private String identification;

  private String address;
  private String phone;

  public enum Gender {
    MALE,
    FEMALE,
    OTHER
  }
}
