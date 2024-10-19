package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.dto.RespDpNiHikCameraDto;
import com.site.mountain.entity.NtHikCameras;
import com.site.mountain.dto.RespDpNiHikBaseDto;
import com.site.mountain.request.PreviewURLsRequest;

import java.util.List;
import java.util.Map;

public interface NtHikCamerasService {

    int updateByPrimaryKeySelective(NtHikCameras record);

    NtHikCameras selectByPrimaryKey(String guid);

    int insertSelective(NtHikCameras record);

    int deleteByPrimaryKey(String guid);

    int deleteList(List<NtHikCameras> list);

    List<NtHikCameras> selectList(NtHikCameras record);

    PageInfo<NtHikCameras> selectListByPage(NtHikCameras record);

    String previewURLs(PreviewURLsRequest previewURLsRequest) throws Exception;

    void syncCameraData() ;

    List<NtHikCameras> judgeCameraInFence(Map<String,Object> params);

    List<RespDpNiHikBaseDto<RespDpNiHikCameraDto>> dpCameraBaseConvert(List<NtHikCameras> camerasList);

    int addAndUpdateCamera(RespDpNiHikBaseDto<RespDpNiHikCameraDto> record);

    int updateIsDelByPrimaryKey(String isAdd);

}
