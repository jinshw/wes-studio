package com.site.mountain.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtAreaFenceMapper;
import com.site.mountain.dao.mysql.NtHikCamerasMapper;
import com.site.mountain.entity.NtAreaFence;
import com.site.mountain.entity.NtHikCameras;
import com.site.mountain.dto.RespDpNiHikBaseDto;
import com.site.mountain.dto.RespDpNiHikCameraDto;
import com.site.mountain.request.CamerasRequest;
import com.site.mountain.request.CamerasResp;
import com.site.mountain.request.PreviewURLsRequest;
import com.site.mountain.request.RespBase;
import com.site.mountain.service.NtHikCamerasService;
import com.site.mountain.utils.ArtemisButtUtils;
import com.site.mountain.utils.GeoUtil;
import com.site.mountain.utils.JsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NtHikCamerasServiceImpl implements NtHikCamerasService {

    @Autowired
    NtHikCamerasMapper hikCamerasMapper;

    @Autowired
    NtAreaFenceMapper areaFenceMapper;

    @Autowired
    ArtemisButtUtils artemisButtUtils;
    
    @Override
    public int updateByPrimaryKeySelective(NtHikCameras record) {
        return hikCamerasMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public NtHikCameras selectByPrimaryKey(String cameraIndexCode) {
        return hikCamerasMapper.selectByPrimaryKey(cameraIndexCode);
    }

    @Override
    public int insertSelective(NtHikCameras record) {
        return hikCamerasMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String cameraIndexCode) {
        return hikCamerasMapper.deleteByPrimaryKey(cameraIndexCode);
    }

    @Override
    public int deleteList(List<NtHikCameras> list) {
        return 0;
    }

    @Override
    public List<NtHikCameras> selectList(NtHikCameras record) {
        return hikCamerasMapper.selectList(record);
    }

    @Override
    public PageInfo<NtHikCameras> selectListByPage(NtHikCameras record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtHikCameras> list = hikCamerasMapper.selectList(record);
        PageInfo<NtHikCameras> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public String previewURLs(PreviewURLsRequest previewURLsRequest) throws Exception{
        previewURLsRequest.setExpand("transcode=1");
        String previewURLsResult = artemisButtUtils.previewURLs(previewURLsRequest);
        return previewURLsResult;
    }

    @Override
    public void syncCameraData() {
        List<NtHikCameras> cameraslist = new ArrayList<>();
        int total = 0;
        int pageNo = 1;
        int pageSize = 1000;
        boolean qFlag = true;
        try {
            CamerasRequest camerasRequest = new CamerasRequest();
            while(qFlag){
                camerasRequest.setPageNo(pageNo);
                camerasRequest.setPageSize(pageSize);
                camerasRequest.setTreeCode("0");
                String camerasResult= artemisButtUtils.cameras(camerasRequest);
                if(camerasResult == null){
                    qFlag = false;
                    break;
                }
                RespBase<CamerasResp> respBase = JsonUtil.jsonToObject(camerasResult, new TypeReference<RespBase<CamerasResp>>() {});
                if(respBase.isSuccess()){
                    List<NtHikCameras> hikCaList = respBase.getData().getList();
                    if(hikCaList.size() !=0 && hikCaList.size()<pageSize){
                        cameraslist.addAll(hikCaList);
                        qFlag = false;
                    }else{
                        pageNo++;
                        cameraslist.addAll(hikCaList);
                    }
                }else{
                    qFlag = false;
                }
            }
            hikCamerasMapper.replaceSelectiveBatch(cameraslist);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<NtHikCameras> judgeCameraInFence(Map<String,Object> params) {
        NtHikCameras record = new NtHikCameras();
        record.setPageSize(30000);
        List<NtHikCameras> camerasInFence = new ArrayList<>();
        try{
            //para.put("actplanId",params.getActplanId());
            if(params.containsKey("actplanId") && StringUtils.isEmpty(params.get("actplanId"))){
                return camerasInFence;
            }
            NtAreaFence areaFence = new NtAreaFence();
            areaFence.setStatus(1);
            //areaFence.setType("1");
            areaFence.setActplanId(params.get("actplanId").toString());
            List<NtAreaFence> areaFenceList= areaFenceMapper.selectList(areaFence);
            if(areaFenceList.size() <1){
                return camerasInFence;
            }

            List<String> camIdList = new ArrayList<>();
            List<NtHikCameras> camerasList = selectList(record);
            for(NtAreaFence fence:areaFenceList) {
                String polygonWkt = GeoUtil.transformPolygons(fence.getPolygons());
                for(NtHikCameras camera:camerasList){
                    if(camIdList.contains(camera.getCameraIndexCode())){
                        continue;
                    }
                    if(StringUtils.isEmpty(camera.getLatitude()) || StringUtils.isEmpty(camera.getLongitude())){
                        continue;
                    }
                    Double lat = Double.valueOf(camera.getLatitude());
                    Double lon = Double.valueOf(camera.getLongitude());
                    String pointWkt = GeoUtil.transformPoint(lat,lon);
                    boolean boo = GeoUtil.containsPointPolygon(pointWkt,polygonWkt);
                    if(boo){
                        camerasInFence.add(camera);
                        camIdList.add(camera.getCameraIndexCode());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return camerasInFence;
    }

    @Override
    public List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBaseConvert(List<NtHikCameras> camerasList) {
        List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> respDpCaList = new ArrayList<>();
        try{
            for(NtHikCameras entity:camerasList){
                if(StringUtils.isEmpty(entity.getLatitude()) || StringUtils.isEmpty(entity.getLongitude())){
                    continue;
                }
                RespDpNiHikBaseDto reCamera = new RespDpNiHikBaseDto();
                reCamera.setId(entity.getCameraIndexCode());
                reCamera.setType(entity.getPoiType());
                reCamera.setIconUrl(entity.getIconUrl());
                reCamera.setTextColor(entity.getTextColor());
                Double[] points = new Double[2];
                points[0] = Double.valueOf(entity.getLongitude());
                points[1] = Double.valueOf(entity.getLatitude());
                reCamera.setPoints(points);
                RespDpNiHikCameraDto cameraTemp = new RespDpNiHikCameraDto();
                BeanUtils.copyProperties(entity,cameraTemp);
                reCamera.setPropertyInfo(cameraTemp);
                respDpCaList.add(reCamera);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return respDpCaList;
    }

    @Override
    public int addAndUpdateCamera(RespDpNiHikBaseDto<RespDpNiHikCameraDto> record) {
        int reNum = 0;
        if(record != null && !"".equals(record)) {
            RespDpNiHikCameraDto cameraDto = record.getPropertyInfo();
            NtHikCameras entity = new NtHikCameras();
            BeanUtils.copyProperties(cameraDto,entity);
            entity.setIconUrl(record.getIconUrl());
            entity.setTextColor(record.getTextColor());
            JSONArray pointsArr = JSONArray.parseArray(JSONArray.toJSONString(record.getPoints()));
            Double[] points = JsonUtil.jsonArrayToDoubleArray(pointsArr);
            if(points.length>0 && points.length<4){
                entity.setLongitude(points[0].toString());
                entity.setLatitude(points[1].toString());
            }
            if(points.length>2){
                entity.setAltitude(points[2].toString());
            }
            entity.setIsScope("1");
            entity.setCameraIndexCode(record.getId());
            reNum =  hikCamerasMapper.replaceSelective(entity);
        }
        return reNum;
    }

    @Override
    public int updateIsDelByPrimaryKey(String uuid) {
        return hikCamerasMapper.updateIsDelByPrimaryKey(uuid);
    }

}
