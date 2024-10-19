package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SysLog;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SysLogService {

    PageInfo<SysLog> selectListByPage(SysLog sysLog);
    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectList(SysLog sysLog);

    ResponseEntity export(SysLog sysLog);

}
