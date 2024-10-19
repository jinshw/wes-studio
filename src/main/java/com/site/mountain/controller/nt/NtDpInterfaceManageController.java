package com.site.mountain.controller.nt;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mysql.jdbc.StringUtils;
import com.site.mountain.dto.*;
import com.site.mountain.entity.*;
import com.site.mountain.request.GdbitDataDto;
import com.site.mountain.request.PreviewURLsRequest;
import com.site.mountain.request.RespBase;
import com.site.mountain.service.*;
import com.site.mountain.utils.*;
import io.swagger.annotations.ApiOperation;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/nt/dpInteManage")
public class NtDpInterfaceManageController {

    @Autowired
    NtHikCamerasService hikCamerasService;

    @Autowired
    NtHikCrossingService hikCrossingService;

    @Autowired
    NtActplanInfoService actplanInfoService;

    @Autowired
    NtActplanAssetDictService actplanAssetDictService;

    @Autowired
    NtActplanAssetDataService actplanAssetDataService;

    @Autowired
    NtIbdFactorDataService ibdFactorDataService;

    @Autowired
    NtIbdCrossingDataService ibdCrossingDataService;

    @Autowired
    NtIbdAssetRoadService assetRoadService;

    @Autowired
    NtIbdAssetSectionService assetSectionService;

    @Autowired
    NtIbdTrafficPoleService trafficPoleService;

    @Autowired
    NtIbdTrafficHldService trafficHldService;

    @Autowired
    NtLocationAssetDictService locationAssetDictService;

    @Autowired
    NtTrffassetLaserRadarService trffLaserRadarService;

    @Autowired
    MapDeviceService mapDeviceService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    ProjectFileService projectFileService;

    @Autowired
    NtTrffassetChangeInfoMarkService trffassetChangeInfoMarkService;

    private Integer PageSize = 10000;

    @Value("${splitDistance}")
    private double splitDistance;

