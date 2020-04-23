package com.cms.service.impl;

import com.cms.dao.SystemDao;
import com.cms.entity.SystemEntity;
import com.cms.service.SystemService;
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
public class SystemServiceImpl implements SystemService {
    @Autowired
    SystemDao systemDao;

    @Override
    public ResultType<SystemEntity> getSysDescribe(int currentPage, int number) {
        PageHelper.startPage(currentPage,number);
        List<SystemEntity> result = systemDao.getSysDescribe();
        PageInfo<SystemEntity> pageInfo = new PageInfo<>(result);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setTotal(pageInfo.getTotal());
        if(result.size()>0){
            resultType.setMsg("得到数据");
            List data = new ArrayList();
            for(int i = 0; i < result.size(); i++){
                SystemEntity temp = result.get(i);
                Map map = new HashMap();
                map.put("sysId", temp.getSysId());
                map.put("sysName", temp.getSysName());
                map.put("sysIconPath", temp.getSysIconPath());
                map.put("sysUrl", temp.getSysUrl());
                data.add(map);
            }
            resultType.setData(data);
        } else {
            resultType.setMsg("没有数据");
        }
        return resultType;
    }

    @Override
    public ResultType<SystemEntity> getSysDetailById(int sysId) {
        List<SystemEntity> result = systemDao.getSysDetailById(sysId);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setTotal(new Long(1));
        resultType.setData(result);
        resultType.setMsg("得到数据");
        return resultType;
    }

    @Override
    public ResultType<SystemEntity> getSysName() {
        List<SystemEntity> result = systemDao.getSysName();

        ResultType resultType = new ResultType();
        if(result.size()>0){
            resultType.setMsg("得到数据");
            List data = new ArrayList();
            for(int i = 0; i < result.size(); i++){
                SystemEntity temp = result.get(i);
                Map map = new HashMap();
                map.put("sysId", temp.getSysId());
                map.put("sysName", temp.getSysName());
                map.put("sysSaveName", temp.getSysSaveName());
                data.add(map);
            }
            resultType.setData(data);
        } else {
            resultType.setMsg("没有已生成的系统");
        }
        return resultType;
    }
}
