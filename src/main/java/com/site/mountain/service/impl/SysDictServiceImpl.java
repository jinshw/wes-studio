package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysDictDao;
import com.site.mountain.entity.SysDict;
import com.site.mountain.service.SysDictService;
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
public class SysDictServiceImpl implements SysDictService {

    @Autowired
    public SysDictDao sysDictDao;

    @Override
    public List<SysDict> findList(SysDict sysDict) {
        return sysDictDao.selectDictByCode(sysDict);
    }
}