    @ApiOperation(value = "大屏-查询视频点资源数据(全部)", notes = "大屏-查询视频点资源数据(全部)")
    @RequestMapping(value = "/queryAreaCameraList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAreaCameraList(@RequestBody Map<String,Object> params) {
        List<NtHikCameras> cameras = new ArrayList<>();
        try{
            NtHikCameras record = new NtHikCameras();
            record.setPageSize(PageSize);
            cameras = hikCamerasService.selectList(record);
            List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBases = hikCamerasService.dpCameraBaseConvert(cameras);
            return ResponseUtils.success(dpCameraBases.size(),dpCameraBases);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "大屏-查询卡点资源数据(全部)", notes = "大屏-查询卡点资源数据(全部)")
    @RequestMapping(value = "/queryCrossingList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryCrossingList(@RequestBody Map<String,Object> params) {
        List<NtHikCrossing> crossings = new ArrayList<>();
        try{
            NtHikCrossing record = new NtHikCrossing();
            record.setPageSize(PageSize);
            crossings = hikCrossingService.selectList(record);
            List<RespDpNiHikBaseDto<RespDpNiHikCrossingDto>> dpCameraBases = hikCrossingService.dpCrossingBaseConvert(crossings);
            return ResponseUtils.success(dpCameraBases.size(),dpCameraBases);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "大屏-查询视频点资源数据(在围栏中)", notes = "大屏-查询视频点资源数据(在围栏中)")
    @RequestMapping(value = "/queryAreaCameraInFenceList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAreaCameraInFenceList(@RequestBody Map<String,Object> params) {
        try{
            List<NtHikCameras> cameras = hikCamerasService.judgeCameraInFence(params);
            List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBases = hikCamerasService.dpCameraBaseConvert(cameras);
            return ResponseUtils.success(dpCameraBases.size(),dpCameraBases);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("失败！");
        }
    }

    @ApiOperation(value = "大屏-查询卡口点资源数据(在围栏中)", notes = "大屏-查询卡口点资源数据(在围栏中)")
    @RequestMapping(value = "/queryAreaCrossingInFenceList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAreaCrossingInFenceList(@RequestBody Map<String,Object> params) {
        try{
            List<NtHikCrossing> cameras = hikCrossingService.judgeCrossingInFence();
            List<RespDpNiHikBaseDto<RespDpNiHikCrossingDto>> dpCameraBases = hikCrossingService.dpCrossingBaseConvert(cameras);
            return ResponseUtils.success(dpCameraBases.size(),dpCameraBases);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("失败！");
        }
    }

    @ApiOperation(value = "大屏-查询视频点资源数据(在围栏中),大屏-查询卡口点资源数据(在围栏中)", notes = "大屏-查询视频点资源数据(在围栏中),大屏-查询卡口点资源数据(在围栏中)")
    @RequestMapping(value = "/queryAreaCameraCrossingInFenceList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAreaCameraCrossingInFenceList(@RequestBody Map<String,Object> params) {
        Map reMap = new HashMap();
        List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBases = new ArrayList<>();
        List<RespDpNiHikBaseDto<RespDpNiHikCrossingDto>> dpCrossingBases = new ArrayList<>();

        try{
            List<NtHikCameras> cameras = hikCamerasService.judgeCameraInFence(params);
            dpCameraBases= hikCamerasService.dpCameraBaseConvert(cameras);

            //去掉卡口
            //List<NtHikCrossing> crossings = hikCrossingService.judgeCrossingInFence();
            //dpCrossingBases = hikCrossingService.dpCrossingBaseConvert(crossings);

            reMap.put(DpNiHikConstant.DP_HIK_TYPE1,dpCameraBases);
            reMap.put(DpNiHikConstant.DP_HIK_TYPE2,dpCrossingBases);
            return ResponseUtils.success(reMap);
        }catch (Exception e){
            e.printStackTrace();
            reMap.put(DpNiHikConstant.DP_HIK_TYPE1,dpCameraBases);
            reMap.put(DpNiHikConstant.DP_HIK_TYPE2,dpCrossingBases);
            return ResponseUtils.error(0,reMap);
        }
    }

    @ApiOperation(value = "大屏-查询视频点览取流URL", notes = "大屏-查询视频点览取流URL")
    @RequestMapping(value = "/queryCameraPreviewUrl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryCameraPreviewUrl(@RequestBody PreviewURLsRequest previewURLsRequest) {
        Map reMap = new HashMap();
        try{
            String previewURLsResult = hikCamerasService.previewURLs(previewURLsRequest);
            if(previewURLsResult != null){
                RespBase<Map<String,Object>> respBase = JsonUtil.jsonToObject(previewURLsResult, new TypeReference<RespBase<Map<String,Object>>>() {});
                if(respBase.isSuccess()){
                    Map<String,Object> cameraMap = respBase.getData();
                    reMap.put("url",cameraMap.get("url").toString());
                }
            }
            return ResponseUtils.success(reMap);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "同步海康接口视频点数据", notes = "同步海康接口视频点数据")
    @RequestMapping(value = "/syncCameraData", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtils syncCameraData(@RequestBody Map<String,Object> params) {
        try{
            hikCamerasService.syncCameraData();
            return ResponseUtils.success("camera同步成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("失败！");
        }
    }

    @ApiOperation(value = "同步海康接口卡口点数据", notes = "同步海康接口卡口点数据")
    @RequestMapping(value = "/syncCrossingData", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtils syncCrossingData(@RequestBody Map<String,Object> params) {
        try{
            hikCrossingService.syncCrossingData();
            return ResponseUtils.success("卡口数据同步成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("失败！");
        }
    }

    @ApiOperation(value = "获取预案列表", notes = "获取预案列表")
    @RequestMapping(value = "/queryActplanInfoList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryActplanInfoList(@RequestBody NtActplanInfo params) {
        List<NtActplanInfo> actplanInfoList = new ArrayList<>();
        try{
            params.setPageSize(PageSize);
            actplanInfoList = actplanInfoService.selectList(params);
            return ResponseUtils.success(actplanInfoList.size(),actplanInfoList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("查询失败！");
        }
    }

    @ApiOperation(value = "根据预案查询-当前资产列表", notes = "根据预案查询-当前资产列表")
    @RequestMapping(value = "/queryAssetDictListByActplan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAssetDictListByActplan(@RequestBody NtActplanAssetData params) {
        List<NtActplanAssetDict> actplanAssetDictList = new ArrayList<>();
        try{
            NtActplanAssetDict record = new NtActplanAssetDict();
            NtActplanInfo actplanInfo = actplanInfoService.selectByPrimaryKey(params.getActplanId());
            if(actplanInfo != null){
                String assetDictGroup = actplanInfo.getAssetDictGroup();
                String[] assetDictGroupArr = assetDictGroup.split(",");
                record.setDictTypeList(Arrays.asList(assetDictGroupArr));
            }
            record.setPageSize(PageSize);
            actplanAssetDictList = actplanAssetDictService.selectList(record);
            for(NtActplanAssetDict entity:actplanAssetDictList){
                if((DpAssetDictConstant.DP_ASSETDICT_SHEXIANGTOU).equals(entity.getType())){
                    Map<String,Object> para = new HashMap<>();
                    para.put("actplanId",params.getActplanId());
                    List<NtHikCameras> cameras = hikCamerasService.judgeCameraInFence(para);
                    List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBases= hikCamerasService.dpCameraBaseConvert(cameras);
                    entity.setPositionData(dpCameraBases);
                }else{
                    List<RespDpNiHikBaseDto> respDpBaseDtoList = new ArrayList<>();
                    params.setPageSize(PageSize);
                    params.setType(entity.getType());
                    List<NtActplanAssetData> assetDataList = actplanAssetDataService.selectList(params);
                    for(NtActplanAssetData assetData:assetDataList){
                        RespDpNiHikBaseDto respDpBaseDto = new RespDpNiHikBaseDto();
                        respDpBaseDto.setId(assetData.getAssetDataId());
                        respDpBaseDto.setType(assetData.getType());
                        respDpBaseDto.setIconUrl(assetData.getIconUrl());
                        respDpBaseDto.setTextColor(assetData.getTextColor());
                        respDpBaseDto.setPoints(JSONArray.parseArray(assetData.getPolygon()));
                        if(!StringUtil.isEmpty(assetData.getSubPoints())){
                            respDpBaseDto.setSubPoints(JSONArray.parseArray(assetData.getSubPoints()));
                        }
                        respDpBaseDto.setPropertyInfo(assetData);
                        respDpBaseDtoList.add(respDpBaseDto);
                    }
                    entity.setPositionData(respDpBaseDtoList);
                }

            }
            return ResponseUtils.success(actplanAssetDictList.size(),actplanAssetDictList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("查询失败！");
        }
    }

    @ApiOperation(value = "根据预案和当前资产类型-查询该类型的资产数据列表", notes = "根据预案和当前资产类型-查询该类型的资产数据列表")
    @RequestMapping(value = "/queryAssetDataListByPlanAndDict", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAssetDataListByPlanAndDict(@RequestBody NtActplanAssetData params) {
        List<NtActplanAssetDict> actplanAssetDictList = new ArrayList<>();
        List<RespDpNiHikBaseDto> respDpBaseDtoList = new ArrayList<>();
        try{
            NtActplanAssetDict record = new NtActplanAssetDict();
            record.setType(params.getType());
            record.setPageSize(PageSize);
            actplanAssetDictList = actplanAssetDictService.selectList(record);
            if(actplanAssetDictList.size() >0){
                record = actplanAssetDictList.get(0);
            }
            if((DpAssetDictConstant.DP_ASSETDICT_SHEXIANGTOU).equals(params.getType())){
                Map<String,Object> para = new HashMap<>();
                para.put("actplanId",params.getActplanId());
                List<NtHikCameras> cameras = hikCamerasService.judgeCameraInFence(para);
                List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBases= hikCamerasService.dpCameraBaseConvert(cameras);
                record.setPositionData(dpCameraBases);
            }else{
                params.setPageSize(PageSize);
                List<NtActplanAssetData> assetDataList = actplanAssetDataService.selectList(params);
                for(NtActplanAssetData assetData:assetDataList){
                    RespDpNiHikBaseDto respDpBaseDto = new RespDpNiHikBaseDto();
                    respDpBaseDto.setId(assetData.getAssetDataId());
                    respDpBaseDto.setType(assetData.getType());
                    respDpBaseDto.setIconUrl(assetData.getIconUrl());
                    respDpBaseDto.setTextColor(assetData.getTextColor());
                    respDpBaseDto.setPoints(JSONArray.parseArray(assetData.getPolygon()));
                    if(!StringUtil.isEmpty(assetData.getSubPoints())){
                        respDpBaseDto.setSubPoints(JSONArray.parseArray(assetData.getSubPoints()));
                    }
                    respDpBaseDto.setPropertyInfo(assetData);
                    respDpBaseDtoList.add(respDpBaseDto);
                }
                record.setPositionData(respDpBaseDtoList);
            }
            return ResponseUtils.success(record);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("查询失败！");
        }
    }

    @ApiOperation(value = "根据预案查询-当前资产类型点的详情数据", notes = "根据预案查询-当前资产列表")
    @RequestMapping(value = "/queryAssetDataDetail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAssetDataDetail(@RequestBody RespDpNiHikBaseDto<NtActplanAssetData> params) {
        try{
            RespDpNiHikBaseDto respDpBaseDto = new RespDpNiHikBaseDto();
            NtActplanAssetData assetData = actplanAssetDataService.selectByPrimaryKey(params.getId());
            if(assetData != null){
                respDpBaseDto.setId(assetData.getAssetDataId());
                respDpBaseDto.setType(assetData.getType());
                respDpBaseDto.setIconUrl(assetData.getIconUrl());
                respDpBaseDto.setTextColor(assetData.getTextColor());
                respDpBaseDto.setPoints(JSONArray.parseArray(assetData.getPolygon()));
                respDpBaseDto.setPropertyInfo(assetData);
            }else{
                NtHikCameras record = new NtHikCameras();
                record.setCameraIndexCode(params.getId());
                List<NtHikCameras> cameras = hikCamerasService.selectList(record);
                List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBases= hikCamerasService.dpCameraBaseConvert(cameras);
                if(dpCameraBases.size()>0){
                    respDpBaseDto = dpCameraBases.get(0);
                }
            }
            return ResponseUtils.success(respDpBaseDto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("查询失败！");
        }
    }

    @ApiOperation(value = "保存和修改-当前资产类型点的详情数据", notes = "保存和修改-当前资产类型点的详情数据")
    @RequestMapping(value = "/addAndUpdateActplanAssetData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils addAndUpdateActplanAssetData(@RequestBody RespDpNiHikBaseDto params) {
        try{
            int reNum = 0;
            if(StringUtil.isEmpty(params.getId())){
                return ResponseUtils.error("保存失败！id不能为空");
            }
            if((DpAssetDictConstant.DP_ASSETDICT_SHEXIANGTOU).equals(params.getType())){
                Map propertyMap = (Map)params.getPropertyInfo();
                RespDpNiHikCameraDto tempData = new RespDpNiHikCameraDto();
                MapToBeanUtils.mapToBean(propertyMap,tempData);
                params.setPropertyInfo(tempData);
                reNum = hikCamerasService.addAndUpdateCamera(params);
            }else{
                Map propertyMap = (Map)params.getPropertyInfo();
                NtActplanAssetData tempData = new NtActplanAssetData();
                MapToBeanUtils.mapToBean(propertyMap,tempData);
                params.setPropertyInfo(tempData);
                reNum = actplanAssetDataService.addAndUpdateActplanAssetData(params);
            }
            if(reNum >0){
                return ResponseUtils.success("保存成功！",params.getId());
            }else if(reNum >0){
                return ResponseUtils.success("修改成功！",params.getId());
            }else{
                return ResponseUtils.error("保存失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("保存失败！");
        }
    }

    @ApiOperation(value = "批量-保存和修改-当前资产类型点的详情数据", notes = "批量-保存和修改-当前资产类型点的详情数据")
    @RequestMapping(value = "/addAndUpdateBatchActplanAssetData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils addAndUpdateBatchActplanAssetData(@RequestBody List<RespDpNiHikBaseDto> records) {
        try{
            boolean reFlag = false;
            for(RespDpNiHikBaseDto params:records){
                String uuid = UUIDUtil.create32UUID();
                if(StringUtil.isEmpty(params.getId())){
                    params.setId(uuid);
                }
                int reNumTemp = 0;
                if((DpAssetDictConstant.DP_ASSETDICT_SHEXIANGTOU).equals(params.getType())){
                    Map propertyMap = (Map)params.getPropertyInfo();
                    RespDpNiHikCameraDto tempData = new RespDpNiHikCameraDto();
                    MapToBeanUtils.mapToBean(propertyMap,tempData);
                    params.setPropertyInfo(tempData);
                    reNumTemp = hikCamerasService.addAndUpdateCamera(params);
                }else{
                    Map propertyMap = (Map)params.getPropertyInfo();
                    NtActplanAssetData tempData = new NtActplanAssetData();
                    MapToBeanUtils.mapToBean(propertyMap,tempData);
                    params.setPropertyInfo(tempData);
                    reNumTemp = actplanAssetDataService.addAndUpdateActplanAssetData(params);
                }
                if(reNumTemp == 0 ){
                    reFlag = true;
                    break;
                }
            }
            if(reFlag){
                return ResponseUtils.error("批量保存失败！");
            }else{
                return ResponseUtils.success("批量保存成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("批量保存失败！");
        }
    }

    @ApiOperation(value = "根据当前点id删除数据资产数据", notes = "根据当前点id删除数据资产数据")
    @RequestMapping(value = "/deleteAssetDataById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils deleteAssetDataById(@RequestBody RespDpNiHikBaseDto<NtActplanAssetData> params) {
        try{
            int reNum = 0;
            if(!StringUtil.isEmpty(params.getId())){
                reNum = actplanAssetDataService.updateIsDelByPrimaryKey(params.getId());
                if(reNum == 0){
                    reNum = hikCamerasService.updateIsDelByPrimaryKey(params.getId());
                }
            }
            if(reNum > 0){
                return ResponseUtils.success("删除成功！");
            }else{
                return ResponseUtils.success("删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("删除失败！");
        }
    }

    @ApiOperation(value = "计算动态流线", notes = "计算动态流线")
    @RequestMapping(value = "/refreshCalculateDynamicFlowLines", method = RequestMethod.GET)
    @ResponseBody
    public ResponseUtils refreshCalculateDynamicFlowLines() {
        try{
            boolean flag = true;
            NtActplanAssetData record = new NtActplanAssetData();
            record.setPageSize(PageSize);
            record.setLineStyle(DpAssetDictConstant.DP_ASSETDICT_ENTITY_POLYLINE_GLOW);
            List<NtActplanAssetData> list = actplanAssetDataService.selectList(record);
            for(NtActplanAssetData assetData:list){
                if(!StringUtil.isEmpty(assetData.getPolygon())){
                    String subPoints = GeoJstPolylineUtils.handleMultiPolylineSplitToPointsjson(assetData.getPolygon(),splitDistance);
                    assetData.setSubPoints(subPoints);
                    int reNum = actplanAssetDataService.updateByPrimaryKeySelective(assetData);
                    if(reNum==0){
                        flag = false;
                    }
                }
            }

            if(flag){
                return ResponseUtils.success("重新计算线分段成功！");
            }else{
                return ResponseUtils.success("重新计算线分段失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("重新计算线分段失败！");
        }
    }

    @ApiOperation(value = "ibd路口数据查询", notes = "ibd路口数据查询")
    @RequestMapping(value = "/queryIbdRoadCrossingList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryIbdRoadCrossingList(@RequestBody NtIbdCrossingData params) {
        try{
            params.setPageSize(PageSize);
            List<NtIbdCrossingData> crossingDataList = ibdCrossingDataService.selectList(params);
            List<NtIbdTrafficPole> poleList = trafficPoleService.selectList(new NtIbdTrafficPole(PageSize));
            for(int i=0;i<crossingDataList.size();i++){
                for(int k=0;k<poleList.size();k++){
                    if(!StringUtils.isNullOrEmpty(poleList.get(k).getCrossId()) && poleList.get(k).getCrossId().equals(crossingDataList.get(i).getCrossId())){
                        crossingDataList.get(i).getTrafficPoleList().add(poleList.get(k));
                    }
                }
            }
            return ResponseUtils.success(crossingDataList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "ibd路段数据查询", notes = "ibd路段数据查询")
    @RequestMapping(value = "/queryIbdRoadSectionList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryIbdRoadSectionList(@RequestBody NtIbdAssetSection params) {
        try{
            params.setPageSize(PageSize);
            List<NtIbdAssetSection> dataList = assetSectionService.selectList(params);
            return ResponseUtils.success(dataList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "ibd路口数据查询", notes = "ibd路口数据查询")
    @RequestMapping(value = "/queryIbdRoadCrossingDataById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryIbdRoadCrossingDataById(@RequestBody DpAssetParamDto params) {
        try{
            String polygon = "";
            if(DpIbdParamTypeConstant.DP_POITYPE_ROAD.equals(params.getPoiType())){
                List<NtIbdAssetRoad> list = assetRoadService.selectList(new NtIbdAssetRoad(PageSize,params.getId()));
                if(list.size() > 0){
                    polygon = list.get(0).getPolygon();
                }
            }else if(DpIbdParamTypeConstant.DP_POITYPE_SECTION.equals(params.getPoiType())){
                List<NtIbdAssetSection> list = assetSectionService.selectList(new NtIbdAssetSection(PageSize,params.getId()));
                if(list.size() > 0){
                    NtIbdAssetSection section = list.get(0);
                    if(!StringUtil.isEmpty(params.getDirectionType()) && !DpIbdParamTypeConstant.DP_SECION_DIRECTION1.equals(params.getDirectionType())){
                        List<NtIbdAssetSectionDirection> directions = section.getDirections();
                        for(NtIbdAssetSectionDirection dir:directions){
                            if(params.getDirectionType().equals(dir.getDirectionType())){
                                polygon = dir.getPolygon();
                                break;
                            }
                        }
                    }else{
                        polygon = section.getPolygon();
                    }
                }
            }else if(DpIbdParamTypeConstant.DP_POITYPE_CROSSING.equals(params.getPoiType())){
                List<NtIbdCrossingData> list = ibdCrossingDataService.selectList(new NtIbdCrossingData(PageSize,params.getId()));
                if(list.size() > 0){
                    polygon = list.get(0).getPolygon();
                }
            }
            if(StringUtil.isEmpty(polygon)){
                return ResponseUtils.success(new JSONObject());
            }
            JSONArray polygonArr = JSONArray.parseArray(polygon);
            String crossingOneAreaParam = polygonArr.getJSONArray(0).toJSONString();
            String reIbdContent = ibdFactorDataService.queryIbdRoadCrossingData(crossingOneAreaParam);
            if(StringUtil.isEmpty(reIbdContent)){
                return ResponseUtils.success(new JSONObject());
            }
            return ResponseUtils.success(JSONObject.parseObject(reIbdContent));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "ibd数据修改，删除，新增接口", notes = "ibd数据修改，删除，新增接口")
    @RequestMapping(value = "/ibdAddAndUpdateDataInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils ibdAddAndUpdateDataInfo(@RequestBody DpIbdParamDto params) {
        try{
            Map<String,Object> reIbdObj = ibdFactorDataService.ibdAddAndUpdateDataInfo(params);
            return ResponseUtils.success(reIbdObj);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "ibd数据修改，删除，新增接口", notes = "ibd数据修改，删除，新增接口")
    @RequestMapping(value = "/ibdAddAndUpdateLaneLinkInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils ibdAddAndUpdateLaneLinkInfo(@RequestBody DpIbdLaneLinkParamDto params) {
        try{
            Map<String,Object> reIbdObj = ibdFactorDataService.ibdAddAndUpdateLaneLinkInfoByAreaCoord(params);
            return ResponseUtils.success(reIbdObj);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }


    @ApiOperation(value = "ibd数据修改，删除，新增接口", notes = "ibd数据修改，删除，新增接口")
    @RequestMapping(value = "/ibdUpdateRoadLinkJuncRelInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils ibdUpdateRoadLinkJuncRelInfo(@RequestBody DpIbdRoadLinkJuncRelDto params) {
        try{
            Map<String,Object> reIbdObj = ibdFactorDataService.ibdUpdateRoadLinkJuncRelInfo(params);
            return ResponseUtils.success(reIbdObj);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "ibd箭头数据查询", notes = "ibd箭头数据查询")
    @RequestMapping(value = "/queryIbdArrowDataById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryIbdArrowDataById(@RequestBody DpAssetParamDto params) {
        try{
            String polygon = "";
            if(DpIbdParamTypeConstant.DP_POITYPE_ROAD.equals(params.getPoiType())){
                List<NtIbdAssetRoad> list = assetRoadService.selectList(new NtIbdAssetRoad(PageSize,params.getId()));
                if(list.size() > 0){
                    polygon = list.get(0).getPolygon();
                }
            }else if(DpIbdParamTypeConstant.DP_POITYPE_SECTION.equals(params.getPoiType())){
                List<NtIbdAssetSection> list = assetSectionService.selectList(new NtIbdAssetSection(PageSize,params.getId()));
                if(list.size() > 0){
                    polygon = list.get(0).getPolygon();
                }
            }else if(DpIbdParamTypeConstant.DP_POITYPE_CROSSING.equals(params.getPoiType())){
                List<NtIbdCrossingData> list = ibdCrossingDataService.selectList(new NtIbdCrossingData(PageSize,params.getId()));
                if(list.size() > 0){
                    polygon = list.get(0).getPolygon();
                }
            }
            if(StringUtil.isEmpty(params.getPoiType()) && StringUtil.isEmpty(params.getId())){
                polygon = "[]";
                Map<String,Object>  reIbdObj = ibdFactorDataService.queryIbdArrowDataByAreaCoord(polygon);
                return ResponseUtils.success(reIbdObj);
            }
            if(StringUtil.isEmpty(polygon)){
                return ResponseUtils.success(new JSONObject());
            }
            JSONArray polygonArr = JSONArray.parseArray(polygon);
            String crossingOneAreaParam = polygonArr.getJSONArray(0).toJSONString();
            Map<String,Object> reIbdContent = ibdFactorDataService.queryIbdArrowDataByAreaCoord(crossingOneAreaParam);
            return ResponseUtils.success(reIbdContent);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "ibd路线和路段树形数据查询", notes = "ibd路线和路段树形数据查询")
    @RequestMapping(value = "/queryIbdRoadSectionTree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryIbdRoadSectionTree(@RequestBody NtIbdAssetRoad params) {
        try{
            params.setPageSize(PageSize);
            List<NtIbdAssetRoad> roadList = assetRoadService.selectList(params);
            return ResponseUtils.success(roadList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "资产定位数据按类型查询", notes = "资产定位数据按类型查询")
    @RequestMapping(value = "/queryLocationAssetData", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryLocationAssetData(@RequestBody DpAssetParamDto params) {
        try{
            List<NtLocationAssetDict> resultAssetDictList = new ArrayList<>();
            String[] typeCodes = params.getTypeCodes();
            List<NtLocationAssetDict> locationAssetDictList =  locationAssetDictService.selectList(new NtLocationAssetDict(PageSize));
            for(NtLocationAssetDict aDict:locationAssetDictList){
                if(typeCodes !=null && typeCodes.length>0){
                    if(!Arrays.asList(typeCodes).contains(aDict.getTypeCode())){
                        continue;
                    }
                }
                if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[1].equalsIgnoreCase(aDict.getTypeCode())){
                    NtHikCameras camera = new NtHikCameras(PageSize);
                    camera.setCrossId(params.getId());
                    List<NtHikCameras> partList = hikCamerasService.selectList(camera);
                    partList.forEach(item -> { item.setTypeCode(aDict.getTypeCode()); });
                    aDict.setDatalist(partList);
                }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[2].equalsIgnoreCase(aDict.getTypeCode())){
                    NtIbdTrafficPole pole = new NtIbdTrafficPole(PageSize);
                    pole.setCrossId(params.getId());
                    pole.setDeviceTypeCode("trafficpole");
                    List<NtIbdTrafficPole> partList = trafficPoleService.selectList(pole);
                    List<NtIbdTrafficHld> hldList = new ArrayList<>();
                    for(NtIbdTrafficPole cPole:partList){
                        List<NtIbdTrafficHld> tempHldList = cPole.getHldList();
                        tempHldList.forEach(item -> {
                            item.setDeviceList(cPole.getDeviceList());
                            item.setTypeCode(aDict.getTypeCode());
                            item.setDeviceId(cPole.getDeviceList().size()>0?cPole.getDeviceList().get(0).getId():null);
                        });
                        hldList.addAll(tempHldList);
                    }
                    aDict.setDatalist(hldList);
                }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[3].equalsIgnoreCase(aDict.getTypeCode())){
                    aDict.setDatalist(trffLaserRadarService.selectList(new NtTrffassetLaserRadar(PageSize,"3",null,params.getId())));
                } else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[4].equalsIgnoreCase(aDict.getTypeCode())){
                    aDict.setDatalist(trffLaserRadarService.selectList(new NtTrffassetLaserRadar(PageSize,"4",params.getId(),null)));
                }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[5].equalsIgnoreCase(aDict.getTypeCode())){
                    aDict.setDatalist(trffLaserRadarService.selectList(new NtTrffassetLaserRadar(PageSize,"5",params.getId(),null)));
                }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[10].equalsIgnoreCase(aDict.getTypeCode())){
                    aDict.setDatalist(trffassetChangeInfoMarkService.selectList(new NtTrffassetChangeInfoMark(PageSize,"10",null,params.getId())));
                }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[11].equalsIgnoreCase(aDict.getTypeCode())){
                    aDict.setDatalist(trffassetChangeInfoMarkService.selectList(new NtTrffassetChangeInfoMark(PageSize,"11",null,params.getId())));
                }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[12].equalsIgnoreCase(aDict.getTypeCode())){
                    aDict.setDatalist(trffassetChangeInfoMarkService.selectList(new NtTrffassetChangeInfoMark(PageSize,"12",null,params.getId())));
                }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[13].equalsIgnoreCase(aDict.getTypeCode())){
                    aDict.setDatalist(trffassetChangeInfoMarkService.selectList(new NtTrffassetChangeInfoMark(PageSize,"13",null,params.getId())));
                }
                resultAssetDictList.add(aDict);
            }
            return ResponseUtils.success(resultAssetDictList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "资产定位数据按类型查询单个数据byId", notes = "资产定位数据按类型查询单个数据byId")
    @RequestMapping(value = "/queryLocationAssetDataById", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryLocationAssetDataById(@RequestBody DpAssetParamDto params) {
        try{
            String typeCode = params.getTypeCode();
            List<NtLocationAssetDict> locationAssetDictList =  locationAssetDictService.selectList(new NtLocationAssetDict(PageSize));
            if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[1].equalsIgnoreCase(typeCode)){
                NtHikCameras entity = hikCamerasService.selectByPrimaryKey(params.getId());
                BaseNtBean baseNtBean = changeBeanEntity(locationAssetDictList,typeCode,entity.getDeviceList(),entity.getCrossId());
                entity.setTypeName(baseNtBean.getTypeName());
                entity.setTypeCode(baseNtBean.getTypeCode());
                entity.setCrossName(baseNtBean.getCrossName());
                entity.setSectionName(baseNtBean.getSectionName());
                return ResponseUtils.success(entity);
            } else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[2].equalsIgnoreCase(typeCode)){
                NtIbdTrafficHld entity = trafficHldService.selectByPrimaryKey(params.getId());
                NtIbdTrafficPole trffPole = trafficPoleService.selectByPrimaryKey(entity.getPoleId());
                BaseNtBean baseNtBean = changeBeanEntity(locationAssetDictList,typeCode,entity.getDeviceList(),trffPole.getCrossId());
                entity.setTypeName(baseNtBean.getTypeName());
                entity.setTypeCode(baseNtBean.getTypeCode());
                entity.setCrossName(baseNtBean.getCrossName());
                entity.setSectionName(baseNtBean.getSectionName());
                entity.setDeviceId(trffPole.getDeviceId());
                entity.setDeviceList(trffPole.getDeviceList());
                return ResponseUtils.success(entity);
            }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[3].equalsIgnoreCase(typeCode)){
                NtTrffassetLaserRadar entity = trffLaserRadarService.selectByPrimaryKey(params.getId());
                BaseNtBean baseNtBean = changeBeanEntity(locationAssetDictList,typeCode,entity.getDeviceList(),entity.getCrossId());
                entity.setTypeName(baseNtBean.getTypeName());
                entity.setTypeCode(baseNtBean.getTypeCode());
                entity.setCrossName(baseNtBean.getCrossName());
                entity.setSectionName(baseNtBean.getSectionName());
                return ResponseUtils.success(entity);
            }else if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[11].equalsIgnoreCase(typeCode)){
                NtTrffassetChangeInfoMark entity = trffassetChangeInfoMarkService.selectByPrimaryKey(params.getId());
                BaseNtBean baseNtBean = changeBeanEntity(locationAssetDictList,typeCode,entity.getDeviceList(),entity.getCrossId());
                entity.setTypeName(baseNtBean.getTypeName());
                entity.setTypeCode(baseNtBean.getTypeCode());
                entity.setCrossName(baseNtBean.getCrossName());
                entity.setSectionName(baseNtBean.getSectionName());
                return ResponseUtils.success(entity);
            }
            return ResponseUtils.success(new JSONObject());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    /**
     *
     * @param locationAssetDictList
     * @param typeCode
     * @param deviceList
     * @param crossId
     * @return
     */
    private BaseNtBean changeBeanEntity(List<NtLocationAssetDict> locationAssetDictList,String typeCode,List<MapDevice> deviceList,String crossId){
        NtIbdAssetRoad roadObj = new NtIbdAssetRoad();
        BaseNtBean baseB = new BaseNtBean();
        NtIbdAssetSection sectionObj = new NtIbdAssetSection();
        NtIbdCrossingData crossingDataObj = new NtIbdCrossingData();
        locationAssetDictList.forEach(item -> {
            if(item.getTypeCode().equalsIgnoreCase(typeCode)){
                baseB.setTypeName(item.getName());
                baseB.setTypeCode(item.getTypeCode());
            }
        });
        /*if(deviceList.size()>0){
            baseB.setDeviceCode(deviceList.get(0).getDeviceCode());
        }*/
        if(!StringUtil.isEmpty(crossId)){
            crossingDataObj = ibdCrossingDataService.selectByPrimaryKey(crossId);
            baseB.setCrossName(crossingDataObj.getName());
        }
        baseB.setSectionName(sectionObj.getName());
        return baseB;
    }

    @ApiOperation(value = "资产类别字典数据列表", notes = "资产类别字典数据列表")
    @RequestMapping(value = "/queryLocationAssetDictlist", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryLocationAssetDictlist(@RequestBody DpAssetParamDto params) {
        try{
            List<NtLocationAssetDict> locationAssetDictList =  locationAssetDictService.selectList(new NtLocationAssetDict(PageSize));
            return ResponseUtils.success(locationAssetDictList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "app登录", notes = "app登录")
    @RequestMapping(value = "/ntApp/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryNtAppLogin(@RequestBody SysUser sysUser) {
        try{
            if(StringUtils.isNullOrEmpty(sysUser.getUsername()) || StringUtils.isNullOrEmpty(sysUser.getPassword())){
                ResponseUtils.error("error，请输入用户名或密码");
            }
            String username = sysUser.getUsername();
            String password = sysUser.getPassword();
            password = MD5Util.getMD5String(password);
            sysUser.setPassword(password);
            String newDate = DateTimeUtil.formatDateTime(new Date());//2019-12-01 12:12:12
            String timeToken = CommTokenUtil.createMaxToken();
            timeToken = timeToken.substring(0, 8) + newDate.substring(0, 4) + newDate.substring(5, 7) + newDate.substring(8, 10) + newDate.substring(11, 13) + newDate.substring(14, 16) + newDate.substring(17, 19) + timeToken.substring(9, 17) + timeToken.substring(20, 22);
            Map<String,Object> reMap = new HashMap<>();
            reMap.put("token",timeToken);
            List userList = sysUserService.findUserList(sysUser);
            if(userList.size()>0){
                SysUser pojo = new SysUser();
                pojo.setUsername(username);
                Map uMap = getUserInfo(pojo);
                reMap.put("user",uMap);
                return ResponseUtils.success("登录成功！",reMap);
            }else{
                return ResponseUtils.error("用户名或密码错误！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    public Map getUserInfo(SysUser pojo){
        pojo.setRoles(new Integer[0]);
        List list = sysUserService.findList(pojo);
        Map reMap = new HashMap();
        reMap.put("userId","");
        reMap.put("username","");
        reMap.put("nickname","");
        reMap.put("role","");
        reMap.put("mobile","");
        reMap.put("email","");
        if(list.size()>0){
            SysUser user = (SysUser)list.get(0);
            reMap.put("userId",user.getUserId());
            reMap.put("username",user.getUsername());
            reMap.put("nickname",user.getNickname());
            reMap.put("mobile",user.getMobile());
            reMap.put("email",user.getEmail());
            reMap.put("dept",user.getSysDept().getName());
            String roleS = "";
            List<SysRole> roleList = user.getRoleList();
            for(int i=0;i<roleList.size();i++){
                if(i>0){
                    roleS += ";";
                }
                roleS += roleList.get(i).getRoleName();
            }
            reMap.put("role",roleS);
        }else{
        }
        return reMap;
    }

    @ApiOperation(value = "设备模型模版", notes = "设备模型模版")
    @RequestMapping(value = "/ntApp/queryDeviceTemplateList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryDeviceTemplateList(@RequestBody MapDevice params) {
        try{
            List<MapDevice> deviceList = new ArrayList<>();
            params.setPageSize(PageSize);
            if(!StringUtils.isNullOrEmpty(params.getDeviceTypeCode())){
                deviceList = mapDeviceService.selectListByDeviceTypes(params);
            }
            return ResponseUtils.success(deviceList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "app设备采集数据保存接口", notes = "app设备采集数据保存接口")
    @RequestMapping(value = "/ntApp/addOrUpdateCollectList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils addOrUpdateAppCollectList(@RequestBody AppCollectDto params) {
        try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            NtIbdTrafficPole devicePole = params.getDevicePole();
            String poleId = UUIDUtil.create32UUID();
            devicePole.setPoleId(poleId);
            devicePole.setInstallationTime(timestamp);
            devicePole.setInfoSource(2L);
            devicePole.setAlt(0.7);
            devicePole.setPitch(90D);
            int addIndex1 = trafficPoleService.insertSelective(devicePole);

            int addIndex2 =0;
            List<NtHikCameras> shexiangtouList = params.getShexiangtou();
            if(shexiangtouList.size()>0){
                for(NtHikCameras camera:shexiangtouList){
                    String cameraId = UUIDUtil.create32UUID();
                    camera.setCameraIndexCode(cameraId);
                    camera.setPoleId(poleId);
                    camera.setInstallationTime(timestamp);
                    camera.setInfoSource(2L);
                    camera.setPitch(90D);
                    camera.setAltitude("6.1");
                    addIndex2 = hikCamerasService.insertSelective(camera);
                }
            }else{
                addIndex2 = 1;
            }

            int addIndex3 =0;
            String[] imgs = params.getImgs();
            if(imgs.length>0){
                for(String img:imgs){
                    ProjectFile projectFile = new ProjectFile();
                    projectFile.setId(img);
                    projectFile.setMasterId(poleId);
                    addIndex3 = projectFileService.update(projectFile);
                }
            }else{
                addIndex3 = 1;
            }

            if(addIndex1 >0 && addIndex2 >0 && addIndex3>0){
                return ResponseUtils.success("保存成功");
            }else{
                return ResponseUtils.error("保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error("保存失败");
        }
    }

    @ApiOperation(value = "资产定位数据按类型查询-来源于app采集", notes = "资产定位数据按类型查询-来源于app采集")
    @RequestMapping(value = "/ntApp/queryLocationAssetDataFuzzyApp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryLocationAssetDataFuzzyApp(@RequestBody DpAssetParamDto params) {
        try{
            List<NtLocationAssetDict> resultAssetDictList = new ArrayList<>();
            String[] typeCodes = params.getTypeCodes();
            List<NtLocationAssetDict> locationAssetDictList =  locationAssetDictService.selectList(new NtLocationAssetDict(PageSize));
            for(NtLocationAssetDict aDict:locationAssetDictList){
                if(typeCodes !=null && typeCodes.length>0){
                    if(!Arrays.asList(typeCodes).contains(aDict.getTypeCode())){
                        continue;
                    }
                }
               if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[0].equalsIgnoreCase(aDict.getTypeCode())){
                   NtIbdTrafficPole pole = new NtIbdTrafficPole(PageSize);
                   pole.setInfoSource(2L);
                   List<NtIbdTrafficPole> partList = trafficPoleService.selectList(pole);
                   for(NtIbdTrafficPole item:partList){
                       item.setTypeCode(aDict.getTypeCode());
                       List<MapDevice> deviceList = item.getDeviceList();
                       MapDevice tempD = new MapDevice();
                       BeanUtils.copyProperties(deviceList.get(0),tempD);
                       tempD.setLon(item.getLon());
                       tempD.setLat(item.getLat());
                       tempD.setAlt(item.getAlt());
                       tempD.setHeading(item.getHeading());
                       tempD.setPitch(item.getPitch());
                       tempD.setRoll(item.getRoll());
                       List<MapDevice> deviceListNew = new ArrayList<>();
                       deviceListNew.add(tempD);
                       item.setDeviceList(deviceListNew);
                   }
                   aDict.setDatalist(partList);
                }
               if(DpIbdParamTypeConstant.DP_LOCAASSET_DICTS[1].equalsIgnoreCase(aDict.getTypeCode())){
                   NtHikCameras camera = new NtHikCameras(PageSize);
                   camera.setInfoSource(2L);
                   List<NtHikCameras> partList = hikCamerasService.selectList(camera);
                   partList.forEach(item -> {
                       item.setTypeCode(aDict.getTypeCode());
                       List<MapDevice> deviceList = item.getDeviceList();
                       MapDevice tempD = new MapDevice();
                       BeanUtils.copyProperties(deviceList.get(0),tempD);
                       tempD.setLon(Double.parseDouble(item.getLongitude()));
                       tempD.setLat(Double.parseDouble(item.getLatitude()));
                       tempD.setAlt(Double.parseDouble(item.getAltitude()));
                       tempD.setHeading(item.getHeading());
                       tempD.setPitch(item.getPitch());
                       tempD.setRoll(item.getRoll());
                       List<MapDevice> deviceListNew = new ArrayList<>();
                       deviceListNew.add(tempD);
                       item.setDeviceList(deviceListNew);
                   });
                   aDict.setDatalist(partList);
               }
               resultAssetDictList.add(aDict);
            }
            return ResponseUtils.success(resultAssetDictList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "资产统计分析查询1", notes = "资产统计分析查询1")
    @RequestMapping(value = "/queryAssetStatAnalysis", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAssetStatAnalysis(@RequestBody DpAssetParamDto params) {
        try{
            Map resultMap = new HashMap();
            resultMap.put("statAssetCount",getAssetStatAnalysisCount());
            resultMap.put("statIbdCount",getIbdStatAnalysisCount());
            //resultMap.put("statTrafficCarFlow",new ArrayList<>());
            //resultMap.put("statRealRrowdRank",new ArrayList<>());
            return ResponseUtils.success(resultMap);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    private List<Map> getAssetStatAnalysisCount(){
        List<Map> reList = new ArrayList<>();
        List<NtLocationAssetDict> locationAssetDictList =  locationAssetDictService.selectList(new NtLocationAssetDict(PageSize));
        for(NtLocationAssetDict aDict:locationAssetDictList){
            int count = locationAssetDictService.selectAssetStatAnalysis(aDict);
            Map<String,Object> reMap = new HashMap<>();
            reMap.put("name",aDict.getName());
            reMap.put("count",count);
            reList.add(reMap);
        }
        return reList;
    }

    private List<Map> getIbdStatAnalysisCount(){
        List<Map> reList = new ArrayList<>();
        Map<String,Object> reIbdObj = ibdFactorDataService.getIbdGetObjCount();
        if(Boolean.parseBoolean(reIbdObj.get("status").toString())){
            JSONObject reIbdData = JSONObject.parseObject(reIbdObj.get("data").toString());
            String[] keys = new String[]{"ArrowCount","InnerJunRoadLink","OuterJunLaneLink","OuterJunRoadLink"};
            //String[] names = new String[]{"箭头数量","交叉口内参考线","路口外参考线","车道中心线（路口外）"};
            String[] names = new String[]{"方向规则","路口内方向规则","路口外方向规则","路权/车速规则"};
            for(int i=0;i<keys.length;i++){
                if(reIbdData.containsKey(keys[i])){
                    Map<String,Object> reMap = new HashMap<>();
                    reMap.put("name",names[i]);
                    reMap.put("count",reIbdData.getDoubleValue(keys[i]));
                    reList.add(reMap);
                }
            }
        }
        return reList;
    }

    @ApiOperation(value = "可变信息标志下载", notes = "可变信息标志下载")
    @RequestMapping(value = "/queryAssetCmsDownload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAssetCmsDownload(@RequestBody GdbitDataDto params) {
        try{
            Map<String,Object> jsonObj = new JSONObject();
            if(!StringUtils.isNullOrEmpty(params.getDevCode())){
                jsonObj = trffassetChangeInfoMarkService.getCmsDownload(params);
            }
            return ResponseUtils.success(jsonObj);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "信号灯的状态查询", notes = "信号灯的状态查询")
    @RequestMapping(value = "/queryAssetSignalDengInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils queryAssetSignalDengInfo(@RequestBody GdbitDataDto params) {
        try{
            Map<String,Object> jsonObj = new JSONObject();
            if(!StringUtils.isNullOrEmpty(params.getDevCode())){
                jsonObj = trffassetChangeInfoMarkService.getSignalDengReadInfo(params);
            }
            return ResponseUtils.success(jsonObj);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtils.error();
        }
    }

    @ApiOperation(value = "OTA文件下载", notes = "OTA文件下载")
    @RequestMapping(value = "/getOtaDownLoadFile", method = RequestMethod.GET)
    @ResponseBody
    public void getOtaDownLoadFile(@RequestParam String url,@RequestParam String filePath) {
        try{
            //下载文件
            FileDownLoadUtils.downLoadFile(url, filePath);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "OTA文件下载Bytes", notes = "OTA文件下载Bytes")
    @RequestMapping(value = "/getOtaDownLoadFileBytes", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getOtaDownLoadFileBytes(@RequestParam String url,@RequestParam long startByte) {
        byte[] byteArray= new byte[0];
        try{
            //下载文件
            byteArray= FileDownLoadUtils.downLoadFileBytes(url,startByte);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(byteArray, HttpStatus.CREATED);
    }

}
