package com.site.mountain.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtIbdAssetRoadMapper;
import com.site.mountain.dao.mysql.NtIbdAssetSectionMapper;
import com.site.mountain.dao.mysql.NtIbdFactorDataMapper;
import com.site.mountain.dto.DpIbdLaneLinkParamDto;
import com.site.mountain.dto.DpIbdParamDto;
import com.site.mountain.dto.DpIbdParamTypeConstant;
import com.site.mountain.dto.DpIbdRoadLinkJuncRelDto;
import com.site.mountain.entity.NtIbdAssetRoad;
import com.site.mountain.entity.NtIbdAssetSection;
import com.site.mountain.entity.NtIbdFactorData;
import com.site.mountain.service.NtIbdFactorDataService;
import com.site.mountain.sqlite.SqliteIbdResolveUtils;
import com.site.mountain.utils.IbdDllUtils;
import com.site.mountain.utils.MapToBeanUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NtIbdFactorDataServiceImpl implements NtIbdFactorDataService {

    @Autowired
    NtIbdFactorDataMapper ibdFactorDataMapper;

    @Autowired
    NtIbdAssetRoadMapper assetRoadMapper;

    @Autowired
    NtIbdAssetSectionMapper assetSectionMapper;

    @Value("${ibdServerIpAddress}")
    private String ibdServerIpAddress;

    @Value("${ibdServerPort}")
    private String ibdServerPort;

    @Override
    public List<NtIbdFactorData> selectList(NtIbdFactorData record) {
        return ibdFactorDataMapper.selectList(record);
    }

    @Override
    public PageInfo<NtIbdFactorData> selectListByPage(NtIbdFactorData record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtIbdFactorData> list = ibdFactorDataMapper.selectList(record);
        PageInfo<NtIbdFactorData> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdFactorData record) {
        return ibdFactorDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteList(List<NtIbdFactorData> list) {
        return 0;
    }

    @Override
    public NtIbdFactorData selectByPrimaryKey(String guid) {
        return ibdFactorDataMapper.selectByPrimaryKey(guid);
    }

    @Override
    public int insertSelective(NtIbdFactorData record) {
        return ibdFactorDataMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(String guid) {
        return ibdFactorDataMapper.deleteByPrimaryKey(guid);
    }

    @Override
    public int replaceSelectiveBatch(List<NtIbdFactorData> records) {
        return ibdFactorDataMapper.replaceSelectiveBatch(records);
    }

    @Override
    public void changeIbdBizData() {
        List<Map<String,Object>> ibdList = SqliteIbdResolveUtils.queryIbdLogList();
        for(int i=0;i<ibdList.size();i++){
            String tableName = ibdList.get(i).get("tableName").toString();
            String queryCondition = " where pid='"+ibdList.get(i).get("pid").toString()+"'";
            List<Map<String,Object>> ibdBizList = SqliteIbdResolveUtils.queryIbdBizList(tableName,"",queryCondition);
            if(ibdBizList.size()>0){
                Map<String,Object> temp = ibdBizList.get(0);
                NtIbdFactorData fFactorBase = new NtIbdFactorData();
                MapToBeanUtils.mapToBean(temp,fFactorBase);
                int num = ibdFactorDataMapper.updateByPrimaryKeySelective(fFactorBase);
            }
        }
    }

    @Override
    public String queryIbdRoadCrossingData(String crossingOneAreaParam) {
        IbdDllUtils dllUtils = new IbdDllUtils();
        String reIbdContent = dllUtils.getIbdRoadCrossingData(ibdServerIpAddress,ibdServerPort,crossingOneAreaParam);
        return reIbdContent;
    }

    @Override
    public Map<String,Object> ibdAddAndUpdateDataInfo(DpIbdParamDto params) {
        String objType = params.getObjType();
        String opterateType = params.getOpterateType();
        Map jsonDataMap = params.getJsonData();
        String jsonData = JSONObject.toJSONString(jsonDataMap);
        Map reIbdObj = IbdDllUtils.ibdAddAndUpdateDataInfo(ibdServerIpAddress,ibdServerPort,objType,opterateType,jsonData);
        return reIbdObj;
    }

    @Override
    public Map<String,Object> ibdAddAndUpdateLaneLinkInfoByAreaCoord(DpIbdLaneLinkParamDto params) {
        Map<String,Object> reIbdObj = new HashMap<>();
        String id = params.getId();
        Map jsonDataMap = params.getJsonData();
        String jsonData = JSONObject.toJSONString(jsonDataMap);
        String jsonAreaCorrd = "";
        if(params.getDataType().equals(DpIbdParamTypeConstant.DP_POITYPE_ROAD)){
            NtIbdAssetRoad entity = assetRoadMapper.selectByPrimaryKey(id);
            if(entity != null){
                JSONArray polygonArr = JSONArray.parseArray(entity.getPolygon());
                jsonAreaCorrd = polygonArr.getJSONArray(0).toJSONString();
            }
        }else if(params.getDataType().equals(DpIbdParamTypeConstant.DP_POITYPE_SECTION)){
            NtIbdAssetSection entity = assetSectionMapper.selectByPrimaryKey(id);
            if(entity != null){
                JSONArray polygonArr = JSONArray.parseArray(entity.getPolygon());
                jsonAreaCorrd = polygonArr.getJSONArray(0).toJSONString();
            }
        }
        if(params.getUpdateType().equals(DpIbdParamTypeConstant.DP_PARAM_SPEED)){
            reIbdObj = IbdDllUtils.ibdUpdateLaneLinkSpeedInfoByAreaCoord(ibdServerIpAddress,ibdServerPort,jsonAreaCorrd,jsonData);
        }else if(params.getUpdateType().equals(DpIbdParamTypeConstant.DP_PARAM_VEHICLE)){
            reIbdObj = IbdDllUtils.ibdUpdateLaneLinkVehicleInfoByAreaCoord(ibdServerIpAddress,ibdServerPort,jsonAreaCorrd,jsonData);
        }
        return reIbdObj;
    }

    @Override
    public Map<String,Object> ibdUpdateRoadLinkJuncRelInfo(DpIbdRoadLinkJuncRelDto params) {
        Map reIbdObj = IbdDllUtils.ibdUpdateRoadLinkJuncRelInfo(ibdServerIpAddress,ibdServerPort,params.getRelType(),params.getObjId());
        return reIbdObj;
    }

    @Override
    public Map<String,Object> queryIbdArrowDataByAreaCoord(String crossingOneAreaParam) {
        Map reIbdObj = IbdDllUtils.getIbdArrowDataByAreaCoord(ibdServerIpAddress,ibdServerPort,crossingOneAreaParam);
        return reIbdObj;
    }

    @Override
    public Map<String,Object> getIbdGetObjCount() {
        Map reIbdObj = IbdDllUtils.getIbdGetObjCount(ibdServerIpAddress,ibdServerPort);
        return reIbdObj;
    }
}
