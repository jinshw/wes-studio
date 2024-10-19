package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SysDistrict;

import java.util.List;

/**
 * @author XU
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/17 001710:05
 */
public interface SysDistrictDao {

    List<SysDistrict> queryCityList(SysDistrict sysDistrict);


}
