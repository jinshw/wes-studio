package com.site.mountain.dao.mysql;

import com.site.mountain.entity.CmsContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CmsContentDao {

    int delete(CmsContent pojo);

    List<CmsContent> findList(CmsContent pojo);

    List<CmsContent> findListByPage(CmsContent pojo);

    int insert(CmsContent pojo);

    int insertSelective(List<CmsContent> pojo);

    int updateOne(CmsContent pojo);

}