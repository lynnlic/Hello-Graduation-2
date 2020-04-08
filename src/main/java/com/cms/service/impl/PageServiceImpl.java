package com.cms.service.impl;

import com.cms.dao.PageDao;
import com.cms.entity.PageEntity;
import com.cms.entity.UserEntity;
import com.cms.service.PageService;
import com.cms.utils.ResultType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    PageDao pageDao;

    @Override
    public ResultType<PageEntity> getPageInfo(int currentPage, int number) {
        PageHelper.startPage(currentPage, number);
        List<PageEntity> page = pageDao.getPageInfo();
        PageInfo<PageEntity> pageInfo = new PageInfo<>(page);

        ResultType pageResult = new ResultType();
        pageResult.setCode(200);
        pageResult.setMsg("获取生成页信息");
        pageResult.setTotal(pageInfo.getTotal());

        List data = new ArrayList();
        for(int i = 0; i < page.size(); i++){
            PageEntity temp = page.get(i);
            Map map = new HashMap();
            map.put("pageId",temp.getPageId());
            map.put("pageName",temp.getPageName());
            map.put("fileName",temp.getFileName());
            map.put("pagePath",temp.getPagePath());
            map.put("creatorId",temp.getCreatorId());
            map.put("createTime",temp.getCreateTime());
            map.put("siteId",temp.getSiteEntity().getSiteId());
            map.put("siteName",temp.getSiteEntity().getSiteName());
            map.put("templateId",temp.getTemplateEntity().getTemplateId());
            map.put("templateName",temp.getTemplateEntity().getTemplateName());
            map.put("sysId",temp.getSystemEntity().getSysId());
            map.put("sysName",temp.getSystemEntity().getSysName());
            data.add(map);
        }
        pageResult.setData(data);

        return pageResult;
    }

    @Override
    public ResultType<PageEntity> getPagesBySysid(int sysId) {
        List<PageEntity> page = pageDao.getPagesBySysid(sysId);
        ResultType pageResult = new ResultType();
        pageResult.setCode(200);
        pageResult.setMsg("获取生成页信息");

        List data = new ArrayList();
        List siteList = new ArrayList();
        for(int i = 0; i < page.size();i++){
            siteList.add(page.get(i).getSiteEntity().getSiteName());
        }
        TreeSet set = new TreeSet(siteList);
        siteList.clear();
        siteList.addAll(set);
        data.add(siteList);

        List pagesList = new ArrayList();
        for(int i=0; i < page.size();i++){
            PageEntity temp = page.get(i);
            if(temp.getPageId()==0) continue;
            Map map = new HashMap();
            map.put("pageId",temp.getPageId());
            map.put("pageName",temp.getPageName());
            map.put("siteName",temp.getSiteEntity().getSiteName());
            map.put("pagePath",temp.getPagePath());
            map.put("createTime",temp.getCreateTime());
            pagesList.add(map);
        }
        data.add(pagesList);

        pageResult.setData(data);
        return pageResult;
    }
}
