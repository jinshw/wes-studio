package com.site.mountain.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtAreaFenceMapper;
import com.site.mountain.dao.mysql.NtHikCrossingMapper;
import com.site.mountain.dto.RespDpNiHikBaseDto;
import com.site.mountain.dto.RespDpNiHikCrossingDto;
import com.site.mountain.entity.NtAreaFence;
import com.site.mountain.entity.NtHikCrossing;
import com.site.mountain.request.CrossingResp;
import com.site.mountain.request.GetCrossingsWithPageRequest;
import com.site.mountain.request.RespBase;
import com.site.mountain.service.NtHikCrossingService;
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

@Service
@Transactional
public class NtHikCrossingServiceImpl implements NtHikCrossingService {

    @Autowired
    NtHikCrossingMapper hikCrossingMapper;

    @Autowired
    NtAreaFenceMapper areaFenceMapper;

    @Autowired
    ArtemisButtUtils artemisButtUtils;

    @Override
    public int updateByPrimaryKeySelective(NtHikCrossing record) {
        return hikCrossingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public NtHikCrossing selectByPrimaryKey(String indexCode) {
        return hikCrossingMapper.selectByPrimaryKey(indexCode);
    }

    @Override
    public int insertSelective(NtHikCrossing record) {
        return hikCrossingMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String indexCode) {
        return hikCrossingMapper.deleteByPrimaryKey(indexCode);
    }

    @Override
    public int deleteList(List<NtHikCrossing> list) {
        return 0;
    }

    @Override
    public List<NtHikCrossing> selectList(NtHikCrossing record) {
        return hikCrossingMapper.selectList(record);
    }

    @Override
    public PageInfo<NtHikCrossing> selectListByPage(NtHikCrossing record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtHikCrossing> list = hikCrossingMapper.selectList(record);
        PageInfo<NtHikCrossing> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public void syncCrossingData() {
        List<NtHikCrossing> crossinglist = new ArrayList<>();
        int total = 0;
        int pageNo = 1;
        int pageSize = 1000;
        boolean qFlag = true;
        try {
            GetCrossingsWithPageRequest crossingsWithPageRequest = new GetCrossingsWithPageRequest();
            while(qFlag){
                crossingsWithPageRequest.setPageNo(pageNo);
                crossingsWithPageRequest.setPageSize(pageSize);
                crossingsWithPageRequest.setTreeCode("0");
                String crossingResult= artemisButtUtils.getCrossingsWithPage(crossingsWithPageRequest);
                if(crossingResult == null){
                    qFlag = false;
                    break;
                }
                RespBase<CrossingResp> respBase = JsonUtil.jsonToObject(crossingResult, new TypeReference<RespBase<CrossingResp>>() {});
                if(respBase.isSuccess()){
                    List<NtHikCrossing> hikCrossList = respBase.getData().getList();
                    if(hikCrossList.size() !=0 && hikCrossList.size()<pageSize){
                        crossinglist.addAll(hikCrossList);
                        qFlag = false;
                    }else{
                        pageNo++;
                        crossinglist.addAll(hikCrossList);
                    }
                }else{
                    qFlag = false;
                }
            }
            hikCrossingMapper.replaceSelectiveBatch(crossinglist);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<NtHikCrossing> judgeCrossingInFence() {
        NtHikCrossing record = new NtHikCrossing();
        record.setPageSize(10000);
        List<NtHikCrossing> camerasInFence = new ArrayList<>();
        try{
            NtAreaFence areaFence = new NtAreaFence();
            areaFence.setStatus(1);
            areaFence.setType("1");
            List<NtAreaFence> areaFenceList= areaFenceMapper.selectList(areaFence);
            if(areaFenceList.size() <1){
                return camerasInFence;
            }

            List<NtHikCrossing> camerasList = selectList(record);
            for(NtAreaFence fence:areaFenceList) {
                String polygonWkt = GeoUtil.transformPolygons(fence.getPolygons());
                for(NtHikCrossing camera:camerasList){
                    if(StringUtils.isEmpty(camera.getLatitude()) || StringUtils.isEmpty(camera.getLongitude())){
                        continue;
                    }
                    Double lat = Double.valueOf(camera.getLatitude());
                    Double lon = Double.valueOf(camera.getLongitude());
                    String pointWkt = GeoUtil.transformPoint(lat,lon);
                    boolean boo = GeoUtil.containsPointPolygon(pointWkt,polygonWkt);
                    if(boo){
                        camerasInFence.add(camera);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return camerasInFence;
    }

    @Override
    public List<RespDpNiHikBaseDto<RespDpNiHikCrossingDto>> dpCrossingBaseConvert(List<NtHikCrossing> camerasList) {
        List<RespDpNiHikBaseDto<RespDpNiHikCrossingDto>> respDpCaList = new ArrayList<>();
        try{
            for(NtHikCrossing entity:camerasList){
                if(StringUtils.isEmpty(entity.getLatitude()) || StringUtils.isEmpty(entity.getLongitude())){
                    continue;
                }
                RespDpNiHikBaseDto reCamera = new RespDpNiHikBaseDto();
                reCamera.setId(entity.getIndexCode());
                reCamera.setType(entity.getPoiType());
                reCamera.setIconUrl(entity.getIconUrl());
                reCamera.setTextColor(entity.getTextColor());
                Double[] points = new Double[2];
                points[0] = Double.valueOf(entity.getLongitude());
                points[1] = Double.valueOf(entity.getLatitude());
                reCamera.setPoints(points);
                RespDpNiHikCrossingDto cameraTemp = new RespDpNiHikCrossingDto();
                BeanUtils.copyProperties(entity,cameraTemp);
                reCamera.setPropertyInfo(cameraTemp);
                respDpCaList.add(reCamera);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return respDpCaList;
    }

}
