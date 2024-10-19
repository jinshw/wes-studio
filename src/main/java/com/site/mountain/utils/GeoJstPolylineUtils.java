package com.site.mountain.utils;

import com.alibaba.fastjson.JSONArray;
import org.geotools.geometry.jts.GeometryBuilder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineSegment;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class GeoJstPolylineUtils {
    public static void main(String[] args) {
        String points2 = "[[120.88948941030112,31.971499273182072,-3.2162603904930246E-4],[120.89087347836711,31.97144502152061,-0.0031444333896411838],[120.89098165686168,31.971382638872246,-0.0029715754745467536],[120.89104528990777,31.971287710291097,-0.002963483574847739],[120.891026197935,31.971222617403225,-0.0031272137680363],[120.89102619494525,31.97108700655695,-0.003330999588247324],[120.89104528014302,31.970891726407853,-0.0030458212616177806],[120.89105800113238,31.970669324378452,-0.0027222602592752105],[120.89105800113238,31.970669324378452,-0.0027222602592752105]]";
        //double dista = calculatePointsDistance(points2);
        //System.out.println("zong距离=="+dista);
        double splitDistance = 20;
        JSONArray subPoints = GeoJstPolylineUtils.handleMultiPolylineSplitToPointsList(points2,splitDistance);
        System.out.println(subPoints.size());
        System.out.println(subPoints);
    }

    private static double coordinateAltitude = 1.0;

    private void testMethod(){
        //1-輸出分段点集合
        String points = "[[120.8819818718082,31.968842686141677,0],[120.8873361855384,31.968914331959404,0]]";
        double splitDistance = 32;
        String rePointsJson = handleMultiPolylineSplitToPointsjson(points,splitDistance);

        //2-计算
        String points2 = "[[120.889268,31.970315,0.0],[120.889489,31.970354,0.0],[120.889583,31.970334,0.0],[120.889659,31.970275,0.0],[120.889685,31.970173,0.0],[120.889678,31.970031,0.0]]";
        double dista = calculatePointsDistance(points2);
    }

    /**
     * 1-处理线均分成多段，按指定距离(米)
     * @param points
     * @param splitDistance
     * @return
     */
    public static JSONArray handleMultiPolylineSplitToPointsList(String points,double splitDistance){
        //1-返回均分线段的点集合
        List<Coordinate> list = multiPolylineSplitPoints(points, splitDistance);
        if(list.size()>1){
            for(int k=1;k<list.size();k++){
                double dd = HaversineDistance.calculate(list.get(k-1), list.get(k));
                System.out.println("距离="+k+"==="+dd);
            }
        }
        JSONArray pointAll = multiPolylineSplitArray(list);
        return pointAll;
    }

    /**
     * 1-处理线均分成多段，按指定距离(米)
     * @param points
     * @param splitDistance
     * @return
     */
    public static String handleMultiPolylineSplitToPointsjson(String points,double splitDistance){
        String rePointsJson = "";
        //1-返回均分线段的点集合
        List<Coordinate> list = multiPolylineSplitPoints(points, splitDistance);
        if(list.size()>1){
            for(int k=1;k<list.size();k++){
                double dd = HaversineDistance.calculate(list.get(k-1), list.get(k));
                System.out.println("距离="+k+"==="+dd);
            }
        }
        JSONArray pointAll = multiPolylineSplitArray(list);
        rePointsJson = pointAll.toJSONString();
        System.out.println(rePointsJson);
        return rePointsJson;
    }

    /**
     * 先处理成点集合数据json
     * @param list
     * @return
     */
    public static JSONArray multiPolylineSplitArray(List<Coordinate> list){
        JSONArray pointAll = new JSONArray();
        for(int i=0;i<list.size();i++){
            Coordinate temp = list.get(i);
            List<Double> pointArr = new ArrayList<>();
            pointArr.add(temp.getX());
            pointArr.add(temp.getY());
            pointArr.add(temp.getZ());
            pointAll.add(pointArr);
        }
        return pointAll;
    }

    public static List<Coordinate> multiPolylineSplitCoordinate(Coordinate[] coords,double splitDistance){
        List<Coordinate> listCoordinate = new ArrayList<>();
        int splitNum = 2;
        //Coordinate[] coords = new Coordinate[]{
        //        new Coordinate(120.8819818718082,31.968842686141677,0),
        //        new Coordinate(120.8873361855384,31.968914331959404,0)
        //};
        //double dista = HaversineDistance.calculate(coords[0],coords[1]);//两点间距

        int numSegments = (int) Math.ceil(HaversineDistance.calculate(coords[0], coords[1]) / splitDistance);
        System.out.println(numSegments);
        splitNum = numSegments;//该线段分段数
        if(numSegments != 0){
            listCoordinate.add(coords[0]);
            if(numSegments > 1){
                List<Coordinate> listSub = polylineDivide(coords[0],coords[1],splitNum);
                listCoordinate.addAll(listSub);
            }
            listCoordinate.add(coords[1]);
        }
        return listCoordinate;
    }

    public static List<Coordinate> multiPolylineSplitPoints(String points,double splitDistance){
        List<Coordinate> listCoordinateAll = new ArrayList<>();
        JSONArray pointsList = JSONArray.parseArray(points);
        if(pointsList.size()>1){
            for(int k=1;k<pointsList.size();k++){
                JSONArray tempStart = JSONArray.parseArray(pointsList.get(k-1).toString());
                List<Double> startList = tempStart.stream().mapToDouble(value1 -> Double.parseDouble(value1.toString())).boxed().collect(Collectors.toList());
                JSONArray tempEnd = JSONArray.parseArray(pointsList.get(k).toString());
                List<Double> endList = tempEnd.stream().mapToDouble(value1 -> Double.parseDouble(value1.toString())).boxed().collect(Collectors.toList());
                Coordinate p0 = new Coordinate(startList.get(0),startList.get(1),coordinateAltitude);
                Coordinate p1 = new Coordinate(endList.get(0),endList.get(1),coordinateAltitude);
                Coordinate[] coords = new Coordinate[]{p0,p1};
                List<Coordinate> listCoordinate = multiPolylineSplitCoordinate(coords,splitDistance);
                listCoordinateAll.addAll(listCoordinate);
            }
        }
        return listCoordinateAll;
    }

    /**
     * 计算各点连线的距离
     * @param points 线二维数组json
     * @return
     */
    public static double calculatePointsDistance(String points){
        JSONArray pointsList = JSONArray.parseArray(points);
        double dista = 0;
        if(pointsList.size()>1){
            for(int k=1;k<pointsList.size();k++){
                JSONArray tempStart = JSONArray.parseArray(pointsList.get(k-1).toString());
                List<Double> startList = tempStart.stream().mapToDouble(value1 -> Double.parseDouble(value1.toString())).boxed().collect(Collectors.toList());

                JSONArray tempEnd = JSONArray.parseArray(pointsList.get(k).toString());
                List<Double> endList = tempEnd.stream().mapToDouble(value1 -> Double.parseDouble(value1.toString())).boxed().collect(Collectors.toList());
                Coordinate p0 = new Coordinate(startList.get(0),startList.get(1),0);
                Coordinate p1 = new Coordinate(endList.get(0),endList.get(1),0);
                dista += HaversineDistance.calculate(p0,p1);
            }
        }
        return dista;
    }

    /**
     * 方法描述:  折线等分
     *
     * @param p0  起点
     * @param p1  终点
     * @param num 等分数
     * @throws
     * @Return {@link List< Coordinate>}
     * @author tarzan
     * @date 2022年02月22日 14:11:09
     */
    public static List<Coordinate> polylineDivide (Coordinate p0, Coordinate p1, int num) {
        double factor=1.0D/num;
        int points=num-1;
        List<Coordinate> coordinates = new ArrayList<>(points);
        for (int i = 0; i < points; i++) {
            coordinates.add(pointAlong(p0,p1,factor*(i+1)));
        }
        return coordinates;
    }

    /**
     * 方法描述: 线段比例点坐标计算
     *
     * @param p0
     * @param p1
     * @param factor
     * @return {@link Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 13:49:52
     */
    public static Coordinate pointAlong(Coordinate p0,Coordinate p1,double factor) {
        Coordinate coord = new Coordinate();
        coord.x = p0.x + factor * (p1.x - p0.x);
        coord.y = p0.y + factor * (p1.y - p0.y);
        //coord.z = p0.y + factor * (p1.z - p0.z);
        coord.z = p0.z + factor * (p1.z - p0.z);
        return coord;
    }

    /**
     * 方法描述:  判断点是否在线段上
     *
     * @param p     给定点
     * @param p0 线段开始点坐标
     * @param p1   线段结束点坐标
     * @Return {@link boolean}
     * @author tarzan
     * @date 2022年02月20日 16:51:49
     */
    public static boolean onLineSegment (Coordinate p, Coordinate p0, Coordinate p1) {
        double total = distance(p0, p1);
        double toStart = distance(p, p0);
        double toEnd = distance(p, p1);
        double diff = total - toEnd - toStart;
        return diff==0;
    }
    /**
     * 方法描述:  判断点是否在一条直线上
     *
     * @param p     给定点
     * @param p0 线段开始点坐标
     * @param p1   线段结束点坐标
     * @Return {@link boolean}
     * @author tarzan
     * @date 2022年02月22日 16:51:49
     */
    public static boolean onLine(Coordinate p, Coordinate p0, Coordinate p1) {
        double lsLen = distance(p0, p1);
        double toStart = distance(p, p0);
        double toEnd = distance(p, p1);
        double diff = lsLen - toEnd - toStart;
        if(diff==0) {
            return true;
        }
        diff = toEnd - toStart - lsLen;
        if(diff==0) {
            return true;
        }
        diff = toStart - toEnd - lsLen;
        if(diff==0) {
            return true;
        }
        return false;
    }


    /**
     * 方法描述: 线段中间点
     *
     * @param p0
     * @param p1
     * @return {@link Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 09:33:01
     */
    public static Coordinate midPoint(Coordinate p0, Coordinate p1) {
        return new Coordinate((p0.x + p1.x) / 2.0D, (p0.y + p1.y) / 2.0D, (p0.z + p1.z) / 2.0D);
    }



    /**
     * 方法描述:  点与点的距离
     *
     * @param p0
     * @param p1
     * @return {@link double}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 09:30:03
     */
    public static double distance3D(Coordinate p0,Coordinate p1) {
        return p0.distance3D(p1);
    }


    /**
     * 方法描述:  点与点的距离
     *
     * @param p0
     * @param p1
     * @return {@link double}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 09:30:03
     */
    public static double distance(Coordinate p0,Coordinate p1) {
        return p0.distance(p1);
    }

    /**
     * 方法描述:  转换点坐标
     *
     * @param x
     * @param y
     * @param z
     * @return {@link Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年02月22日 10:35:28
     */
    public static Coordinate getCoordinate(BigDecimal x, BigDecimal y, BigDecimal z){
        if(x==null){
            x=BigDecimal.ZERO;
        }
        if(y==null){
            y=BigDecimal.ZERO;
        }
        if(z==null){
            z=BigDecimal.ZERO;
        }
        Coordinate coordinate=new Coordinate(x.doubleValue(),y.doubleValue(),z.doubleValue());
        return coordinate;
    }

    /**
     * 方法描述: 线段角度 (基于xy的二维角度)
     *
     * @param p0
     * @param p1
     * @return {@link double}
     * @throws
     * @author tarzan
     * @date 2022年02月24日 14:14:33
     */
    public static double angle(Coordinate p0,Coordinate p1){
        LineSegment ls=new LineSegment(p0,p1);
        return ls.angle();
    }

    /**
     * 方法描述: 线段角度 (基于xy的二维角度和基于xz的二维角度)
     *
     * @param p0
     * @param p1
     * @return {@link double}
     * @throws
     * @author tarzan
     * @date 2022年02月24日 14:14:33
     */
    public static double[] angle3D(Coordinate p0,Coordinate p1){
        double[] angles=new double[2];
        angles[0]=Math.atan2(p1.y - p0.y, p1.x - p0.x);
        double c=p0.distance(p1);
        angles[1]=Math.atan2(p1.z - p0.z, c);
        return angles;
    }

    /**
     * 方法描述: 二维移动点
     *
     * @param p
     * @param angle
     * @param distance
     * @return {@link Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年02月24日 14:14:49
     */
    public static Coordinate movePoint(Coordinate p,double angle,double distance){
        double sin=Math.sin(angle);
        double a= distance*sin;
        double cos=Math.cos(angle);
        double b= distance*cos;
        return new Coordinate(p.x+b,p.y+a,0);
    }


    /**
     * 方法描述: 三维移动点
     *
     * @param p
     * @param angles
     * @param distance
     * @return {@link Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年02月24日 14:14:49
     */
    public static Coordinate movePoint3D(Coordinate p,double[] angles,double distance){
        double sin1=Math.sin(angles[1]);
        double d= distance*sin1;
        double cos1=Math.cos(angles[1]);
        double c= distance*cos1;
        double sin0=Math.sin(angles[0]);
        double a= c*sin0;
        double cos0=Math.cos(angles[0]);
        double b= c*cos0;
        return new Coordinate(p.x+b,p.y+a,p.z+d);
    }


    /**
     * 方法描述: 获取点离多段线最近的线及投影点
     *
     * @param
     * @return LineSegment
     * @auther tarzan
     * @date 2022年02月28日 14:09:10
     */
    public static LineSegment nearestLineSegment(Coordinate p, List<LineSegment> lines) {
        double distance = Double.MAX_VALUE;
        LineSegment result = null;
        for (LineSegment ls : lines) {
            double distance2 = distance(p, ls.p0, ls.p1);
            distance = Double.min(distance, distance2);
            if (distance2 <= distance) {
                result = ls;
            }
        }
        return result;
    }

    /**
     * 方法描述: 点到线的距离
     *
     * @param p
     * @param p0
     * @param p1
     * @return {@link double}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 09:21:13
     */
    public static double distance3D(Coordinate p, Coordinate p0, Coordinate p1) {
        GeometryBuilder builder = new GeometryBuilder();
//        Point point = builder.pointZ(p.x, p.y, p.z);
//        LineString ls = builder.lineStringZ(p0.x, p0.y, p0.z, p1.x, p1.y, p1.z);
//        return point.distance(ls);
        return 0;
    }

    /**
     * 方法描述: 点到线的距离
     *
     * @param p
     * @param p0
     * @param p1
     * @return {@link double}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 09:21:13
     */
    public static double distance(Coordinate p, Coordinate p0, Coordinate p1) {
        LineSegment ls=new LineSegment(p0,p1);
        return ls.distance(p);
    }

    /**
     * 方法描述:  点与线段上的最近点计算
     *
     * @param p  给定点
     * @param p0 起始点
     * @param p1 结束点
     * @throws
     * @Return {@link Coordinate}
     * @author tarzan
     * @date 2021年08月03日 18:01:42
     */
    public static Coordinate closestPoint(Coordinate p, Coordinate p0, Coordinate p1) {
        double factor = projectionFactor(p, p0, p1);
        if (factor > 0.0D && factor < 1.0D) {
            return project(p, p0, p1);
        } else {
            double dist0 = p0.distance3D(p);
            double dist1 = p1.distance3D(p);
            return dist0 < dist1 ? p0 : p1;
        }
    }

    /**
     * 方法描述: 点在线上的投影点
     *
     * @param p
     * @param p0
     * @param p1
     * @return {@link org.locationtech.jts.geom.Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 09:25:31
     */
    public static Coordinate project(Coordinate p, Coordinate p0, Coordinate p1) {
        if (!p.equals3D(p0) && !p.equals3D(p1)) {
            double r = projectionFactor(p, p0, p1);
            Coordinate coord = new Coordinate();
            coord.x = p0.x + r * (p1.x - p0.x);
            coord.y = p0.y + r * (p1.y - p0.y);
            coord.z = p0.z + r * (p1.z - p0.z);
            return coord;
        } else {
            return new Coordinate(p);
        }
    }

    /**
     * 方法描述:  投影系数
     *
     * @param p
     * @param p0
     * @param p1
     * @return {@link double}
     * @throws
     * @author tarzan
     * @date 2022年02月23日 09:26:33
     */
    private static double projectionFactor(Coordinate p, Coordinate p0, Coordinate p1) {
        if (p.equals3D(p0)) {
            return 0.0D;
        } else if (p.equals3D(p1)) {
            return 1.0D;
        } else {
            double dx = p1.x - p0.x;
            double dy = p1.y - p0.y;
            double dz = p1.z - p0.z;
            double len = dx * dx + dy * dy+ dz * dz;
            if (Double.compare(len,0)==0) {
                return 0;
            } else {
                double r = ((p.x - p0.x) * dx + (p.y - p0.y) * dy+  (p.z - p0.z) * dz) / len;
                return r;
            }
        }
    }


    /**
     * 方法描述:  转相对坐标
     *
     * @param p 坐标点
     * @param baseP 基准点
     * @return {@link Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年04月22日 10:05:31
     */
    private static Coordinate relativeCoordinate(Coordinate p, Coordinate baseP) {
        double x=p.getX()-baseP.getX();
        double y=p.getY()-baseP.getY();
        double z=p.getZ()-baseP.getZ();
        return new Coordinate(x,y,z);
    }

    /**
     * 方法描述: 转巴比伦坐标
     *
     * @param p
     * @param baseP
     * @return {@link Coordinate}
     * @throws
     * @author tarzan
     * @date 2022年04月22日 10:06:03
     */
    private static Coordinate babylonCoordinate(Coordinate p, Coordinate baseP) {
        Coordinate p0=relativeCoordinate(p,baseP);
        p0.setX(-p0.getX());
        p0.setY(p0.getZ());
        p0.setZ(-p0.getY());
        return  p0;
    }

}
