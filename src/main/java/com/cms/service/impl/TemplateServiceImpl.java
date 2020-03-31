package com.cms.service.impl;

import com.cms.dao.TemplateDao;
import com.cms.entity.TemplateEntity;
import com.cms.service.TemplateService;
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
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateDao templateDao;

    @Override
    public ResultType<TemplateEntity> getAllTemplate(int currentPage, int number) {
        PageHelper.startPage(currentPage, number);
        List<TemplateEntity> result = templateDao.getAllTemplate();
        PageInfo<TemplateEntity> pageInfo = new PageInfo<>(result);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setTotal(pageInfo.getTotal());

        if(pageInfo.getTotal()<1){
            resultType.setMsg("未查询到结果");
        } else {
            resultType.setMsg("查询到结果");
            List data = new ArrayList();
            for(int i = 0; i < result.size(); i++){
                TemplateEntity temp = result.get(i);
                Map map = new HashMap();
                map.put("templateId", temp.getTemplateId());
                map.put("templateName", temp.getTemplateName());
                map.put("sysId", temp.getSystemEntity().getSysId());
                map.put("sysName", temp.getSystemEntity().getSysName());
                map.put("templatePath", temp.getTemplatePath());
                map.put("describe", temp.getDescribe());
                map.put("templateTags", temp.getTemplateTags());
                map.put("creatorId", temp.getCreatorId());
                map.put("createTime", temp.getCreateTime());
                map.put("state", temp.getState());
                data.add(map);
            }
            resultType.setData(data);
        }

        return resultType;
    }
}
