package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.NtIbdAssetSectionDirectionMapper;
import com.site.mountain.entity.NtIbdAssetSectionDirection;
import com.site.mountain.service.NtIbdAssetSectionDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NtIbdAssetSectionDirectionServiceImpl implements NtIbdAssetSectionDirectionService {

    @Autowired
    NtIbdAssetSectionDirectionMapper assetSectionDirectionMapper;

    @Override
    public int deleteByPrimaryKey(String roadId) {
        return assetSectionDirectionMapper.deleteByPrimaryKey(roadId);
    }

    @Override
    public int insert(NtIbdAssetSectionDirection record) {
        return assetSectionDirectionMapper.insert(record);
    }

    @Override
    public int insertSelective(NtIbdAssetSectionDirection record) {
        return assetSectionDirectionMapper.insertSelective(record);
    }

    @Override
    public NtIbdAssetSectionDirection selectByPrimaryKey(String roadId) {
        return assetSectionDirectionMapper.selectByPrimaryKey(roadId);
    }

    @Override
    public int updateByPrimaryKeySelective(NtIbdAssetSectionDirection record) {
        return assetSectionDirectionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(NtIbdAssetSectionDirection record) {
        return assetSectionDirectionMapper.updateByPrimaryKey(record);
    }

    @Override
    public int replaceSelectiveBatch(List<NtIbdAssetSectionDirection> records) {
        return assetSectionDirectionMapper.replaceSelectiveBatch(records);
    }

}
