package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtActplanAssetData;

import java.util.List;

public interface NtActplanAssetDataMapper {
    int deleteByPrimaryKey(String assetDataId);

    int insert(NtActplanAssetData record);

    int insertSelective(NtActplanAssetData record);

    NtActplanAssetData selectByPrimaryKey(String assetDataId);

    int updateByPrimaryKeySelective(NtActplanAssetData record);

    int updateByPrimaryKey(NtActplanAssetData record);

    int updateIsDelByPrimaryKey(String assetDataId);

    List<NtActplanAssetData> selectList(NtActplanAssetData record);

    int replace(NtActplanAssetData record);

    int replaceSelective(NtActplanAssetData record);
}