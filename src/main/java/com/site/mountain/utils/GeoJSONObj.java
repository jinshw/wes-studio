package com.site.mountain.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoJSONObj<T> {
    public String type = "FeatureCollection";
    public List<Feature> features = new ArrayList<>();

    /**
     *
     * @param list
     * @param geoType
     * @param featureType
     * @param geometryType
     * @return
     */
    public Map<String,Object> create(List<T> list, String geoType,
                                     String featureType,
                                     String geometryType){
        Map<String,Object> reMap = new HashMap<>();
        if(list.size()<1){
            return reMap;
        }
        GeoJSONObj geoJSONObj = new GeoJSONObj();
        List<Feature> featureList = new ArrayList<>();
        String poiType = "geojson";
        for(int i=0;i<list.size();i++){
            Object entity = list.get(i);
            Feature feature = new Feature();
            Double lon = 0D;
            Double lat = 0D;
            String jsonObjStr = JSONObject.toJSONString(entity);
            JSONObject jsonObjectEntity = JSONObject.parseObject(jsonObjStr);
            if(jsonObjectEntity.containsKey("lon")){
                lon = jsonObjectEntity.getDouble("lon");
            }
            if(jsonObjectEntity.containsKey("lat")){
                lat = jsonObjectEntity.getDouble("lat");
            }
            if(jsonObjectEntity.containsKey("id")){
                jsonObjectEntity.put("poiCode",jsonObjectEntity.getLong("id"));
            }
            if(jsonObjectEntity.containsKey("poiType")){
                poiType = jsonObjectEntity.getString("poiType");
            }
            feature = feature.create(jsonObjectEntity,featureType,geometryType,lon,lat);
            featureList.add(feature);
        }
        geoJSONObj.setFeatures(featureList);
        if(geoType != null && !geoType.equals("")){
            geoJSONObj.setType(geoType);
        }

        reMap.put(poiType,geoJSONObj);
        return reMap;
    }

    public  GeoJSONObj(){
    }

    public  GeoJSONObj(String geoType,List<Feature> features){
        if(geoType != null && !geoType.equals("")){
            this.type = geoType;
        }
        this.features = features;
    }

    public  GeoJSONObj(List<Feature> features){
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
