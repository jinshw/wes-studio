package com.site.mountain.sqlite;

import com.alibaba.fastjson.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKBReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class SqliteIbdResolveUtils {

    @Value("${sqliteUrl}")
    public static String sqliteRoUrl;

    public static void main(String[] args) {
        //List<Map<String,Object>> ibdList = test();

        /*String shapePoints = "120.872045461355 32.001065061829 13.369997848926;120.872083348778 32.001068486444 13.369997848926;120.872083013879 32.001071176920 13.369997848926;120.872108774511 32.001069424276 13.369997848926;120.872084018577 32.001063105492 13.369997848926;120.872083683678 32.001065795968 13.369997848926;120.872045796256 32.001062371353 13.369997848926;120.872045461355 32.001065061829 13.369997848926";
        String shapePointsGeojson = shapePoint2Geojson(shapePoints);
        System.out.println(shapePointsGeojson);*/

//        List<Map<String,Object>> ibdList = queryIbdLogList();
//        System.out.println(ibdList.size());

        List<Map<String,Object>> ibdBizList = queryIbdBizList("","","");
        System.out.println(ibdBizList.size());

    }



    public static List<Map<String,Object>> test(){
        String ibdTable = "IBD_OBJECT_STOPLINE";
        String querySql = "OBJECT_PID, GUID, MESH, WIDTH, COLOR, LOCATION_TYPE, GEOMETRY, ROAD_PID";
        List<Map<String,Object>> ibdList = ibdReadBasicsData(ibdTable, querySql,null);
        return ibdList;
    }

    public static List<Map<String,Object>> queryIbdBizList(String ibdTable,String querySql,String queryCondition){
        if("".equals(ibdTable)){
            ibdTable = "IBD_OBJECT_ARROW";
        }
        if("".equals(querySql)){
            querySql = "OBJECT_PID as pid,GUID as guid,GEOMETRY as geometry,SHAPEPOINTS as shapePoints";
        }
        List<Map<String,Object>> ibdBizList = ibdReadBasicsData(ibdTable,querySql,queryCondition);
        return ibdBizList;
    }

    public static List<Map<String,Object>> queryIbdLogList(){
        String ibdTable = "IBD_LOG";
        String querySql = "DATE as cteateTime,TABLE_NAME as tableName,PID as pid,TYPE as type";
        String queryCondition = "ORDER BY DATE asc";
        List<Map<String,Object>> ibdLogList = ibdReadBasicsData(ibdTable,querySql,queryCondition);
        return ibdLogList;
    }

    /**
     * 查询sqlite库数据
     * @param ibdTable
     * @param querySql
     * @param queryCondition
     * @return
     */
    public static List<Map<String,Object>> ibdReadBasicsData(String ibdTable,String querySql,String queryCondition){
        List<Map<String,Object>> ibdDataList = new ArrayList<>();
        String sqlite_url = loadPropertyInit();

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+sqlite_url);
            c.setAutoCommit(false);
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "select "+ querySql +" from "+ ibdTable;
            if(queryCondition !=null && !"".equals(queryCondition)){
                sql += " "+queryCondition;
            }

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Map<String,Object> temp = new HashMap<>();
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int t = 1; t <= rsmd.getColumnCount(); t++) {
                    String col = rsmd.getColumnName(t);
                    if("GEOMETRY".equalsIgnoreCase(col)){
                        byte[] geom = rs.getBytes("GEOMETRY");// 输入geometry byte数组
                        byte[] bytes = new byte[geom.length - 39];
                        bytes[0] = geom[1];
                        for (int i = 1; i < bytes.length; i++) {
                            bytes[i] = geom[i + 38];
                        }
                        Geometry geometry = new WKBReader().read(bytes);//输出jts geometery
                        String geomGeojson = geometry2Geojson(geometry);
                        temp.put(col,geomGeojson);
                    }else if("SHAPEPOINTS".equalsIgnoreCase(col)){
                        String shapePoints = rs.getObject(col).toString();
                        String shapePointsGeojson = shapePoint2Geojson(shapePoints);
                        temp.put(col,shapePointsGeojson);
                    }else{
                        temp.put(col,rs.getObject(col));
                    }
                }
                ibdDataList.add(temp);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }finally {
            //System.out.println("Operation done successfully");
        }
        return ibdDataList;
    }

    /**
     * 加载文件路径
     * @return
     */
    public static String loadPropertyInit() {
        String sqlite_url = "";
        try {
            Properties properties = new Properties();
            // 使用ClassLoader加载properties配置文件生成对应的输入流
            InputStream in = SqliteIbdResolveUtils.class.getClassLoader().getResourceAsStream("application-dev.properties");
            // 使用properties对象加载输入流
            properties.load(in);
            //获取key对应的value值
            sqlite_url = properties.getProperty("sqliteUrl");
        }catch (Exception e){
            e.printStackTrace();
        }
        return sqlite_url;
    }

    /**
     * geometry转换为Geojson
     * @param geometry
     * @return
     */
    public static String geometry2Geojson(Geometry geometry)  {
        JSONObject jsonObject = new JSONObject();
        String geoType = geometry.getGeometryType();
        jsonObject.put("type",geoType);

        Double[] coords1List = new Double[3];
        List<Double[]> coords2List = new ArrayList<>();
        List coords3List = new ArrayList();
        Coordinate[] coordinateList = geometry.getCoordinates();
        for(Coordinate temp:coordinateList){
            Double[] geomdArray = new Double[]{temp.getX(),temp.getY(),temp.getZ()};
            coords1List = geomdArray;
            coords2List.add(geomdArray);
        }
        if("Point".equalsIgnoreCase(geoType)){
            jsonObject.put("coordinates",coords1List);
        }else if("LineString".equalsIgnoreCase(geoType)){
            jsonObject.put("coordinates",coords2List);
        }else if("Polygon".equalsIgnoreCase(geoType)){
            coords3List.add(coords2List);
            jsonObject.put("coordinates",coords3List);
        }
        return jsonObject.toJSONString();
    }

    /**
     * shapePoints转换为Geojson
     * @param shapePoints
     * @return
     */
    public static String shapePoint2Geojson(String shapePoints){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type","Polygon");

        List<Double[]> coords2List = new ArrayList<>();
        List coords3List = new ArrayList();
        String[] shapePointArr = shapePoints.split(";");
        for (String shapePoint:shapePointArr) {
            String[] shapePointOne = shapePoint.split(" ");
            Double[] coords1List = new Double[shapePointOne.length];
            for (int i = 0; i < shapePointOne.length; i++) {
                coords1List[i] = Double.parseDouble(shapePointOne[i]);
            }
            coords2List.add(coords1List);
        }

        coords3List.add(coords2List);
        jsonObject.put("coordinates",coords3List);
        return jsonObject.toJSONString();
    }

}
