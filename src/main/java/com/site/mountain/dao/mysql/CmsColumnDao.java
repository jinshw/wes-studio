package com.site.mountain.dao.mysql;

import java.util.List;

import com.site.mountain.entity.CmsColumn;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CmsColumnDao {

    int delete(CmsColumn pojo);

    List<CmsColumn> findList(CmsColumn pojo);

    List<CmsColumn> findListByCode(CmsColumn pojo);

    int insert(CmsColumn pojo);

    int insertSelective(List<CmsColumn> pojo);

    int updateOne(CmsColumn pojo);

}