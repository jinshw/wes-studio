package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.NtActplanAssetDictMapper;
import com.site.mountain.entity.NtActplanAssetDict;
import com.site.mountain.service.NtActplanAssetDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NtActplanAssetDictServiceImpl implements NtActplanAssetDictService {

    @Autowired
    NtActplanAssetDictMapper actplanAssetDictMapper;

    @Override
    public int deleteByPrimaryKey(String assetDictId) {
        return actplanAssetDictMapper.deleteByPrimaryKey(assetDictId);
    }

    @Override
    public int insert(NtActplanAssetDict record) {
        return actplanAssetDictMapper.insert(record);
    }

    @Override
    public int insertSelective(NtActplanAssetDict record) {
        return actplanAssetDictMapper.insertSelective(record);
    }

    @Override
    public NtActplanAssetDict selectByPrimaryKey(String assetDictId) {
        return actplanAssetDictMapper.selectByPrimaryKey(assetDictId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtActplanAssetDict record) {
        return actplanAssetDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtActplanAssetDict record) {
        return actplanAssetDictMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtActplanAssetDict> selectList(NtActplanAssetDict record) {
        return actplanAssetDictMapper.selectList(record);
    }

    @Override
    public List<String> selectListByEntityType(NtActplanAssetDict record) {
        List<NtActplanAssetDict> list = actplanAssetDictMapper.selectList(record);
        List<String> eTypeList = new ArrayList<>();
        for(NtActplanAssetDict dict:list){
            eTypeList.add(dict.getType());
        }
        return eTypeList;
    }
}
