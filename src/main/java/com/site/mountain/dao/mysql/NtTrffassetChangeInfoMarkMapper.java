package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtTrffassetChangeInfoMark;

import java.util.List;

public interface NtTrffassetChangeInfoMarkMapper {
    int deleteByPrimaryKey(String id);

    int insert(NtTrffassetChangeInfoMark record);

    int insertSelective(NtTrffassetChangeInfoMark record);

    NtTrffassetChangeInfoMark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NtTrffassetChangeInfoMark record);

    int updateByPrimaryKey(NtTrffassetChangeInfoMark record);

    List<NtTrffassetChangeInfoMark> selectList(NtTrffassetChangeInfoMark record);

    int updateIsDelByPrimaryKey(String id);
}