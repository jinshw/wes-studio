package com.site.mountain.common;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public abstract class SliceMethod {
    //    private String fileType;
//    private String sliceType;
//    private String filePath;
    private Map<String, String> param;

    public abstract JSONObject slice();

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
