package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtIbdFactorData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NtIbdFactorDataMapper {
    int deleteByPrimaryKey(String guid);

    int insert(NtIbdFactorData record);

    int insertSelective(NtIbdFactorData record);

    NtIbdFactorData selectByPrimaryKey(String guid);

    int updateByPrimaryKeySelective(NtIbdFactorData record);

    int updateByPrimaryKey(NtIbdFactorData record);

    List<NtIbdFactorData> selectList(NtIbdFactorData record);

    int replaceSelectiveBatch(@Param("records") List<NtIbdFactorData> records);
}