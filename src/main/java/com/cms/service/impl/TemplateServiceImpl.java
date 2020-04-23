package com.cms.service.impl;

import com.cms.dao.TemplateDao;
import com.cms.entity.TemplateEntity;
import com.cms.service.TemplateService;
import com.cms.utils.ReadTxt;
import com.cms.utils.ResultType;
import com.cms.utils.StringCut;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    TemplateDao templateDao;

    @Override
    public ResultType<TemplateEntity> getTemplateBySysid(int sysId) {
        List<TemplateEntity> result = templateDao.getTemplateBySysid(sysId);
        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setTotal(new Long(result.size()));

        List data = new ArrayList();
        for(int i = 0; i < result.size(); i++){
            TemplateEntity temp = result.get(i);
            Map map = new HashMap();
            map.put("templateId", temp.getTemplateId());
            map.put("templateName", temp.getTemplateName());
            data.add(map);
        }
        resultType.setData(data);
        return resultType;
    }

    @Override
    public ResultType<TemplateEntity> getTemplateByCondition(Map<String, Object> map) {
        //条件值
        int sysId = map.get("sysId")==null?-1:Integer.parseInt(map.get("sysId").toString());
        String templateName = map.get("templateName")==null?null:map.get("templateName").toString();
        int state = map.get("state")==null?-1:Integer.parseInt(map.get("state").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage, number);
        List<TemplateEntity> result = templateDao.getTemplateByCondition(sysId, templateName, state);
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
                Map tempMap = new HashMap();
                tempMap.put("templateId", temp.getTemplateId());
                tempMap.put("templateName", temp.getTemplateName());
                tempMap.put("sysId", temp.getSystemEntity().getSysId());
                tempMap.put("sysName", temp.getSystemEntity().getSysName());
                tempMap.put("templatePath", temp.getTemplatePath());
                tempMap.put("describe", temp.getDescribe());
                tempMap.put("templateTags", temp.getTemplateTags());
                tempMap.put("creatorId", temp.getCreatorId());
                tempMap.put("createTime", temp.getCreateTime());
                tempMap.put("state", temp.getState());
                data.add(tempMap);
            }
            resultType.setData(data);
        }

        return resultType;
    }

    @Override
    public ResultType fileUpload(MultipartFile file) {
        String path = "D:/cms/template";
        //为了避免多次上传同一个文件导致命名重复，在文件名前加UUID前缀
        String prefix = UUID.randomUUID().toString();
        prefix = prefix.replace("-", "");
        String filename = prefix + "_" + file.getOriginalFilename();
        File filepath = new File(path);
        //判断路径是否存在，如果不存在就创建一个
        System.out.println(filepath.getParentFile());
        if(!filepath.getParentFile().exists()){
            filepath.getParentFile().mkdirs();
        }
        if (!filepath.exists()){
            filepath.mkdirs();
        }
        //将上传文件保存到一个目标文件当中
        try
        {
            file.transferTo(new File(path+"/"+filename));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        ReadTxt readTxt = new ReadTxt();
        String result = readTxt.readText(new File(path+"/"+filename));
        List<String> list = StringCut.getSubString(result,"\\#\\{(.*?)}");
        for(int i = 0; i < list.size(); i++){
            String temp = list.get(i);
            temp = temp.substring(2,temp.length()-1);
            list.remove(i);
            list.add(i,temp);
        }

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setData(list);
        resultType.setMsg("上传成功");
        return resultType;
    }
}
