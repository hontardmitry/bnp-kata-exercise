package com.dmytrohont.test.models;

import lombok.Data;

import java.time.Instant;


@Data
public class PostEntity {

    private int id;
    private String title;
    private String content;
    private Instant createdDate;
}
