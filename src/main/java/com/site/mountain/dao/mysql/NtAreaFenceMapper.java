package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtAreaFence;

import java.util.List;

public interface NtAreaFenceMapper {
    int insert(NtAreaFence record);

    int insertSelective(NtAreaFence record);

    List<NtAreaFence> selectList(NtAreaFence record);
}