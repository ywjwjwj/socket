package com.yangood.socket.boot.bean;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author ywj
 * @ClassName Response
 * @Deseription TODO
 * @date 2022/3/1 15:30
 * @description : 统一返回结果类
 */
@Data
public class Response {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    //私有构造方法
    private Response(){}

    //为了链式编程
//    Response.ok().Code().Message()....

    //成功静态方法
    public static Response ok(){
        Response r = new Response();
        r.setSuccess(true);
        r.setCode(1);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static Response error(){
        Response r = new Response();
        r.setSuccess(false);
        r.setCode(0);
        r.setMessage("失败");
        return r;
    }

    //谁调用就返回谁
    public Response success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public Response message(String message){
        this.setMessage(message);
        return this;
    }

    public Response code(Integer code){
        this.setCode(code);
        return this;
    }

    public Response data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Response data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
