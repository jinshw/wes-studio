package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdTrafficPole;

import java.util.List;

public interface NtIbdTrafficPoleService {
    int deleteByPrimaryKey(String poleId);

    int insert(NtIbdTrafficPole record);

    int insertSelective(NtIbdTrafficPole record);

    NtIbdTrafficPole selectByPrimaryKey(String poleId);

    int updateByPrimaryKeySelective(NtIbdTrafficPole record);

    int updateByPrimaryKey(NtIbdTrafficPole record);

    List<NtIbdTrafficPole> selectList(NtIbdTrafficPole record);

    PageInfo<NtIbdTrafficPole> selectListByPage(NtIbdTrafficPole record);

    int updateIsDelByPrimaryKey(String sectionId);
}
