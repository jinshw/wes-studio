package com.site.mountain.utils;

import org.locationtech.jts.geom.Coordinate;

public class HaversineDistance {
    private static final double R = 6371000; // 地球半径，单位米

    public static double calculate(Coordinate p1, Coordinate p2) {
        double lat1 = Math.toRadians(p1.y);
        double lon1 = Math.toRadians(p1.x);
        double lat2 = Math.toRadians(p2.y);
        double lon2 = Math.toRadians(p2.x);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public static double calculateVlue() {
        Coordinate p1 = new Coordinate(0, 0);
        Coordinate p2 = new Coordinate(10, 10);
        double distance = calculate(p1, p2);
        return distance;
    }

}
