package com.site.mountain.service;

import com.site.mountain.dto.RespDpNiHikBaseDto;
import com.site.mountain.entity.NtActplanAssetData;

import java.util.List;

public interface NtActplanAssetDataService {

    int deleteByPrimaryKey(String assetDataId);

    int insert(NtActplanAssetData record);

    int insertSelective(NtActplanAssetData record);

    NtActplanAssetData selectByPrimaryKey(String assetDataId);

    int updateByPrimaryKeySelective(NtActplanAssetData record);

    int updateByPrimaryKey(NtActplanAssetData record);

    int updateIsDelByPrimaryKey(String assetDataId);

    int addAndUpdateActplanAssetData(RespDpNiHikBaseDto<NtActplanAssetData> params);

    List<NtActplanAssetData> selectList(NtActplanAssetData record);

}
