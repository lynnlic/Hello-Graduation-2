package com.cms.service.impl;

import com.cms.dao.PageDao;
import com.cms.dao.SiteDao;
import com.cms.entity.PageEntity;
import com.cms.entity.SiteEntity;
import com.cms.entity.UserEntity;
import com.cms.service.PageService;
import com.cms.utils.ResultType;
import com.cms.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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

    @Override
    public ResultType<PageEntity> getPagesByCondition(Map<String, Object> map) {
        //条件值
        String name = map.get("name")==null?null:map.get("name").toString();
        int sysId = map.get("sysId")==null?-1:Integer.parseInt(map.get("sysId").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage, number);
        List<PageEntity> page = pageDao.getPagesByCondition(name,sysId);
        PageInfo<PageEntity> pageInfo = new PageInfo<>(page);

        ResultType pageResult = new ResultType();
        pageResult.setCode(200);
        pageResult.setMsg("获取生成页信息");
        pageResult.setTotal(pageInfo.getTotal());

        List data = new ArrayList();
        for(int i = 0; i < page.size(); i++){
            PageEntity temp = page.get(i);
            Map mapTemp = new HashMap();
            mapTemp.put("pageId",temp.getPageId());
            mapTemp.put("pageName",temp.getPageName());
            mapTemp.put("fileName",temp.getFileName());
            mapTemp.put("pagePath",temp.getPagePath());
            mapTemp.put("creatorId",temp.getCreatorId());
            mapTemp.put("createTime",temp.getCreateTime());
            mapTemp.put("siteId",temp.getSiteEntity().getSiteId());
            mapTemp.put("siteName",temp.getSiteEntity().getSiteName());
            mapTemp.put("templateId",temp.getTemplateEntity().getTemplateId());
            mapTemp.put("templateName",temp.getTemplateEntity().getTemplateName());
            mapTemp.put("sysId",temp.getSystemEntity().getSysId());
            mapTemp.put("sysName",temp.getSystemEntity().getSysName());
            data.add(mapTemp);
        }
        pageResult.setData(data);

        return pageResult;
    }

    @Override
    public ResultType<PageEntity> deletePage(Map<String, Object> map) {
        int pageId = Integer.parseInt(map.get("pageId").toString());
        String pagePath = map.get("pagePath").toString();
        ResultType resultType;
        File file = new File(pagePath);
        if(file.exists()){
            if(file.delete()){
                int result = pageDao.deletePage(pageId);
                if(result==1){
                    resultType = ResultUtil.success(204,"删除成功",null);
                } else {
                    resultType = ResultUtil.error(205,"删除失败");
                }
            } else {
                resultType = ResultUtil.error(205,"删除失败");
            }
        } else {
            resultType = ResultUtil.error(205,"文件不存在");
        }
        return resultType;
    }
}
