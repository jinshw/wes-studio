package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysDict;

import java.util.List;

/**
 * @author XU
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/17 001710:05
 */
public interface SysDictDao {

    List<SysDict> selectDictByCode(SysDict sysDict);


}
