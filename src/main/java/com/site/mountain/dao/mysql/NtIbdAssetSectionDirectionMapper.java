package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtIbdAssetSectionDirection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NtIbdAssetSectionDirectionMapper {
    int deleteByPrimaryKey(String directionId);

    int insert(NtIbdAssetSectionDirection record);

    int insertSelective(NtIbdAssetSectionDirection record);

    NtIbdAssetSectionDirection selectByPrimaryKey(String directionId);

    int updateByPrimaryKeySelective(NtIbdAssetSectionDirection record);

    int updateByPrimaryKey(NtIbdAssetSectionDirection record);

    int replaceSelectiveBatch(@Param("records") List<NtIbdAssetSectionDirection> records);
}