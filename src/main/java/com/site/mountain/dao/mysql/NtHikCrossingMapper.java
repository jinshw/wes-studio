package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtHikCrossing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NtHikCrossingMapper {
    int deleteByPrimaryKey(String indexCode);

    int insert(NtHikCrossing record);

    int insertSelective(NtHikCrossing record);

    NtHikCrossing selectByPrimaryKey(String indexCode);

    int updateByPrimaryKeySelective(NtHikCrossing record);

    int updateByPrimaryKey(NtHikCrossing record);

    List<NtHikCrossing> selectList(NtHikCrossing record);

    int replaceSelectiveBatch(@Param("records") List<NtHikCrossing> records);
}