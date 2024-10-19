package com.site.mountain.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.site.mountain.dll.IbdCustomerInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class IbdDllUtils {

    public static void main(String[] args) throws InterruptedException {
        //String ipAddress = "172.19.250.14";
        //String port = "9999";
        //String crossingOneAreaParam ="[[120.88618465443659,31.96430847809984,0],[120.88598861237121,31.96757991788934,0],[120.89057744739756,31.96764024485662,0],[120.8908604202502,31.96424350521859,0],[120.88618465443659,31.96430847809984,0]]";

        /*IbdDllUtils dllUtils = new IbdDllUtils(ipAddress,port);
        String reIbdContent = dllUtils.getIbdRoadCrossingData(crossingOneAreaParam);*/
        //dllUtils.ibdDataWriteFile(reIbdContent);

        /*String objType = "ObjectArrow";
        String opterateType = "1";
        String jsonData = "{\"ObjectArrow\":{\"Id\":\"2024030910573994843\",\"Type\":1}}";
        Map<String,Object> reIbdObj = IbdDllUtils.ibdAddAndUpdateDataInfo(ipAddress,port,objType,opterateType,jsonData);
        System.out.println(reIbdObj);
        String areaParam = "[]";
        Map<String,Object> reIbdObj = IbdDllUtils.getIbdArrowDataByAreaCoord(ipAddress,port,areaParam);
        System.out.println(reIbdObj);*/

        /*Map<String,Object> reIbdObj = IbdDllUtils.getIbdGetObjCount(ipAddress,port);
        System.out.println(reIbdObj);*/
    }

    public static Map<String,Object> getIbdGetObjCount(String ipAddress, String port){
        Map reMap = new HashMap();
        String reIbdContent = IbdCustomerInterface.ibdCustomerInterface.GetObjCount(ipAddress,port);
        String[] reIbdContentArr = reIbdContent.split(";");
        if(reIbdContentArr.length==2){
            Boolean boo = Boolean.parseBoolean(reIbdContentArr[0]);
            reMap.put("status",boo);
            if(boo){
                reMap.put("msg","");
                JSONObject arrowObj = JSONObject.parseObject(reIbdContentArr[1]);
                reMap.put("data",arrowObj);
            }else{
                reMap.put("msg",reIbdContentArr[1]);
                reMap.put("data",new ArrayList<>());
            }
        }
        return reMap;
    }

    public static Map<String,Object> getIbdArrowDataByAreaCoord(String ipAddress, String port,String areaParam){
        Map reMap = new HashMap();
        String reIbdContent = IbdCustomerInterface.ibdCustomerInterface.GetArrowDataByAreaCoord(ipAddress,port,areaParam);
        String[] reIbdContentArr = reIbdContent.split(";");
        if(reIbdContentArr.length==2){
            Boolean boo = Boolean.parseBoolean(reIbdContentArr[0]);
            reMap.put("status",boo);
            if(boo){
                reMap.put("msg","");
                JSONObject arrowObj = JSONObject.parseObject(reIbdContentArr[1]);
                JSONArray arrowArr = arrowObj.getJSONArray("ObjectArrow");
                reMap.put("data",arrowArr);
            }else{
                reMap.put("msg",reIbdContentArr[1]);
                reMap.put("data",new ArrayList<>());
            }
        }
        return reMap;
    }

    /**
     *
     * @param ipAddress
     * @param port
     * @param relType
     * @param objId
     * @return
     */
    public static Map<String,Object> ibdUpdateRoadLinkJuncRelInfo(String ipAddress, String port, String relType, String objId){
        Map reMap = new HashMap();
        String reIbdContent = IbdCustomerInterface.ibdCustomerInterface.UpdateRoadLinkJuncRelInfo(ipAddress,port,relType,objId);
        String[] reIbdContentArr = reIbdContent.split(";");
        if(reIbdContentArr.length==1){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg","");
        }
        if(reIbdContentArr.length==2){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg",reIbdContentArr[1]);
        }
        return reMap;
    }

    /**
     * 根据区域坐标对车道通行车辆类型批量更新接口
     * @param ipAddress
     * @param port
     * @param jsonAreaCorrd
     * @param jsonData
     * @return
     */
    public static Map<String,Object> ibdUpdateLaneLinkVehicleInfoByAreaCoord(String ipAddress, String port, String jsonAreaCorrd, String jsonData){
        Map reMap = new HashMap();
        String reIbdContent = IbdCustomerInterface.ibdCustomerInterface.UpdateLaneLinkVehicleInfoByAreaCoord(ipAddress,port,jsonAreaCorrd,jsonData);
        String[] reIbdContentArr = reIbdContent.split(";");
        if(reIbdContentArr.length==1){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg","");
        }
        if(reIbdContentArr.length==2){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg",reIbdContentArr[1]);
        }
        return reMap;
    }

    /**
     * 根据区域坐标对车道限速批量更新接口
     * @param ipAddress
     * @param port
     * @param jsonAreaCorrd
     * @param jsonData
     * @return
     */
    public static Map<String,Object> ibdUpdateLaneLinkSpeedInfoByAreaCoord(String ipAddress, String port, String jsonAreaCorrd, String jsonData){
        Map reMap = new HashMap();
        String reIbdContent = IbdCustomerInterface.ibdCustomerInterface.UpdateLaneLinkSpeedInfoByAreaCoord(ipAddress,port,jsonAreaCorrd,jsonData);
        String[] reIbdContentArr = reIbdContent.split(";");
        if(reIbdContentArr.length==1){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg","");
        }
        if(reIbdContentArr.length==2){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg",reIbdContentArr[1]);
        }
        return reMap;
    }

    /**
     * 修改ibd数据
     * @param ipAddress
     * @param port
     * @param objType
     * @param opterateType
     * @param jsonData
     * @return
     */
    public static Map<String,Object> ibdAddAndUpdateDataInfo(String ipAddress, String port, String objType, String opterateType, String jsonData){
        Map reMap = new HashMap();
        String reIbdContent = IbdCustomerInterface.ibdCustomerInterface.UpdateDataInfo(ipAddress,port,objType,opterateType,jsonData);
        String[] reIbdContentArr = reIbdContent.split(";");
        if(reIbdContentArr.length==1){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg","");
        }
        if(reIbdContentArr.length==2){
            reMap.put("status",Boolean.parseBoolean(reIbdContentArr[0]));
            reMap.put("msg",reIbdContentArr[1]);
        }
        return reMap;
    }

    /**
     * 根据路口区域查询当前路口的所有要素数据(线，箭头)
     * @param areaParam
     * @return
     */
    public static String getIbdRoadCrossingData(String ipAddress, String port,String areaParam){
        String reIbdObj = "";
        reIbdObj = IbdCustomerInterface.ibdCustomerInterface.GetDataByAreaCoordAndIPInfo(ipAddress,port,areaParam);
        return reIbdObj;
    }

    public static boolean getIbdInitTcpConnect(String ipAddress, String port){
        boolean booCoon = IbdCustomerInterface.ibdCustomerInterface.InitTcpConnect(ipAddress,port);//"172.19.250.34","9999"
        return booCoon;
    }

    public String getIbdRoadCrossingData(String areaParam){
        String reIbdObj = "";
        reIbdObj = IbdCustomerInterface.ibdCustomerInterface.GetDataByAreaCoord(areaParam);
        return reIbdObj;
    }

    /**
     * 根据路口区域查询当前路口的所有要素数据(线，箭头)
     * @param areaParam
     * @return
     */
    public static String getIbdRoadCrossingDataInit(String ipAddress, String port, String areaParam){
        String reIbdObj = "";
        boolean booCoon = IbdCustomerInterface.ibdCustomerInterface.InitTcpConnect(ipAddress,port);
        if(booCoon){
            reIbdObj = IbdCustomerInterface.ibdCustomerInterface.GetDataByAreaCoord(areaParam);
            IbdCustomerInterface.ibdCustomerInterface.DisconnectFromHost();
        }
        return reIbdObj;
    }

    public static void ibdDataInteHelloWo(){
        IbdCustomerInterface.ibdCustomerInterface.HelloWorld2();
    }

    public static void ibdDataWriteFile(String reIbdContent,String filePath){
        //String filePath = "C:\\Users\\Administrator\\Desktop\\test2.json"; // 指定文件路径
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(reIbdContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getIbdRoadCrossingDataByThread(int threadNum, int timeOut, String areaParam) {
        // 获取线程池
        ExecutorService es = Executors.newFixedThreadPool(threadNum);
        // Future用于执行多线程的执行结果
        Future<String> future = es.submit(() -> {
            String reIbdObj ="";
            return reIbdObj;
        });

        try {
            // futrue.get()测试被执行的程序是否能在timeOut时限内返回字符串
            return future.get(timeOut, TimeUnit.SECONDS);//任务处理超时时间设为 1 秒
        } catch (Exception ex) {
            System.out.println("输出异常：" + ex.getMessage());
        } finally {
            // 关闭线程池
            es.shutdown();
        }
        return null;
    }

}
