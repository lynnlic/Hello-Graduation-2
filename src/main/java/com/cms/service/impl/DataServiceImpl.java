package com.cms.service.impl;

import com.cms.dao.DataDao;
import com.cms.entity.DataEntity;
import com.cms.service.DataService;
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
public class DataServiceImpl implements DataService {
    @Autowired
    DataDao dataDao;

    @Override
    public ResultType<DataEntity> getAllData(int currentPage, int number) {
        PageHelper.startPage(currentPage,number);
        List<DataEntity> result = dataDao.getData();
        PageInfo<DataEntity> pageInfo = new PageInfo<>(result);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setTotal(pageInfo.getTotal());
        if(result.size()==0){
            resultType.setMsg("未搜索到");
        }else {
            resultType.setMsg("搜索到结果");
            List data = new ArrayList();
            for(int i=0; i<result.size();i++){
                DataEntity tempData = result.get(i);
                Map tempMap = new HashMap();
                tempMap.put("id",tempData.getDataId());
                tempMap.put("title",tempData.getTitle());
                tempMap.put("path",tempData.getPath());
                tempMap.put("tag",tempData.getTag());
                tempMap.put("siteName",tempData.getSiteEntity().getSiteName());
                tempMap.put("sysName",tempData.getSiteEntity().getSystemEntity().getSysName());
                tempMap.put("dataCreateTime",tempData.getCreateTime());
                data.add(tempMap);
            }
            resultType.setData(data);
        }
        return resultType;
    }
}
