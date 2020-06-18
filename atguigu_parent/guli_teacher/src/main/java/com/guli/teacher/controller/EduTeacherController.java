package com.guli.teacher.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.execption.EduExcetion;
import com.guli.common.response.ResponseResult;
import com.guli.common.response.ResultCode;
import com.guli.teacher.entity.EduTeacher;
import com.guli.teacher.entity.TeacherQuery;
import com.guli.teacher.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseResult list(){
        ResponseResult responseResult = new ResponseResult();
        try {
            List<EduTeacher> list = eduTeacherService.list(null);
            Map<String,Object> map = new HashMap<>();
            map.put("list",list);
            responseResult = ResponseResult.ok().responseBody(map);
        }catch (Exception e){
            responseResult = ResponseResult.error().message(e.getMessage());
        }
        return responseResult;
    }

    @DeleteMapping("/del/{id}")
    public ResponseResult delById(@PathVariable("id") String id){
//        boolean b = eduTeacherService.removeById(id);
//        return b;
        ResponseResult responseResult = new ResponseResult();

        try {
            EduTeacher eduTeacher = eduTeacherService.getById(id);
            if(eduTeacher==null){
                throw new EduExcetion(ResultCode.MiSS,"没有此人员");
            }
            eduTeacher.setIsDeleted(1);
            boolean update = eduTeacherService.saveOrUpdate(eduTeacher);
            if(update){
                responseResult = ResponseResult.ok();
            }else{
                responseResult = ResponseResult.error();
            }
        }catch (Exception e){
            responseResult = ResponseResult.error().message(e.getMessage());
        }
        return responseResult;
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page/{page}/{limit}")
    public ResponseResult selectListByPage(@PathVariable("page") Integer page,
                                           @PathVariable("limit")Integer limit
                                           ){
        ResponseResult responseResult = new ResponseResult();
        try {
            Page<EduTeacher> teacherPage = new Page<>(page,limit);
            eduTeacherService.page(teacherPage, null);
            List<EduTeacher> records = teacherPage.getRecords();
            long total = teacherPage.getTotal();
            Map<String,Object> map = new HashMap<>();
            map.put("list",records);
            map.put("total",total);
            responseResult = ResponseResult.ok().responseBody(map);
        } catch (Exception e) {
            responseResult = ResponseResult.error().message(e.getMessage());
        }
        return responseResult;
    }
    @ApiOperation(value = "分页查询条件")
    @PostMapping("/pagequery/{page}/{limit}")
    public ResponseResult selectListByPageQuery(@PathVariable("page") Integer page,
                                           @PathVariable("limit")Integer limit,
                                           @RequestBody TeacherQuery query
    ){
        ResponseResult responseResult = new ResponseResult();
        try {
            Page<EduTeacher> teacherPage = new Page<>(page,limit);
            eduTeacherService.pageQuery(teacherPage, query);
            List<EduTeacher> records = teacherPage.getRecords();
            long total = teacherPage.getTotal();
            Map<String,Object> map = new HashMap<>();
            map.put("list",records);
            map.put("total",total);
            responseResult = ResponseResult.ok().responseBody(map);
        } catch (Exception e) {
            responseResult = ResponseResult.error().message(e.getMessage());
        }
        return responseResult;
    }
    /**
     * 新增
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody EduTeacher teacher){
        ResponseResult responseResult = new ResponseResult();
        try {
            eduTeacherService.save(teacher);
            responseResult = ResponseResult.ok();
        } catch (Exception e) {
            responseResult = ResponseResult.error().message(e.getMessage());
        }
        return responseResult;
    }
}

