package com.site.mountain.utils;


import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

public class GisUtils {
    private static double radius = 6378.137;// 单位千米

    /**
     * 两个点之间距离
     *
     * @param c1
     * @param c2
     * @return
     */
    public static double getPointDistance(Coordinate c1, Coordinate c2) {
		/*
		double lineLength = Geodesic.WGS84.Inverse(c0.y, c0.x, c1.y, c1.x).s12;
		return lineLength;
		*/
        double lat1 = (Math.PI / 180) * c1.y;
        double lat2 = (Math.PI / 180) * c2.y;
        double lng1 = (Math.PI / 180) * c1.x;
        double lng2 = (Math.PI / 180) * c2.x;
        //因此AB两点的球面距离为:{arccos[sinb*siny+cosb*cosy*cos(a-x)]}*R
        //地球半径
        double dist = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2 - lng1)) * radius;
        return dist;
    }

    /**
     * wgs84（EPSG:4326）转成EPSG:3857
     *
     * @param sourceCoordinate
     * @return
     */
    public static Coordinate wgs84ToMercator(Coordinate sourceCoordinate) {
        // 4326到3857转换
        CoordinateReferenceSystem sourceCRS = null;
        Coordinate coorDst = new Coordinate();
        try {
            sourceCRS = CRS.decode("EPSG:4326");
            CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:3857");
            MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
            JTS.transform(sourceCoordinate, coorDst, transform);
            System.out.println(coorDst);
        } catch (FactoryException | TransformException e) {
            e.printStackTrace();
        }
        return coorDst;
    }

    public static void main(String[] args) {
        double lon1 = 92.4819020463424;
        double lat1 = 43.2509190028098;
        double lon2 = 92.4825524090665;
        double lat2 = 43.250908732552;
        Coordinate c1 = new Coordinate(lon1, lat1);
        Coordinate c2 = new Coordinate(lon2, lat2);
        double pointDistance = getPointDistance(c1, c2);
        System.out.println("pointDistance..." + pointDistance);
    }
}
