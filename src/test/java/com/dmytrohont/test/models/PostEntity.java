package com.dmytrohont.test.models;

import lombok.Data;


@Data
public class PostEntity {

    private int id;
    private String title;
    private String content;
    private String createdDate;
}
