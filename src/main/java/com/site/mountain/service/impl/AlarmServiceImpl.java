package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.AlarmMapper;
import com.site.mountain.entity.Alarm;
import com.site.mountain.service.AlarmServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmServer {

    @Autowired
    private AlarmMapper alarmMapper;


    public int insert(Alarm alarm){
        return alarmMapper.insert(alarm);
    }

    public List<Alarm> selectAll(Alarm alarm){
        return alarmMapper.selectAll(alarm);
    }


}
