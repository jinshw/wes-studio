package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdAssetRoad;
import com.site.mountain.entity.NtIbdAssetSection;

import java.util.List;

public interface NtIbdAssetSectionService {
    int deleteByPrimaryKey(String sectionId);

    int insert(NtIbdAssetSection record);

    int insertSelective(NtIbdAssetSection record);

    NtIbdAssetSection selectByPrimaryKey(String sectionId);

    int updateByPrimaryKeySelective(NtIbdAssetSection record);

    int updateByPrimaryKey(NtIbdAssetSection record);

    List<NtIbdAssetSection> selectList(NtIbdAssetSection record);

    PageInfo<NtIbdAssetSection> selectListByPage(NtIbdAssetSection record);

    int updateIsDelByPrimaryKey(String sectionId);
}
