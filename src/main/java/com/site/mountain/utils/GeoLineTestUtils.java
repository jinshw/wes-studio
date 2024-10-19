package com.site.mountain.utils;


import com.alibaba.fastjson.JSONArray;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;
//import org.locationtech.jts.algorithm.distance.HaversineDistance;

/**
 *
 */
public class GeoLineTestUtils {

    //private GeometryFactory geometryFactory = new GeometryFactory();

    //==第二种方法==================
    public static LineString[] splitLineString(LineString line, double distance) {
        if (line == null || distance <= 0) {
            throw new IllegalArgumentException("Invalid input: line must not be null and distance must be greater than 0");
        }

        Coordinate[] coords = line.getCoordinates();
        System.out.println(line.getLength());
        int n = (int) Math.ceil(line.getLength() / distance);

        LineString[] segments = new LineString[n - 1];
        Coordinate p0 = coords[0];
        for (int i = 1; i < coords.length; i++) {
            Coordinate p1 = coords[i];
            double len = p0.distance(p1);
            int steps = (int) (len / distance);
            Coordinate between = new Coordinate();
            for (int step = 1; step <= steps; step++) {
                if (i - 1 < segments.length) {
                    double fraction = step / (double) steps;
                    between = new Coordinate(
                            p0.x + (p1.x - p0.x) * fraction,
                            p0.y + (p1.y - p0.y) * fraction
                    );
                    segments[i - 1] = line.getFactory().createLineString(new Coordinate[]{p0, between});
                }
                p0 = between;
            }
        }

        return segments;
    }

    private static void handleSplitSegments2(){
        // 创建一个示例线段 //new Coordinate[]{new Coordinate(0, 0), new Coordinate(10, 10)};
        Coordinate[] coords = new Coordinate[]{ new Coordinate(120.88271981764011, 31.9673506840457), new Coordinate(120.890231270701, 31.967307179403058)};

        GeometryFactory factory = new GeometryFactory();
        LineString line = factory.createLineString(coords);

        // 按照指定的距离均分线段
        double splitDistance = 10;
        LineString[] splitSegments = splitLineString(line, splitDistance);

        // 输出结果
        for (LineString segment : splitSegments) {
            System.out.println(segment.toText());
        }
    }

    //==第三种方法==================
    public static LineString[] splitGeodesicLineString(LineString line, double distance) {
        if (line == null || distance <= 0) {
            throw new IllegalArgumentException("Invalid input: line must not be null and distance must be greater than 0");
        }

        Coordinate[] coords = line.getCoordinates();
        //int numSegments2 = (int) Math.ceil(HaversineDistance.distance(coords[0].x, coords[0].y, coords[1].x, coords[1].y) / distance);
        int numSegments = (int) Math.ceil(HaversineDistance.calculate(coords[0], coords[1]) / distance);
        System.out.println("numSegments===="+numSegments);

        LineString[] segments = new LineString[numSegments - 1];
        Coordinate startCoord = coords[0];
        for (int i = 1; i < numSegments; i++) {
            double fraction = (double) i / numSegments;
            Coordinate midCoord = interpolateGeodesic(startCoord, coords[1], fraction);
            segments[i - 1] = createLineString(startCoord, midCoord);
            startCoord = midCoord;
        }

        return segments;
    }

    private static Coordinate interpolateGeodesic(Coordinate start, Coordinate end, double fraction) {
        // 使用球面距离插值算法（如Haversine公式）进行插值
        // 这里只是一个示意，实际应用中需要更复杂的算法考虑地球曲率
        return new Coordinate(
                start.x + (end.x - start.x) * fraction,
                start.y + (end.y - start.y) * fraction
        );
    }

    private static LineString createLineString(Coordinate start, Coordinate end) {
        GeometryFactory factory = new GeometryFactory();
        return factory.createLineString(new Coordinate[] { start, end });
    }

    private static void test1(){
        // 创建一个示例经纬度线段
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(116.387, 39.9), // 北京的经纬度
                new Coordinate(121.47, 31.23)  // 上海的经纬度
        };
        LineString line = new GeometryFactory().createLineString(coords);

        // 按照指定的距离均分线段
        double splitDistance = 100000; // 指定的距离，单位：米

        LineString[] splitSegments = splitGeodesicLineString(line, splitDistance);

        // 输出结果
        for (LineString segment : splitSegments) {
            System.out.println(segment.toText());
        }
        JSONArray pointAll = new JSONArray();
        for (int k=0;k<splitSegments.length;k++){
            LineString segment = splitSegments[k];
            Point pointS = segment.getStartPoint();
            List<Double> pointStartArr = new ArrayList<>();
            pointStartArr.add(pointS.getX());
            pointStartArr.add(pointS.getY());
            Point pointE = segment.getEndPoint();
            List<Double> pointEndArr = new ArrayList<>();
            pointEndArr.add(pointE.getX());
            pointEndArr.add(pointE.getY());
            if(k==0){
                pointAll.add(pointStartArr);
                pointAll.add(pointEndArr);
            }else{
                pointAll.add(pointEndArr);
            }
        }
        System.out.println(pointAll.toJSONString());

    }

    /**
     * 根据指定距离将线平均分成多线段
     * splitDistance = 10; // 指定的距离，单位：米
     */
    public static void multiPolylineSplit(double splitDistance){
        Coordinate[] coords0 = new Coordinate[]{
                new Coordinate(120.8819818718082,31.968842686141677),
                new Coordinate(120.8873361855384,31.968914331959404)
        };

        // 创建一个示例经纬度线段
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(120.893992408,31.971080023),
                new Coordinate(120.895285233,31.971283870)
        };
        LineString line = new GeometryFactory().createLineString(coords);

        double dis = HaversineDistance.calculate(coords[0], coords[1]);
        int numSegments = (int) Math.ceil(HaversineDistance.calculate(coords[0], coords[1]) / splitDistance);
        System.out.println(dis);
        System.out.println(numSegments);

        LineString[] splitSegments = splitGeodesicLineString(line, splitDistance);

        // 输出结果
        for (LineString segment : splitSegments) {
            String text = segment.toText();
        }
        JSONArray pointAll = new JSONArray();
        for (int k=0;k<splitSegments.length;k++){
            LineString segment = splitSegments[k];
            Point pointS = segment.getStartPoint();
            List<Double> pointStartArr = new ArrayList<>();
            pointStartArr.add(pointS.getX());
            pointStartArr.add(pointS.getY());
            pointStartArr.add(0.0);
            Point pointE = segment.getEndPoint();
            List<Double> pointEndArr = new ArrayList<>();
            pointEndArr.add(pointE.getX());
            pointEndArr.add(pointE.getY());
            pointEndArr.add(0.0);
            Coordinate[] coordOne = segment.getCoordinates();
            double dd = HaversineDistance.calculate(coordOne[0], coordOne[1]);
            System.out.println(dd);
            if(k==0){
                pointAll.add(pointStartArr);
                pointAll.add(pointEndArr);
            }else{
                pointAll.add(pointEndArr);
            }
        }
    }

    public static void main(String[] args) {
        multiPolylineSplit(25);
    }

}
