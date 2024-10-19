package com.site.mountain.dll;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface IbdCustomerInterface extends Library {
    String property = System.getProperty("user.dir");
    String filePath = property + "\\dll\\";
    //String filePath = "D:\\nantongmain2024\\dll\\";
    //String filePath = "/home/mywork/nt_bgmanage/apache-tomcat-8.5.59/webapps/roadrule/WEB-INF/classes/com/site/mountain/dll/";
    //String filePath = "/home/dll/";E:\2024-nantongService\apache-tomcat-8.5.59
    //String filePath = "E:/2024-nantongService/apache-tomcat-8.5.59/webapps/dll/";

    IbdCustomerInterface ibdCustomerInterface = Native.loadLibrary(filePath+"IbdCustomerInterface", IbdCustomerInterface.class);

    boolean InitTcpConnect(String ipAddress, String port);

    void HelloWorld1();

    void HelloWorld2();

    String GetDataByAreaCoord(String jsonAreaCorrd);

    void DisconnectFromHost();

    String GetDataByAreaCoordAndIPInfo(String ipAddress, String port,String jsonAreaCorrd);

    String UpdateDataInfo(String ipAddress, String port, String objType,String opterateType, String jsonData);

    String UpdateLaneLinkSpeedInfoByAreaCoord(String ipAddress, String port, String jsonAreaCorrd, String jsonData);

    String UpdateLaneLinkVehicleInfoByAreaCoord(String ipAddress, String port, String jsonAreaCorrd, String jsonData);

    String UpdateRoadLinkJuncRelInfo(String ipAddress, String port, String relType, String objId);

    String GetArrowDataByAreaCoord(String ipAddress, String port,String jsonAreaCorrd);

    String GetObjCount(String ipAddress, String port);
}
