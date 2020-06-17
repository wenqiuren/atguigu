package com.guli.teacher.controller;


import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-06-17
 */
@RestController
@RequestMapping("/teacher")
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @GetMapping("/list")
    public List<EduTeacher> list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return list;
    }

    @DeleteMapping("/del/{id}")
    public boolean delById(@PathVariable("id") String id){
//        boolean b = eduTeacherService.removeById(id);
//        return b;
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        eduTeacher.setIsDeleted(1);
        boolean update = eduTeacherService.saveOrUpdate(eduTeacher);
        return update;
    }
}

