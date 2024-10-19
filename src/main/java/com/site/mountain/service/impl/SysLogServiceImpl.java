package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SysLogMapper;
import com.site.mountain.entity.MapVehicleAlarm;
import com.site.mountain.entity.PoiNode;
import com.site.mountain.entity.SysLog;
import com.site.mountain.service.SysLogService;
import com.site.mountain.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysLogServiceImpl implements SysLogService {


    @Autowired
    SysLogMapper sysLogMapper;

    public PageInfo<SysLog> selectListByPage(SysLog sysLog) {
        PageHelper.startPage(sysLog.getPageNum(), sysLog.getPageSize());
        List<SysLog> list = sysLogMapper.selectList(sysLog);
        PageInfo<SysLog> pageInfo = new PageInfo(list, sysLog.getPageSize());
        return pageInfo;
    }

    @Override
    public int insert(SysLog record) {
        return sysLogMapper.insert(record);
    }

    @Override
    public int insertSelective(SysLog record) {
        return sysLogMapper.insertSelective(record);
    }

    @Override
    public List<SysLog> selectList(SysLog sysLog) {
        return sysLogMapper.selectList(sysLog);
    }

    public ResponseEntity export(SysLog sysLog){
        sysLog.setPageSize(100000000);
        sysLog.setPageNum(1);
        List<Map<String, Object>> list = sysLogMapper.selectListMap(sysLog);
        List<PoiNode> poiList = new ArrayList<>();
        poiList.add(new PoiNode("opt_user_id","用户ID"));
        poiList.add(new PoiNode("opt_user_name","账号"));
        poiList.add(new PoiNode("method_name","方法名"));
        poiList.add(new PoiNode("opt_content","日志"));
        poiList.add(new PoiNode("opt_time","时间"));
        return PoiUtil.export2Excel("日志",poiList,list);
    }
}
