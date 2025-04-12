package com.dmytrohont.test.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    //int type as selected here because there is integer($int32) for these fields in Swagger
    private int id;
    private Instant createdDate;
    private String name;
    private String lastName;
    private int role; //it could be short but at this point it does not really make a difference
    private String login;
    private String password;
    private String email;
}
