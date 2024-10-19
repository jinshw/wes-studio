package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtIbdTrafficHldMapper;
import com.site.mountain.entity.NtIbdTrafficHld;
import com.site.mountain.service.NtIbdTrafficHldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtIbdTrafficHldServiceImpl implements NtIbdTrafficHldService {

    @Autowired
    NtIbdTrafficHldMapper trafficHldMapper;

    @Override
    public int deleteByPrimaryKey(String lightId) {
        return trafficHldMapper.deleteByPrimaryKey(lightId);
    }

    @Override
    public int insert(NtIbdTrafficHld record) {
        return trafficHldMapper.insert(record);
    }

    @Override
    public int insertSelective(NtIbdTrafficHld record) {
        return trafficHldMapper.insertSelective(record);
    }

    @Override
    public NtIbdTrafficHld selectByPrimaryKey(String lightId) {
        return trafficHldMapper.selectByPrimaryKey(lightId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdTrafficHld record) {
        return trafficHldMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtIbdTrafficHld record) {
        return trafficHldMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtIbdTrafficHld> selectList(NtIbdTrafficHld record) {
        return trafficHldMapper.selectList(record);
    }

    @Override
    public PageInfo<NtIbdTrafficHld> selectListByPage(NtIbdTrafficHld record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtIbdTrafficHld> list = trafficHldMapper.selectList(record);
        PageInfo<NtIbdTrafficHld> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public int updateIsDelByPrimaryKey(String lightId) {
        return trafficHldMapper.updateIsDelByPrimaryKey(lightId);
    }
}
