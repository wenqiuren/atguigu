package com.guli.teacher.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherQuery {
    private String name;

    private Integer level;

    private Date gmtCreate;

    private Date gmtModified;
}
