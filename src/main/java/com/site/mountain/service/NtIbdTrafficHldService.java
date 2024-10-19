package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdTrafficHld;

import java.util.List;

public interface NtIbdTrafficHldService {
    int deleteByPrimaryKey(String lightId);

    int insert(NtIbdTrafficHld record);

    int insertSelective(NtIbdTrafficHld record);

    NtIbdTrafficHld selectByPrimaryKey(String lightId);

    int updateByPrimaryKeySelective(NtIbdTrafficHld record);

    int updateByPrimaryKey(NtIbdTrafficHld record);

    List<NtIbdTrafficHld> selectList(NtIbdTrafficHld record);

    PageInfo<NtIbdTrafficHld> selectListByPage(NtIbdTrafficHld record);

    int updateIsDelByPrimaryKey(String lightId);
}
