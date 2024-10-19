package com.site.mountain.job.impl;

import com.site.mountain.job.BaseJob;
import com.site.mountain.service.NtIbdFactorDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class IbdDataSyncLogJob implements BaseJob {

    @Autowired
    private NtIbdFactorDataService ibdFactorDataService;

    private static Logger _log = LoggerFactory.getLogger(IbdDataSyncLogJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.info("IbdDataSyncLogJob开始执行：" + new Date());
        ibdFactorDataService.changeIbdBizData();
        System.out.println("===ibd数据同步===");
    }

}
