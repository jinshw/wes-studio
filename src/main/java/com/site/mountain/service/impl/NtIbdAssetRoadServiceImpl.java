package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtIbdAssetRoadMapper;
import com.site.mountain.entity.NtIbdAssetRoad;
import com.site.mountain.entity.NtIbdTrafficHld;
import com.site.mountain.service.NtIbdAssetRoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtIbdAssetRoadServiceImpl implements NtIbdAssetRoadService {

    @Autowired
    NtIbdAssetRoadMapper ibdAssetRoadMapper;

    @Override
    public int deleteByPrimaryKey(String roadId) {
        return ibdAssetRoadMapper.deleteByPrimaryKey(roadId);
    }

    @Override
    public int insert(NtIbdAssetRoad record) {
        return ibdAssetRoadMapper.insert(record);
    }

    @Override
    public int insertSelective(NtIbdAssetRoad record) {
        return ibdAssetRoadMapper.insertSelective(record);
    }

    @Override
    public NtIbdAssetRoad selectByPrimaryKey(String roadId) {
        return ibdAssetRoadMapper.selectByPrimaryKey(roadId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdAssetRoad record) {
        return ibdAssetRoadMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtIbdAssetRoad record) {
        return ibdAssetRoadMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtIbdAssetRoad> selectList(NtIbdAssetRoad record) {
        return ibdAssetRoadMapper.selectList(record);
    }

    @Override
    public PageInfo<NtIbdAssetRoad> selectListByPage(NtIbdAssetRoad record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtIbdAssetRoad> list = ibdAssetRoadMapper.selectList(record);
        PageInfo<NtIbdAssetRoad> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public int updateIsDelByPrimaryKey(String roadId) {
        return ibdAssetRoadMapper.updateIsDelByPrimaryKey(roadId);
    }

}
