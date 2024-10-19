package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtIbdTrafficPole;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface NtIbdTrafficPoleMapper {
    int deleteByPrimaryKey(String poleId);

    int insert(NtIbdTrafficPole record);

    int insertSelective(NtIbdTrafficPole record);

    NtIbdTrafficPole selectByPrimaryKey(String poleId);

    int updateByPrimaryKeySelective(NtIbdTrafficPole record);

    int updateByPrimaryKey(NtIbdTrafficPole record);

    List<NtIbdTrafficPole> selectList(NtIbdTrafficPole record);

    int updateIsDelByPrimaryKey(String sectionId);

    Integer selectDevicesByDeviceId(BigInteger id);

    List<Map<String,Object>> selectListByDeviceId(BigInteger deviceId);
}