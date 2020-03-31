package com.cms.service.impl;

import com.cms.dao.SiteDao;
import com.cms.entity.SiteEntity;
import com.cms.service.SiteService;
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
public class SiteServiceImpl implements SiteService {
    @Autowired
    SiteDao siteDao;

    @Override
    public ResultType<SiteEntity> getAllSite(int currentPage, int number) {
        PageHelper.startPage(currentPage, number);
        List<SiteEntity> result = siteDao.getAllSite();
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
                Map map = new HashMap();
                map.put("siteId", temp.getSiteId());
                map.put("siteName", temp.getSiteName());
                map.put("siteDescribe", temp.getSiteDescribe());
                map.put("siteCreateTime", temp.getSiteCreateTime());
                map.put("sysId", temp.getSystemEntity().getSysId());
                map.put("sysName",temp.getSystemEntity().getSysName());
                data.add(map);
            }
            resultType.setData(data);
        }

        return resultType;
    }
}
