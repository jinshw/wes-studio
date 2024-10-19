package com.site.mountain.utils;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.site.mountain.request.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class ArtemisButtUtils {
    /**
     * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
     *
     * ip:port : 平台门户/nginx的IP和端口（必须使用https协议，https端口默认为443）
     * appKey : 请填入appKey
     * appSecret : 请填入appSecret
     * private static ArtemisConfig artemisConfig2 = new ArtemisConfig("10.0.0.1:443", "11111111", "AAAAAAAAAAAAA");
     */
    /**
     * STEP2：设置OpenAPI接口的上下文
     */
    @Value("${artemis.path}")
    private String ARTEMIS_PATH;

    @Value("${artemis.host}")
    private String ARTEMIS_HOST;

    @Value("${artemis.appkey}")
    private String ARTEMIS_APPKEY;

    @Value("${artemis.appsecret}")
    private String ARTEMIS_APPSECRET;

    public void getArtemisConfig(){
        ArtemisConfig artemisConfig = new ArtemisConfig();
        artemisConfig.setHost(ARTEMIS_HOST);
        artemisConfig.setAppKey(ARTEMIS_APPKEY);
        artemisConfig.setAppSecret(ARTEMIS_APPSECRET);
    }

    //1-分页获取点资源
    public String cameras(CamerasRequest camerasRequest) throws Exception {
        getArtemisConfig();
        String camerasDataApi = ARTEMIS_PATH +"/api/resource/v1/cameras";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",camerasDataApi);
            }
        };
        String body= JSONObject.toJSONString(camerasRequest);
        String result = ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
        return result;
    }

    //2-获取点预览取流URL
    public String previewURLs(PreviewURLsRequest previewURLsRequest) throws Exception {
        getArtemisConfig();
        String previewURLsDataApi = ARTEMIS_PATH +"/api/video/v1/cameras/previewURLs";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",previewURLsDataApi);
            }
        };
        String body=JSONObject.toJSONString(previewURLsRequest);
        String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
        return result;
    }

    //3-分页获取卡口资源
    public String getCrossingsWithPage(GetCrossingsWithPageRequest getCrossingsWithPageRequest) throws Exception {
        getArtemisConfig();
        String getCrossingsWithPageDataApi = ARTEMIS_PATH +"/api/resource/v1/crossing/getCrossingsWithPage";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",getCrossingsWithPageDataApi);
            }
        };
        String body=JSONObject.toJSONString(getCrossingsWithPageRequest);
        String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
        return result;
    }

    //4-按车辆属性查询过车信息(增加违法过车查询)
    public String vehicleTrackInAlarmQuery(VehicleTrackInAlarmQueryRequest vehicleTrackInAlarmQueryRequest) throws Exception {
        getArtemisConfig();
        String vehicleTrackInAlarmQueryDataApi = ARTEMIS_PATH +"/api/application/dataresource/v1/vehicle-oia/vehicleTrackInAlarmQuery";
        Map<String,String> path = new HashMap<String,String>(2){
            {
                put("https://",vehicleTrackInAlarmQueryDataApi);
            }
        };
        String body=JSONObject.toJSONString(vehicleTrackInAlarmQueryRequest);
        String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
        return result;
    }

    //5-测试视频url
    private String[] cameraPreviewUrlArr = new String[]{
            "http://220.161.87.62:8800/hls/0/index.m3u8",
            "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8"
    };

    /**
     * 随机视频地址（测试用）
     * @return
     */
    public String getTestRandCameraUrl(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(cameraPreviewUrlArr.length);
        return cameraPreviewUrlArr[randomIndex];
    }

    public void testApiIn(){
        //1-视频点资源
        /*CamerasRequest camerasRequest = new CamerasRequest();
        camerasRequest.setPageNo(1);
        camerasRequest.setPageSize(20);
        camerasRequest.setTreeCode("0");
        String cameras_Result=cameras(camerasRequest);
        RespBase<CamerasResp> respBase = JsonUtil.jsonToObject(cameras_Result, new TypeReference<RespBase<CamerasResp>>() {});*/

        //2-视频点预览
        /*PreviewURLsRequest previewURLsRequest = new PreviewURLsRequest();
        previewURLsRequest.setCameraIndexCode("32060217051210008044");
        String previewURLs_Result=previewURLs(previewURLsRequest);*/

        //3-卡口资源
        /*GetCrossingsWithPageRequest crossingsWithPageRequest = new GetCrossingsWithPageRequest();
        crossingsWithPageRequest.setPageNo(1);
        crossingsWithPageRequest.setPageSize(10);
        crossingsWithPageRequest.setTreeCode("0");
        String crossingResult=getCrossingsWithPage(crossingsWithPageRequest);
        RespBase<CrossingResp> respBase = JsonUtil.jsonToObject(crossingResult, new TypeReference<RespBase<CrossingResp>>() {});*/

        //String vehicleTrackInAlarmQuery_Result=vehicleTrackInAlarmQuery();
    }

}
