package com.devsu.person.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private Long id;
    private String clientId;
    private String password;
    private Boolean status;
}
