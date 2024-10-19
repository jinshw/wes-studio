package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtIbdAssetRoad;

import java.util.List;

public interface NtIbdAssetRoadMapper {
    int deleteByPrimaryKey(String roadId);

    int insert(NtIbdAssetRoad record);

    int insertSelective(NtIbdAssetRoad record);

    NtIbdAssetRoad selectByPrimaryKey(String roadId);

    int updateByPrimaryKeySelective(NtIbdAssetRoad record);

    int updateByPrimaryKey(NtIbdAssetRoad record);

    List<NtIbdAssetRoad> selectList(NtIbdAssetRoad record);

    int updateIsDelByPrimaryKey(String roadId);
}