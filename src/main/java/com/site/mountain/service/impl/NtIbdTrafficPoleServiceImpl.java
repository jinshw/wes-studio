package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtIbdTrafficPoleMapper;
import com.site.mountain.entity.NtIbdTrafficPole;
import com.site.mountain.service.NtIbdTrafficPoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtIbdTrafficPoleServiceImpl implements NtIbdTrafficPoleService {

    @Autowired
    NtIbdTrafficPoleMapper trafficPoleMapper;

    @Override
    public int deleteByPrimaryKey(String roadId) {
        return trafficPoleMapper.deleteByPrimaryKey(roadId);
    }

    @Override
    public int insert(NtIbdTrafficPole record) {
        return trafficPoleMapper.insert(record);
    }

    @Override
    public int insertSelective(NtIbdTrafficPole record) {
        return trafficPoleMapper.insertSelective(record);
    }

    @Override
    public NtIbdTrafficPole selectByPrimaryKey(String roadId) {
        return trafficPoleMapper.selectByPrimaryKey(roadId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdTrafficPole record) {
        return trafficPoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtIbdTrafficPole record) {
        return trafficPoleMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtIbdTrafficPole> selectList(NtIbdTrafficPole record) {
        return trafficPoleMapper.selectList(record);
    }

    @Override
    public PageInfo<NtIbdTrafficPole> selectListByPage(NtIbdTrafficPole record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtIbdTrafficPole> list = trafficPoleMapper.selectList(record);
        PageInfo<NtIbdTrafficPole> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public int updateIsDelByPrimaryKey(String roadId) {
        return trafficPoleMapper.updateIsDelByPrimaryKey(roadId);
    }
    
}
