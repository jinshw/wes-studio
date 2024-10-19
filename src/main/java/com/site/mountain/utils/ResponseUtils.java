package com.site.mountain.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtils<T> {

    public Integer status=200;
    public Integer code=20000;
    public long number=0;
    public Object data;
    public String msg="";

    public ResponseUtils(){
    }

    public static ResponseUtils success(){
        ResponseUtils responseUtils = new ResponseUtils();
        return responseUtils;
    }

    public static ResponseUtils success(String msg){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setMsg(msg);
        return responseUtils;
    }

    public static ResponseUtils success(String msg,Object data){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setMsg(msg);
        responseUtils.setData(data);
        return responseUtils;
    }

    public static ResponseUtils success(Object data){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setData(data);
        return responseUtils;
    }

    public static ResponseUtils success(long number, Object data){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setNumber(number);
        responseUtils.setData(data);
        return responseUtils;
    }

    public static ResponseUtils error(long number, Map<String,Object> data){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setStatus(201);
        responseUtils.setCode(20001);
        responseUtils.setNumber(number);
        responseUtils.setData(data);
        return responseUtils;
    }

    public static ResponseUtils error(){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setStatus(201);
        responseUtils.setCode(20001);
        responseUtils.setData(new HashMap<>());
        return responseUtils;
    }

    public static ResponseUtils error(String msg){
        ResponseUtils responseUtils = new ResponseUtils();
        responseUtils.setStatus(201);
        responseUtils.setCode(20001);
        responseUtils.setData(new HashMap<>());
        responseUtils.setMsg(msg);
        return responseUtils;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
