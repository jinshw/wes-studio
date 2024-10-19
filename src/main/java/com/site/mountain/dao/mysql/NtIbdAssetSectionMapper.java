package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtIbdAssetSection;

import java.util.List;

public interface NtIbdAssetSectionMapper {
    int deleteByPrimaryKey(String sectionId);

    int insert(NtIbdAssetSection record);

    int insertSelective(NtIbdAssetSection record);

    NtIbdAssetSection selectByPrimaryKey(String sectionId);

    int updateByPrimaryKeySelective(NtIbdAssetSection record);

    int updateByPrimaryKey(NtIbdAssetSection record);

    List<NtIbdAssetSection> selectList(NtIbdAssetSection record);

    int updateIsDelByPrimaryKey(String sectionId);
}