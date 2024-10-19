package com.site.mountain.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ApiRequest {

    private final String url;
    private final Map<String, String> headerMap;
    private final String jsonParams;

    public ApiRequest(String url, Map<String, String> headerMap, String jsonParams) {
        this.url = url;
        this.headerMap = headerMap;
        this.jsonParams = jsonParams;
        //System.out.println("请求header: " + JsonUtil.mapToJson(headerMap));
    }

    public String doRequest() {
        if (null == jsonParams) {
            return "请求参数为空";
        }
        // 通过httpclient构造一个post请求
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
        httpPost.setConfig(requestConfig);

        // 设置请求的 header
        for (Map.Entry<String, String> s : headerMap.entrySet()) {
            httpPost.setHeader(s.getKey(), s.getValue());
        }

        // 设置请求数据类型为json
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        // 设置请求的 body
        HttpEntity entity = new StringEntity(jsonParams, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);

        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse httpResp = httpclient.execute(httpPost)) {
            if (Objects.nonNull(httpResp)) {
                return EntityUtils.toString(httpResp.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

}
