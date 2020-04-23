package com.cms.service.impl;

import com.cms.dao.PageDao;
import com.cms.dao.SiteDao;
import com.cms.entity.PageEntity;
import com.cms.entity.SiteEntity;
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

    @Autowired
    SiteDao siteDao;

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

    /***
     * 根据系统id获得站点和页面信息
     * @param sysId
     * @return
     */
    @Override
    public ResultType<PageEntity> getPagesBySysid(int sysId) {
        ResultType pageResult = new ResultType();
        pageResult.setCode(200);
        pageResult.setMsg("获取生成页信息");
        List data = new ArrayList();

        //获取站点信息
        List<SiteEntity> sites = siteDao.getSitesBySysid(sysId);
        List sitesList = new ArrayList();
        for(int i = 0; i < sites.size(); i++){
            Map siteMap = new HashMap();
            siteMap.put("siteId",sites.get(i).getSiteId());
            siteMap.put("siteName",sites.get(i).getSiteName());
            siteMap.put("siteUrl",sites.get(i).getSiteUrl());
            sitesList.add(siteMap);
        }
        data.add(sitesList);

        //获取页面信息
        List<PageEntity> page = pageDao.getPagesBySysid(sysId);
        List pagesList = new ArrayList();
        for(int i=0; i < page.size();i++){
            PageEntity temp = page.get(i);
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
