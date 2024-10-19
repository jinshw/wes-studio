package com.site.mountain.job.impl;

import com.site.mountain.job.BaseJob;
import com.site.mountain.service.NtHikCamerasService;
import com.site.mountain.service.NtHikCrossingService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HikDataSyncJob implements BaseJob {

    @Autowired
    NtHikCamerasService hikCamerasService;

    @Autowired
    NtHikCrossingService hikCrossingService;

    private static Logger _log = LoggerFactory.getLogger(HikDataSyncJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.info("HikDataSyncJob开始执行：" + new Date());
        hikCamerasService.syncCameraData();
        hikCrossingService.syncCrossingData();
        System.out.println("===Camera数据同步===");
        System.out.println("===卡口数据同步===");
    }

}
