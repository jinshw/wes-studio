package com.site.mountain.request;

public class RespBase<T> {

    /**
     * 成功的标记
     */
    public static final String SUCCESS = "0";

    /**
     * 状态码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;

    public RespBase() {
        this.code = SUCCESS;
        this.msg = "";
    }

    public boolean isSuccess() {
        return this.code.equals(SUCCESS);
    }

    public RespBase(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespBase(T data) {
        this();
        this.data = data;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
