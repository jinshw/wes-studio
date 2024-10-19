package com.site.mountain.service;

import com.site.mountain.entity.AppAccident;

import java.util.List;
import java.util.Map;

public interface AppAccidentService {
    List<AppAccident> selectSGYYFX(AppAccident appAccident);

    List<AppAccident> selectSGXT(AppAccident appAccident);

    List<AppAccident> selectCLJSG(AppAccident appAccident);

    List<AppAccident> selectDCSG(AppAccident appAccident);

    List<AppAccident> selectSGCLPPPM(AppAccident appAccident);

    List<AppAccident> selectSFSGPM(AppAccident appAccident);

    List<AppAccident> selectXSZT(AppAccident appAccident);

    Integer selectTotal();

    Integer selectZSQS();

    Integer selectSWRS();


    Map<String,List<AppAccident>> selectSGFB(AppAccident appAccident);

    Map<String, List> select24H(String year, List<String> dateInterval);
}
