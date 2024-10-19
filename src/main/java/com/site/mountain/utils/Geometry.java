package com.site.mountain.utils;

import java.util.ArrayList;
import java.util.List;

public class Geometry<T> {
    public String type;
    public List<T> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<T> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<T> coordinates) {
        this.coordinates = coordinates;
    }

    public Geometry(){
    }

    public Geometry(String type, List<T> coordinates){
        this.type = type;
        this.coordinates = coordinates;
    }

    public Geometry(String type,Double lon,Double lat){
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(lon);
        coordinates.add(lat);
        this.type = type;
        this.coordinates = (List<T>) coordinates;
    }

    public Geometry Geometry2(String type, List<T> coordinates){
        Geometry geometry = new Geometry();
        geometry.setType(type);
        geometry.setCoordinates(coordinates);
        return geometry;
    }

}
