package com.guli.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel(value = "统一返回对象")
public class ResponseResult {
    @ApiModelProperty(value = "成功")
    private Boolean success;
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "信息")
    private String message;
    @ApiModelProperty(value = "返回结果对象")
    private Map<String,Object> responseBody = new HashMap<>();

    public static ResponseResult ok(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResultCode.OK);
        responseResult.setSuccess(true);
        responseResult.setMessage("success");
        return responseResult;
    }

    public static ResponseResult error(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(ResultCode.ERROR);
        responseResult.setSuccess(false);
        responseResult.setMessage("error");
        return responseResult;
    }

    public ResponseResult success(Boolean flag){
        this.setSuccess(flag);
        return this;
    }
    public ResponseResult code(Integer flag){
        this.setCode(flag);
        return this;
    }
    public ResponseResult message(String flag){
        this.setMessage(flag);
        return this;
    }
    public ResponseResult responseBody(Map<String,Object> flag){
        this.setResponseBody(flag);
        return this;
    }
    public ResponseResult date(String key,Object o){
        this.responseBody.put(key,o);
        return this;
    }
}
