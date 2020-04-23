package com.cms.service.impl;

import com.cms.dao.IdDao;
import com.cms.dao.SiteDao;
import com.cms.entity.IDEntity;
import com.cms.entity.SiteEntity;
import com.cms.service.SiteService;
import com.cms.utils.ResultType;
import com.cms.utils.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    SiteDao siteDao;
    @Autowired
    IdDao idDao;

    @Override
    public ResultType<SiteEntity> addSite(Map<String, Object> map) {
        //获得表中的id号，使得序号自增
        List<IDEntity> id = idDao.getID("site");
        //新的id
        int newId = id.get(0).getId()+1;
        //设置新的id号
        idDao.updateID(newId,"site");

        String name = map.get("siteName").toString();
        String url = map.get("siteUrl").toString();
        String describe = map.get("siteDescribe").toString();
        int sysId = Integer.parseInt(map.get("sysId").toString());

        int result = siteDao.addSite(newId,name,url,sysId,describe);

        ResultType resultType;
        if(result==1){
            resultType = ResultUtil.success(201, "添加成功",null);
        } else {
            resultType = ResultUtil.error(202, "添加失败");
        }

        return resultType;
    }

    @Override
    public ResultType<SiteEntity> getSiteByCondition(Map<String, Object> map) {
        //条件值
        int sysId = map.get("sysId")==null?-1:Integer.parseInt(map.get("sysId").toString());
        String name = map.get("siteName")==null?null:map.get("siteName").toString();
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage, number);
        List<SiteEntity> result = siteDao.getSiteByCondition(sysId, name);
        PageInfo<SiteEntity> pageInfo = new PageInfo<>(result);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setTotal(pageInfo.getTotal());

        if(pageInfo.getTotal()<1){
            resultType.setMsg("未查询到结果");
        } else {
            resultType.setMsg("查询到结果");
            List data = new ArrayList();
            for(int i = 0; i < result.size(); i++){
                SiteEntity temp = result.get(i);
                Map tempMap = new HashMap();
                tempMap.put("siteId", temp.getSiteId());
                tempMap.put("siteName", temp.getSiteName());
                tempMap.put("siteUrl",temp.getSiteUrl());
                tempMap.put("siteDescribe", temp.getSiteDescribe());
                tempMap.put("siteCreateTime", temp.getSiteCreateTime());
                tempMap.put("sysId", temp.getSystemEntity().getSysId());
                tempMap.put("sysName",temp.getSystemEntity().getSysName());
                data.add(tempMap);
            }
            resultType.setData(data);
        }
        return resultType;
    }
}
