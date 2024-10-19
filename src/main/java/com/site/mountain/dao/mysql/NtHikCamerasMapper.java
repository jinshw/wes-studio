package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtHikCameras;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NtHikCamerasMapper {
    int deleteByPrimaryKey(String cameraIndexCode);

    int insert(NtHikCameras record);

    int insertSelective(NtHikCameras record);

    NtHikCameras selectByPrimaryKey(String cameraIndexCode);

    int updateByPrimaryKeySelective(NtHikCameras record);

    int updateByPrimaryKey(NtHikCameras record);

    List<NtHikCameras> selectList(NtHikCameras record);

    int replaceSelectiveBatch(@Param("records") List<NtHikCameras> records);

    int updateIsDelByPrimaryKey(String uuid);

    int replace(NtHikCameras record);

    int replaceSelective(NtHikCameras record);

}