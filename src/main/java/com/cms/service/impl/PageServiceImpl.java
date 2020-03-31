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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
