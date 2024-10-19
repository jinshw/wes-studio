package com.site.mountain.service;

import com.site.mountain.entity.NtLocationAssetDict;

import java.util.List;

public interface NtLocationAssetDictService {

    int deleteByPrimaryKey(String locatDictId);

    int insert(NtLocationAssetDict record);

    int insertSelective(NtLocationAssetDict record);

    NtLocationAssetDict selectByPrimaryKey(String locatDictId);

    int updateByPrimaryKeySelective(NtLocationAssetDict record);

    int updateByPrimaryKey(NtLocationAssetDict record);

    List<NtLocationAssetDict> selectList(NtLocationAssetDict record);

    int selectAssetStatAnalysis(NtLocationAssetDict record);

}
