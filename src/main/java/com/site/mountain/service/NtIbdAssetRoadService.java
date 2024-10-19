package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdAssetRoad;

import java.util.List;

public interface NtIbdAssetRoadService {
    int deleteByPrimaryKey(String roadId);

    int insert(NtIbdAssetRoad record);

    int insertSelective(NtIbdAssetRoad record);

    NtIbdAssetRoad selectByPrimaryKey(String roadId);

    int updateByPrimaryKeySelective(NtIbdAssetRoad record);

    int updateByPrimaryKey(NtIbdAssetRoad record);

    List<NtIbdAssetRoad> selectList(NtIbdAssetRoad record);

    PageInfo<NtIbdAssetRoad> selectListByPage(NtIbdAssetRoad record);

    int updateIsDelByPrimaryKey(String roadId);
}
