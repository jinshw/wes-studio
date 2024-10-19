package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.CmsContentDao;
import com.site.mountain.entity.CmsContent;
import com.site.mountain.service.CmsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CmsContentServiceImpl implements CmsContentService {

    @Autowired
    private CmsContentDao cmsContentDao;

    @Override
    public int delete(CmsContent pojo) {
        return cmsContentDao.delete(pojo);
    }

    @Override
    public List<CmsContent> findList(CmsContent pojo) {
        return cmsContentDao.findList(pojo);
    }

    @Override
    public List<CmsContent> findListByPage(CmsContent pojo) {
        return cmsContentDao.findListByPage(pojo);
    }

    @Override
    public int insert(CmsContent pojo) {
        return cmsContentDao.insert(pojo);
    }

    @Override
    public int insertSelective(List<CmsContent> pojo) {
        return 0;
    }

    @Override
    public int updateOne(CmsContent pojo) {
        return cmsContentDao.updateOne(pojo);
    }
}
