package com.site.mountain.service;

import com.site.mountain.entity.CmsColumn;

import java.util.List;

public interface CmsColumnService {
    int delete(CmsColumn pojo);

    List<CmsColumn> findList(CmsColumn pojo);

    List<CmsColumn> findListByCode(CmsColumn pojo);

    int insert(CmsColumn pojo);

    int insertSelective(List<CmsColumn> pojo);

    int updateOne(CmsColumn pojo);
}
