package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.dto.DpIbdLaneLinkParamDto;
import com.site.mountain.dto.DpIbdParamDto;
import com.site.mountain.dto.DpIbdRoadLinkJuncRelDto;
import com.site.mountain.entity.NtIbdFactorData;
import java.util.List;
import java.util.Map;

public interface NtIbdFactorDataService {

    int updateByPrimaryKeySelective(NtIbdFactorData record);

    NtIbdFactorData selectByPrimaryKey(String guid);

    int insertSelective(NtIbdFactorData record);

    int deleteByPrimaryKey(String guid);

    int deleteList(List<NtIbdFactorData> list);

    List<NtIbdFactorData> selectList(NtIbdFactorData record);

    PageInfo<NtIbdFactorData> selectListByPage(NtIbdFactorData record);

    int replaceSelectiveBatch(List<NtIbdFactorData> records);

    void changeIbdBizData();

    String queryIbdRoadCrossingData(String crossingOneAreaParam);

    Map<String,Object> ibdAddAndUpdateDataInfo(DpIbdParamDto params);

    Map<String,Object> ibdAddAndUpdateLaneLinkInfoByAreaCoord(DpIbdLaneLinkParamDto params);

    Map<String,Object> ibdUpdateRoadLinkJuncRelInfo(DpIbdRoadLinkJuncRelDto params);

    Map<String,Object> queryIbdArrowDataByAreaCoord(String crossingOneAreaParam);

    Map<String,Object> getIbdGetObjCount();
}
