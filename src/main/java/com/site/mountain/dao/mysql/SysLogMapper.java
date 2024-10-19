package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapVehicleAlarm;
import com.site.mountain.entity.SysLog;

import java.util.List;
import java.util.Map;

public interface SysLogMapper {
    int deleteByPrimaryKey(String logId);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectList(SysLog sysLog);

    List<Map<String,Object>> selectListMap(SysLog sysLog);

    SysLog selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}