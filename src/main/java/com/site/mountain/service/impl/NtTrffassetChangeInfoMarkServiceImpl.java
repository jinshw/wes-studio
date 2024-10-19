package com.site.mountain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtTrffassetChangeInfoMarkMapper;
import com.site.mountain.entity.NtTrffassetChangeInfoMark;
import com.site.mountain.request.GdbitDataDto;
import com.site.mountain.request.GdbitResp;
import com.site.mountain.service.NtTrffassetChangeInfoMarkService;
import com.site.mountain.utils.GdbitButtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NtTrffassetChangeInfoMarkServiceImpl implements NtTrffassetChangeInfoMarkService {

    @Autowired
    NtTrffassetChangeInfoMarkMapper trffassetChangeInfoMarkMapper;

    @Autowired
    GdbitButtUtils gdbitButtUtils;

    @Override
    public int deleteByPrimaryKey(String id) {
        return trffassetChangeInfoMarkMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(NtTrffassetChangeInfoMark record) {
        return trffassetChangeInfoMarkMapper.insert(record);
    }

    @Override
    public int insertSelective(NtTrffassetChangeInfoMark record) {
        return trffassetChangeInfoMarkMapper.insertSelective(record);
    }

    @Override
    public NtTrffassetChangeInfoMark selectByPrimaryKey(String id) {
        return trffassetChangeInfoMarkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(NtTrffassetChangeInfoMark record) {
        return trffassetChangeInfoMarkMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtTrffassetChangeInfoMark record) {
        return trffassetChangeInfoMarkMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtTrffassetChangeInfoMark> selectList(NtTrffassetChangeInfoMark record) {
        return trffassetChangeInfoMarkMapper.selectList(record);
    }

    @Override
    public PageInfo<NtTrffassetChangeInfoMark> selectListByPage(NtTrffassetChangeInfoMark record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtTrffassetChangeInfoMark> list = trffassetChangeInfoMarkMapper.selectList(record);
        PageInfo<NtTrffassetChangeInfoMark> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public int updateIsDelByPrimaryKey(String id) {
        return trffassetChangeInfoMarkMapper.updateIsDelByPrimaryKey(id);
    }

    @Override
    public Map<String,Object> getCmsDownload(GdbitDataDto bitData) {
        Map reMap = new HashMap();
        GdbitResp<Map<String,Object>> bitRespBase = gdbitButtUtils.getCmsDownload(bitData);
        if(bitRespBase.isSuccess()){
            Map<String,Object> dataMap = bitRespBase.getData();
            JSONObject content = JSONObject.parseObject(dataMap.get("fileContent").toString());
            reMap.put("fileContent",content);
        }
        return reMap;
    }

    @Override
    public Map<String, Object> getSignalDengReadInfo(GdbitDataDto bitData) {
        Map reMap = new HashMap();
        GdbitResp<Map<String,Object>> bitRespBase = gdbitButtUtils.getThirdSignalInfoByChannel(bitData);
        if(bitRespBase.isSuccess()){
            Map<String,Object> dataMap = bitRespBase.getData();
            Map<String,Object> contentD = (Map<String, Object>) dataMap.get("Data");
            reMap.put("Data",contentD);
        }
        return reMap;
    }
}
