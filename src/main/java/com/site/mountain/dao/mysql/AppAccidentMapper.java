package com.site.mountain.dao.mysql;

import com.site.mountain.entity.AppAccident;

import java.util.List;

public interface AppAccidentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppAccident record);

    int insertSelective(AppAccident record);

    AppAccident selectByPrimaryKey(Integer id);

    List<AppAccident> selectSGYYFX(AppAccident appAccident);

    List<AppAccident> selectSGXT(AppAccident appAccident);

    List<AppAccident> selectCLJSG(AppAccident appAccident);

    List<AppAccident> selectDCSG(AppAccident appAccident);

    List<AppAccident> selectSGCLPPPM(AppAccident appAccident);

    List<AppAccident> selectSFSGPM(AppAccident appAccident);

    List<AppAccident> selectXSZT(AppAccident appAccident);

    List<AppAccident> selectSGFBGroup(AppAccident appAccident);

    List<AppAccident> selectSGFBList(AppAccident appAccident);

    AppAccident select24H(AppAccident appAccident);

    int selectTotal();

    int selectZSQS();

    int selectSWRS();

    int updateByPrimaryKeySelective(AppAccident record);

    int updateByPrimaryKeyWithBLOBs(AppAccident record);

    int updateByPrimaryKey(AppAccident record);
}