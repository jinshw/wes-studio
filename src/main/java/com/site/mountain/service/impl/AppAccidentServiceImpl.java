package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.AppAccidentMapper;
import com.site.mountain.entity.AppAccident;
import com.site.mountain.service.AppAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppAccidentServiceImpl implements AppAccidentService {

    @Autowired
    private AppAccidentMapper appAccidentMapper;

    @Override
    public List<AppAccident> selectSGYYFX(AppAccident appAccident) {
        return appAccidentMapper.selectSGYYFX(appAccident);
    }

    public List<AppAccident> selectSGXT(AppAccident appAccident) {
        return appAccidentMapper.selectSGXT(appAccident);
    }

    public List<AppAccident> selectCLJSG(AppAccident appAccident) {
        return appAccidentMapper.selectCLJSG(appAccident);
    }

    public List<AppAccident> selectDCSG(AppAccident appAccident) {
        return appAccidentMapper.selectDCSG(appAccident);
    }

    public List<AppAccident> selectSGCLPPPM(AppAccident appAccident) {
        return appAccidentMapper.selectSGCLPPPM(appAccident);
    }

    public List<AppAccident> selectSFSGPM(AppAccident appAccident) {
        return appAccidentMapper.selectSFSGPM(appAccident);
    }

    public List<AppAccident> selectXSZT(AppAccident appAccident) {
        return appAccidentMapper.selectXSZT(appAccident);
    }
    public Integer selectTotal() {
        return appAccidentMapper.selectTotal();
    }
    public Integer selectZSQS() {
        return appAccidentMapper.selectZSQS();
    }
    public Integer selectSWRS() {
        return appAccidentMapper.selectSWRS();
    }

    public Map<String, List<AppAccident>> selectSGFB(AppAccident appAccident) {
        List<AppAccident> appAccidents = appAccidentMapper.selectSGFBGroup(appAccident);
        Map<String, List<AppAccident>> resultMap = new HashMap<>();
        AppAccident tempObj = null;
        List<AppAccident> sgfbList = null;
        for (int i = 0; i < appAccidents.size(); i++) {
            tempObj = appAccidents.get(i);
            tempObj.setStartDateStr(appAccident.getStartDateStr());
            tempObj.setEndDateStr(appAccident.getEndDateStr());
            sgfbList = appAccidentMapper.selectSGFBList(tempObj);
            resultMap.put(tempObj.getSgxt(), sgfbList);
        }
        return resultMap;
    }

    public Map<String, List> select24H(String year, List<String> dateInterval) {
        Map<String, List> resultMap = new HashMap<>();
        AppAccident appAccident = new AppAccident();
        AppAccident tempAppAccident = null;
        List<String> monthValList = null;
        for (int i = 0; i < dateInterval.size(); i++) {
            String tempInterval = dateInterval.get(i);
            String[] hourArr = tempInterval.split("-");
            String startHour = hourArr[0];
            String endHour = hourArr[1];
            monthValList = new ArrayList<>();
            // 计算十二个月数值
            for (int m = 1; m <= 12; m++) {
                String monthStr = String.format("%02d",m);
                String yearMonth = year + "-" + monthStr;
                appAccident.setStartDateStr(startHour);
                appAccident.setEndDateStr(endHour);
                appAccident.setYearMonth(yearMonth);
                tempAppAccident = appAccidentMapper.select24H(appAccident);
                monthValList.add(tempAppAccident.getValue());
            }
            resultMap.put(tempInterval,monthValList);
        }
        return resultMap;

    }
}
