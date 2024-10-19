package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtIbdCrossingDataMapper;
import com.site.mountain.entity.NtIbdCrossingData;
import com.site.mountain.service.NtIbdCrossingDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtIbdCrossingDataServiceImpl implements NtIbdCrossingDataService {

    @Autowired
    NtIbdCrossingDataMapper ibdCrossingDataMapper;

    @Override
    public int deleteByPrimaryKey(String crossId) {
        return ibdCrossingDataMapper.deleteByPrimaryKey(crossId);
    }

    @Override
    public int insert(NtIbdCrossingData record) {
        return ibdCrossingDataMapper.insert(record);
    }

    @Override
    public int insertSelective(NtIbdCrossingData record) {
        return ibdCrossingDataMapper.insertSelective(record);
    }

    @Override
    public NtIbdCrossingData selectByPrimaryKey(String crossId) {
        return ibdCrossingDataMapper.selectByPrimaryKey(crossId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdCrossingData record) {
        return ibdCrossingDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtIbdCrossingData record) {
        return ibdCrossingDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtIbdCrossingData> selectList(NtIbdCrossingData record) {
        return ibdCrossingDataMapper.selectList(record);
    }

    @Override
    public PageInfo<NtIbdCrossingData> selectListByPage(NtIbdCrossingData record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtIbdCrossingData> list = ibdCrossingDataMapper.selectList(record);
        PageInfo<NtIbdCrossingData> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public int updateIsDelByPrimaryKey(String crossId) {
        return ibdCrossingDataMapper.updateIsDelByPrimaryKey(crossId);
    }
}
