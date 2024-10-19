package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.NtTrffassetLaserRadarMapper;
import com.site.mountain.entity.NtTrffassetLaserRadar;
import com.site.mountain.service.NtTrffassetLaserRadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtTrffassetLaserRadarServiceImpl implements NtTrffassetLaserRadarService {

    @Autowired
    NtTrffassetLaserRadarMapper trffassetLaserRadarMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return trffassetLaserRadarMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(NtTrffassetLaserRadar record) {
        return trffassetLaserRadarMapper.insert(record);
    }

    @Override
    public int insertSelective(NtTrffassetLaserRadar record) {
        return trffassetLaserRadarMapper.insertSelective(record);
    }

    @Override
    public NtTrffassetLaserRadar selectByPrimaryKey(String id) {
        return trffassetLaserRadarMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(NtTrffassetLaserRadar record) {
        return trffassetLaserRadarMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtTrffassetLaserRadar record) {
        return trffassetLaserRadarMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtTrffassetLaserRadar> selectList(NtTrffassetLaserRadar record) {
        return trffassetLaserRadarMapper.selectList(record);
    }
}
