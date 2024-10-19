package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.NtIbdAssetSectionMapper;
import com.site.mountain.entity.NtIbdAssetSection;
import com.site.mountain.service.NtIbdAssetSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtIbdAssetSectionServiceImpl implements NtIbdAssetSectionService {

    @Autowired
    NtIbdAssetSectionMapper ibdAssetSectionMapper;

    @Override
    public int deleteByPrimaryKey(String roadId) {
        return ibdAssetSectionMapper.deleteByPrimaryKey(roadId);
    }

    @Override
    public int insert(NtIbdAssetSection record) {
        return ibdAssetSectionMapper.insert(record);
    }

    @Override
    public int insertSelective(NtIbdAssetSection record) {
        return ibdAssetSectionMapper.insertSelective(record);
    }

    @Override
    public NtIbdAssetSection selectByPrimaryKey(String roadId) {
        return ibdAssetSectionMapper.selectByPrimaryKey(roadId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdAssetSection record) {
        return ibdAssetSectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtIbdAssetSection record) {
        return ibdAssetSectionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<NtIbdAssetSection> selectList(NtIbdAssetSection record) {
        return ibdAssetSectionMapper.selectList(record);
    }

    @Override
    public PageInfo<NtIbdAssetSection> selectListByPage(NtIbdAssetSection record) {
        PageHelper.startPage(record.getPageNum(), record.getPageSize());
        List<NtIbdAssetSection> list = ibdAssetSectionMapper.selectList(record);
        PageInfo<NtIbdAssetSection> pageInfo = new PageInfo(list, record.getPageSize());
        return pageInfo;
    }

    @Override
    public int updateIsDelByPrimaryKey(String roadId) {
        return ibdAssetSectionMapper.updateIsDelByPrimaryKey(roadId);
    }
    
}
