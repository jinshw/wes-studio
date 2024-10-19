package com.site.mountain.service.impl;


import com.site.mountain.dao.mysql.NtLocationAssetDictMapper;
import com.site.mountain.entity.NtLocationAssetDict;
import com.site.mountain.service.NtLocationAssetDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtLocationAssetDictServiceImpl implements NtLocationAssetDictService {

    @Autowired
    NtLocationAssetDictMapper locationAssetDictMapper;

    @Override
    public int deleteByPrimaryKey(String locatDictId) {
        return locationAssetDictMapper.deleteByPrimaryKey(locatDictId);
    }

    @Override
    public int insert(NtLocationAssetDict record) {
        return locationAssetDictMapper.insert(record);
    }

    @Override
    public int insertSelective(NtLocationAssetDict record) {
        return locationAssetDictMapper.insertSelective(record);
    }

    @Override
    public NtLocationAssetDict selectByPrimaryKey(String locatDictId) {
        return locationAssetDictMapper.selectByPrimaryKey(locatDictId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtLocationAssetDict record) {
        return locationAssetDictMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtLocationAssetDict record) {
        return locationAssetDictMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtLocationAssetDict> selectList(NtLocationAssetDict record) {
        return locationAssetDictMapper.selectList(record);
    }

    @Override
    public int selectAssetStatAnalysis(NtLocationAssetDict record) {
        return locationAssetDictMapper.selectAssetStatAnalysis(record);
    }
}
