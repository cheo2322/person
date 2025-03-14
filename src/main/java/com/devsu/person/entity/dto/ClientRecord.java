package com.devsu.person.entity.dto;

public record ClientRecord(
    String name,
    String gender,
    Integer age,
    String identification,
    String address,
    String phone,
    String clientId,
    String password,
    Boolean status
) {}
