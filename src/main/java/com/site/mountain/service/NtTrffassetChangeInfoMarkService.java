package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtTrffassetChangeInfoMark;
import com.site.mountain.request.GdbitDataDto;

import java.util.List;
import java.util.Map;

public interface NtTrffassetChangeInfoMarkService {
    int deleteByPrimaryKey(String id);

    int insert(NtTrffassetChangeInfoMark record);

    int insertSelective(NtTrffassetChangeInfoMark record);

    NtTrffassetChangeInfoMark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NtTrffassetChangeInfoMark record);

    int updateByPrimaryKey(NtTrffassetChangeInfoMark record);

    List<NtTrffassetChangeInfoMark> selectList(NtTrffassetChangeInfoMark record);

    PageInfo<NtTrffassetChangeInfoMark> selectListByPage(NtTrffassetChangeInfoMark record);

    int updateIsDelByPrimaryKey(String id);

    Map<String,Object> getCmsDownload(GdbitDataDto bitData);

    Map<String,Object> getSignalDengReadInfo(GdbitDataDto bitData);
}
