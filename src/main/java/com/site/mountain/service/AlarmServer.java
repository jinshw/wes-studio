package com.site.mountain.service;

import com.site.mountain.entity.Alarm;

import java.util.List;

public interface AlarmServer {

    int insert(Alarm alarm);

    List<Alarm> selectAll(Alarm alarm);

}
