package com.cms.service.impl;

import com.cms.dao.DataDao;
import com.cms.entity.DataEntity;
import com.cms.service.DataService;
import com.cms.utils.ResultType;
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
    public ResultType<DataEntity> getAllData() {
        ResultType resultType = new ResultType();
        List<DataEntity> result = dataDao.getData();
        List data = new ArrayList();
        resultType.setCode(200);
        if(result.size()==0){
            resultType.setMsg("未搜索到");
        }else {
            resultType.setMsg("搜索到结果");
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
