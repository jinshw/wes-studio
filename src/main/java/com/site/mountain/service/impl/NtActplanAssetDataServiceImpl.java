package com.site.mountain.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.site.mountain.dao.mysql.NtActplanAssetDataMapper;
import com.site.mountain.dto.DpAssetDictConstant;
import com.site.mountain.dto.DpAssetSaveDictConstant;
import com.site.mountain.dto.RespDpNiHikBaseDto;
import com.site.mountain.entity.NtActplanAssetData;
import com.site.mountain.entity.NtActplanAssetDict;
import com.site.mountain.service.NtActplanAssetDataService;
import com.site.mountain.service.NtActplanAssetDictService;
import com.site.mountain.utils.GeoJstPolylineUtils;
import com.site.mountain.utils.JsonUtil;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NtActplanAssetDataServiceImpl implements NtActplanAssetDataService {

    @Autowired
    NtActplanAssetDataMapper actplanAssetDataMapper;

    @Autowired
    NtActplanAssetDictService actplanAssetDictService;

    @Value("${splitDistance}")
    private double  splitDistance;

    @Override
    public int deleteByPrimaryKey(String assetDataId) {
        return actplanAssetDataMapper.deleteByPrimaryKey(assetDataId);
    }

    @Override
    public int insert(NtActplanAssetData record) {
        return actplanAssetDataMapper.insert(record);
    }

    @Override
    public int insertSelective(NtActplanAssetData record) {
        return actplanAssetDataMapper.insertSelective(record);
    }

    @Override
    public NtActplanAssetData selectByPrimaryKey(String assetDataId) {
        return actplanAssetDataMapper.selectByPrimaryKey(assetDataId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtActplanAssetData record) {
        return actplanAssetDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtActplanAssetData record) {
        return actplanAssetDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateIsDelByPrimaryKey(String assetDataId) {
        return actplanAssetDataMapper.updateIsDelByPrimaryKey(assetDataId);
    }

    @Override
    public int addAndUpdateActplanAssetData(RespDpNiHikBaseDto<NtActplanAssetData> params) {
        NtActplanAssetDict diParam = new NtActplanAssetDict();
        diParam.setPageSize(1000);
        diParam.setEntityType(DpAssetDictConstant.DP_ASSETDICT_ENTITY_POLYLINE);
        List<String> enTypeList = actplanAssetDictService.selectListByEntityType(diParam);
        int reNum = 0;
        NtActplanAssetData assetData = new NtActplanAssetData();
        if(params != null && !"".equals(params)){
            assetData = params.getPropertyInfo();
            if(StringUtil.isEmpty(assetData.getActplanId())){
                //return ResponseUtils.error("保存失败！未选预案！");
            }
            assetData.setIconUrl(params.getIconUrl());
            assetData.setTextColor(params.getTextColor());
            assetData.setType(params.getType());
            String pointsInfo = JSONArray.toJSONString(params.getPoints());
            assetData.setPolygon(pointsInfo);
            Double[] points = JsonUtil.jsonPointsToDoubleArray(assetData.getPolygon());
            if(points != null && points.length>0 && points.length<4){
                assetData.setLongitude(JsonUtil.doubleCapture14Str(points[0]));
                assetData.setLatitude(JsonUtil.doubleCapture14Str(points[1]));
            }
            if(points != null && points.length>2){
                assetData.setAltitude(JsonUtil.doubleCapture14Str(points[2]));
            }
            if(enTypeList.contains(params.getType())){
                double dista = GeoJstPolylineUtils.calculatePointsDistance(assetData.getPolygon());
                assetData.setLineLength(dista);
            }
            if(assetData.getLineStyle() !=null && (DpAssetDictConstant.DP_ASSETDICT_ENTITY_POLYLINE_GLOW).equals(assetData.getLineStyle())){
                String subPoints = GeoJstPolylineUtils.handleMultiPolylineSplitToPointsjson(assetData.getPolygon(),splitDistance);
                assetData.setSubPoints(subPoints);
            }
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            assetData.setCreateTime(timestamp);
            assetData.setIsDel(DpAssetSaveDictConstant.DP_ASSETSAVEDICT1);
            assetData.setOptTime(timestamp);
            assetData.setAssetDataId(params.getId());
            reNum =  actplanAssetDataMapper.replaceSelective(assetData);
        }
        return reNum;
    }

    @Override
    public List<NtActplanAssetData> selectList(NtActplanAssetData record) {
        return actplanAssetDataMapper.selectList(record);
    }
}
