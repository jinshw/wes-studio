package com.site.mountain.service;

import com.site.mountain.entity.CmsContent;

import java.util.List;

public interface CmsContentService {
    int delete(CmsContent pojo);

    List<CmsContent> findList(CmsContent pojo);

    List<CmsContent> findListByPage(CmsContent pojo);

    int insert(CmsContent pojo);

    int insertSelective(List<CmsContent> pojo);

    int updateOne(CmsContent pojo);
}
