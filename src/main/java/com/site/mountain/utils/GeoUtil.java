package com.site.mountain.utils;

import com.alibaba.fastjson.JSONArray;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.geotools.geometry.jts.JTSFactoryFinder;

/**
 * 利用GeoTools判断坐标是否在多边形内
 *
 * @author BBF
 */
public class GeoUtil {

    public static void main(String[] args) {
        String fencePolygons = "[[120.88238302155877, 31.976183819451133],[120.8910305922706, 31.97781695470429],[120.89269010647091, 31.965596089364414]," +
                "[120.88545896332818, 31.964600463807432],[120.88545896332818, 31.964600463807432]]";
        transformPolygons(fencePolygons);
    }

    /**
     * 通用几何对象工厂构建器
     */
    private static final GeometryFactory FACTORY = JTSFactoryFinder.getGeometryFactory(null);
    private static final WKTReader WKT_READER = new WKTReader(FACTORY);


    /**
     * 判断点是否存在多边形内
     *
     * @param point   点
     * @param polygon 多边形
     * @return true - 在多边形内
     * @throws ParseException wkt转换异常
     * @see <a href="https://blog.csdn.net/lidejun152046/article/details/47128169">WKT格式</a>
     */
    public static boolean containsPointPolygon(String point, String polygon) throws ParseException {
        return WKT_READER.read(polygon).contains(WKT_READER.read(point));
    }

    public static String transformPoint(Double lat,Double lng) {
        String pointWk = "POINT (";
        pointWk += lat +" " + lng;
        pointWk += ")";
        return pointWk;
    }

    /**
     * polygons字符串转为POLYGON(())
     * @param polygons
     * @return
     */
    public static String transformPolygons(String polygons){
        String polygonsWk = "POLYGON ((";
        JSONArray polygonsArr = JSONArray.parseArray(polygons);
        JSONArray tempLast = (JSONArray)polygonsArr.get(polygonsArr.size()-1);
        polygonsWk += tempLast.get(0)+" "+tempLast.get(1);


        for(int i=0;i<polygonsArr.size()-1;i++){
            JSONArray tempOne = (JSONArray)polygonsArr.get(i);
            polygonsWk += ", " + tempOne.get(0)+" "+tempOne.get(1);
        }
        polygonsWk += "))";
        return polygonsWk;
    }

}
