package com.guli.teacher.handler;

import com.guli.common.execption.EduExcetion;
import com.guli.common.response.ResponseResult;
import com.guli.common.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e){
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResult.error().message("出错了"+e.getMessage());
    }
    @ExceptionHandler(EduExcetion.class)
    @ResponseBody
    public ResponseResult missing(EduExcetion e){
        log.error(ExceptionUtil.getMessage(e));
        return ResponseResult.error().code(e.getCode()).message("出错了"+e.getMessage());
    }
}
