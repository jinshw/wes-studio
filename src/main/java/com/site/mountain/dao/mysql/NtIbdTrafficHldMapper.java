package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtIbdTrafficHld;

import java.util.List;

public interface NtIbdTrafficHldMapper {
    int deleteByPrimaryKey(String lightId);

    int insert(NtIbdTrafficHld record);

    int insertSelective(NtIbdTrafficHld record);

    NtIbdTrafficHld selectByPrimaryKey(String lightId);

    int updateByPrimaryKeySelective(NtIbdTrafficHld record);

    int updateByPrimaryKey(NtIbdTrafficHld record);

    List<NtIbdTrafficHld> selectList(NtIbdTrafficHld record);

    int updateIsDelByPrimaryKey(String lightId);
}