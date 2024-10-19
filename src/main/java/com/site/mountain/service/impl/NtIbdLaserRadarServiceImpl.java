package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.NtIbdLaserRadarMapper;
import com.site.mountain.entity.NtIbdLaserRadar;
import com.site.mountain.entity.NtIbdLaserRadar;
import com.site.mountain.service.NtIbdLaserRadarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtIbdLaserRadarServiceImpl implements NtIbdLaserRadarService {

    @Autowired
    NtIbdLaserRadarMapper laserRadarMapper;
    @Override
    public int deleteByPrimaryKey(String laserRadarId) {
        return laserRadarMapper.deleteByPrimaryKey(laserRadarId);
    }

    @Override
    public int insert(NtIbdLaserRadar record) {
        return laserRadarMapper.insert(record);
    }

    @Override
    public int insertSelective(NtIbdLaserRadar record) {
        return laserRadarMapper.insertSelective(record);
    }

    @Override
    public NtIbdLaserRadar selectByPrimaryKey(String laserRadarId) {
        return laserRadarMapper.selectByPrimaryKey(laserRadarId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdLaserRadar record) {
        return laserRadarMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtIbdLaserRadar record) {
        return laserRadarMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtIbdLaserRadar> selectList(NtIbdLaserRadar record) {
        return laserRadarMapper.selectList(record);
    }
}
