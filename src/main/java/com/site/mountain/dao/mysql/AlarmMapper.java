package com.site.mountain.dao.mysql;

import com.site.mountain.entity.Alarm;

import java.util.List;

public interface AlarmMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Long id);

    List<Alarm> selectAll(Alarm alarm);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKeyWithBLOBs(Alarm record);

    int updateByPrimaryKey(Alarm record);
}