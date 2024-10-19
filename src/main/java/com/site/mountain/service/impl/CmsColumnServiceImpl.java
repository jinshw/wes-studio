package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.CmsColumnDao;
import com.site.mountain.entity.CmsColumn;
import com.site.mountain.service.CmsColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CmsColumnServiceImpl implements CmsColumnService {

    @Autowired
    private CmsColumnDao cmsColumnDao;

    @Override
    public int delete(CmsColumn pojo) {
        return cmsColumnDao.delete(pojo);
    }

    @Override
    public List<CmsColumn> findList(CmsColumn pojo) {
        return cmsColumnDao.findList(pojo);
    }

    @Override
    public List<CmsColumn> findListByCode(CmsColumn pojo) {
        return cmsColumnDao.findListByCode(pojo);
    }

    @Override
    public int insert(CmsColumn pojo) {
        return cmsColumnDao.insert(pojo);
    }

    @Override
    public int insertSelective(List<CmsColumn> pojo) {
        return 0;
    }

    @Override
    public int updateOne(CmsColumn pojo) {
        return cmsColumnDao.updateOne(pojo);
    }
}
