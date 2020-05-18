package com.cms.dao;

import com.cms.entity.DataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataDao {
    List<DataEntity> getDataBySiteId(int siteId);
    List<DataEntity> getDataByCondition(String title, String siteName, int sysId, int parentId);
    int addContent(int id,String title, String path, String describe, int siteId, int creatorId);
    int updateTag(int did, String tagName);
    //得到页面内容和对应标签
    List<DataEntity> getDataByPageId(int pageId);
    int updateTextValue(int state, int contentId);
    int deleteContent(int contentId);
}
