package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtActplanAssetDict;

import java.util.List;

public interface NtActplanAssetDictMapper {
    int deleteByPrimaryKey(String assetDictId);

    int insert(NtActplanAssetDict record);

    int insertSelective(NtActplanAssetDict record);

    NtActplanAssetDict selectByPrimaryKey(String assetDictId);

    int updateByPrimaryKeySelective(NtActplanAssetDict record);

    int updateByPrimaryKey(NtActplanAssetDict record);

    List<NtActplanAssetDict> selectList(NtActplanAssetDict record);
}