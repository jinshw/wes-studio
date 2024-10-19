package com.site.mountain.service;

import com.site.mountain.entity.NtIbdAssetSectionDirection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NtIbdAssetSectionDirectionService {
    int deleteByPrimaryKey(String directionId);

    int insert(NtIbdAssetSectionDirection record);

    int insertSelective(NtIbdAssetSectionDirection record);

    NtIbdAssetSectionDirection selectByPrimaryKey(String directionId);

    int updateByPrimaryKeySelective(NtIbdAssetSectionDirection record);

    int updateByPrimaryKey(NtIbdAssetSectionDirection record);

    int replaceSelectiveBatch(List<NtIbdAssetSectionDirection> records);
}
