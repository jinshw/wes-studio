package com.site.mountain.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mysql.jdbc.StringUtils;
import com.site.mountain.request.GdbitDataDto;
import com.site.mountain.request.GdbitRequest;
import com.site.mountain.request.GdbitResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class GdbitButtUtils {

    @Value("${gdbit.ipAddress}")
    private String GDBIT_IPADDRESS;

    @Value("${gdbit.version}")
    private String GDBIT_VERSION;

    @Value("${gdbit.token}")
    private String GDBIT_TOKEN;

    @Value("${gdbit.type}")
    private String GDBIT_TYPE;

    private static final String httpUrl = "http://";
    private static final String defaultFileName = "PLAY.LST";
    private static final String cmsStateUrl = "/api/cms/cmsState";
    private static final String cmsUploadUrl = "/api/cms/cmsUpload";
    private static final String cmsDownloadUrl = "/api/cms/cmsDownload";
    private static final String cmsDisplayPlaylistUrl = "/api/cms/cmsDisplayPlaylist";
    private static final String signalReadInfoUrl = "/api/act/readInfo";

    public GdbitResp<Map<String,Object>> getCmsDownload(GdbitDataDto bitData){
        String result = getThirdCmsDownload(bitData);
        GdbitResp<Map<String,Object>> respBase = JsonUtil.jsonToObject(result, new TypeReference<GdbitResp<Map<String,Object>>>() {});
        return respBase;
    }

    /**
     * 从可变信息标志下载文件
     * @param bitData
     * @return
     */
    public String getThirdCmsDownload(GdbitDataDto bitData){
        GdbitRequest gdbitRequest = new GdbitRequest();
        JSONObject reObj = new JSONObject();
        if(StringUtils.isNullOrEmpty(bitData.getFileName())){
            bitData.setFileName(defaultFileName);
        }
        if(StringUtils.isNullOrEmpty(String.valueOf(bitData.getFileType()))){
            bitData.setFileType(0);
        }
        gdbitRequest.setDevCode(bitData.getDevCode());
        gdbitRequest.setToken(GDBIT_TOKEN);
        gdbitRequest.setVersion(GDBIT_VERSION);
        gdbitRequest.setType(GDBIT_TYPE);
        gdbitRequest.setData(bitData);
        String jsonParams = JsonUtil.objectToJson(gdbitRequest);//请求json:
        String doMAIN = httpUrl+ GDBIT_IPADDRESS;
        Map<String, String> headerMap = new HashMap<>();
        ApiRequest request = new ApiRequest(doMAIN + cmsDownloadUrl, headerMap, jsonParams);
        String body = request.doRequest();
        return body;
    }

    public GdbitResp<Map<String,Object>> getThirdSignalInfoByChannel(GdbitDataDto bitData){
        String result = getThirdSignalInfo(bitData);
        GdbitResp<Map<String,Object>> respBase = JsonUtil.jsonToObject(result, new TypeReference<GdbitResp<Map<String,Object>>>() {});
        return respBase;
    }

    /**
     * 信号机信号灯态查询
     * @param bitData
     * @return
     */
    public String getThirdSignalInfo(GdbitDataDto bitData){
        GdbitRequest gdbitRequest = new GdbitRequest();
        gdbitRequest.setDevCode(bitData.getDevCode());
        gdbitRequest.setToken(GDBIT_TOKEN);
        gdbitRequest.setVersion(GDBIT_VERSION);
        gdbitRequest.setType(GDBIT_TYPE);
        Map<String,Object> dataObj = new HashMap<>();
        Map<String,Object> channelObj = new HashMap<>();
        channelObj.put("ChannelStatus",new ArrayList<>());
        dataObj.put("Channel",channelObj);

        gdbitRequest.setData(dataObj);
        String jsonParams = JsonUtil.objectToJson(gdbitRequest);//请求json:
        String doMAIN = httpUrl+ GDBIT_IPADDRESS;
        Map<String, String> headerMap = new HashMap<>();
        ApiRequest request = new ApiRequest(doMAIN + signalReadInfoUrl, headerMap, jsonParams);
        String body = request.doRequest();
        return body;
    }

}
