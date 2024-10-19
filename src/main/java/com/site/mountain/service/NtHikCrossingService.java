package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.dto.RespDpNiHikBaseDto;
import com.site.mountain.dto.RespDpNiHikCrossingDto;
import com.site.mountain.entity.NtHikCrossing;

import java.util.List;

public interface NtHikCrossingService {

    int updateByPrimaryKeySelective(NtHikCrossing record);

    NtHikCrossing selectByPrimaryKey(String indexCode);

    int insertSelective(NtHikCrossing record);

    int deleteByPrimaryKey(String indexCode);

    int deleteList(List<NtHikCrossing> list);

    List<NtHikCrossing> selectList(NtHikCrossing record);

    PageInfo<NtHikCrossing> selectListByPage(NtHikCrossing record);

    void syncCrossingData();

    List<NtHikCrossing> judgeCrossingInFence();

    List<RespDpNiHikBaseDto<RespDpNiHikCrossingDto>> dpCrossingBaseConvert(List<NtHikCrossing> camerasList);
    
}
