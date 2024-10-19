package com.site.mountain.request;

public class GdbitResp<T> {

    public static final String SUCCESS = "ok";

    private String state;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return this.state.equals(SUCCESS);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
