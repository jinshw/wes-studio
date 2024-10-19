package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.NtActplanInfoMapper;
import com.site.mountain.entity.NtActplanInfo;
import com.site.mountain.service.NtActplanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtActplanInfoServiceImpl implements NtActplanInfoService {

    @Autowired
    NtActplanInfoMapper actplanInfoMapper;

    @Override
    public int deleteByPrimaryKey(String actplanId) {
        return actplanInfoMapper.deleteByPrimaryKey(actplanId);
    }

    @Override
    public int insert(NtActplanInfo record) {
        return actplanInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(NtActplanInfo record) {
        return actplanInfoMapper.insertSelective(record);
    }

    @Override
    public NtActplanInfo selectByPrimaryKey(String actplanId) {
        return actplanInfoMapper.selectByPrimaryKey(actplanId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtActplanInfo record) {
        return actplanInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtActplanInfo record) {
        return actplanInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtActplanInfo> selectList(NtActplanInfo record) {
        return actplanInfoMapper.selectList(record);
    }
}
