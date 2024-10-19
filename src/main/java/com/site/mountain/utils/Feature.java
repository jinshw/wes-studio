package com.site.mountain.utils;

public class Feature {
    public String type = "Feature";
    public Object properties;
    public Geometry geometry;

    public Feature(){

    }
    public Feature(String type, Object properties, Geometry geometry){
        this.type = type;
        this.properties = properties;
        this.geometry = geometry;
    }

    public Feature create( Object properties,
            String geometryType,Double lon,Double lat){
        Feature feature = new Feature();
        feature.setProperties(properties);
        Geometry geometry = new Geometry(geometryType,lon,lat);
        feature.setGeometry(geometry);
        return feature;
    }

    public Feature create(Object properties,String featureType,
                          String geometryType,Double lon,Double lat){
        Feature feature = new Feature();
        feature.setProperties(properties);
        Geometry geometry = new Geometry(geometryType,lon,lat);
        feature.setGeometry(geometry);
        if(featureType != null && !featureType.equals("")){
            feature.setType(featureType);
        }
        return feature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
