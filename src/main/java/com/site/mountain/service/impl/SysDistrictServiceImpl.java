package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysDistrictDao;
import com.site.mountain.entity.SysDistrict;
import com.site.mountain.service.SysDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XU
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/17 001710:04
 */
@Service
public class SysDistrictServiceImpl implements SysDistrictService {

    @Autowired
    public SysDistrictDao sysDistrictDao;

    @Override
    public List<SysDistrict> queryCityList(SysDistrict sysDistrict) {
        return sysDistrictDao.queryCityList(sysDistrict);
    }
}
