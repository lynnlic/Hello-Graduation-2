package com.cms.service.impl;

import com.cms.dao.IdDao;
import com.cms.dao.TemplateDao;
import com.cms.entity.IDEntity;
import com.cms.entity.TemplateEntity;
import com.cms.service.TemplateService;
import com.cms.utils.*;
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
    @Autowired
    IdDao idDao;

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
        int parentId = map.get("parentId")==null?-1:Integer.parseInt(map.get("parentId").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage, number);
        List<TemplateEntity> result = templateDao.getTemplateByCondition(sysId, templateName, state, parentId);
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
        Map<String, Object> mapResult = new HashMap<>();

        //为了避免多次上传同一个文件导致命名重复，在文件名前加UUID前缀
        String prefix = UUID.randomUUID().toString();
        prefix = prefix.replace("-", "");
        String filename = prefix + "_" + file.getOriginalFilename();
        File filepath = new File(Route.TEMPLATEPATH);

        //判断路径是否存在，如果不存在就创建一个
        if(!filepath.getParentFile().exists()){
            filepath.getParentFile().mkdirs();
        }
        if (!filepath.exists()){
            filepath.mkdirs();
        }
        //将上传文件保存到一个目标文件当中
        try
        {
            file.transferTo(new File(Route.TEMPLATEPATH+"/"+filename));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        //记录模板存放的路径传回给前台
        mapResult.put("filePath", Route.TEMPLATEPATH +"/"+filename);

        //读取模板文件中的内容
        String result = ReadFile.readText(new File(Route.TEMPLATEPATH +"/"+filename));
        //文件中标签列表
        List<String> taglist = StringCut.getSubString(result,"\\#\\{(.*?)}");
        for(int i = 0; i < taglist.size(); i++){
            String temp = taglist.get(i);
            temp = temp.substring(2,temp.length()-1);
            taglist.remove(i);
            taglist.add(i,temp);
        }
        mapResult.put("tagList", taglist);

        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setData(mapResult);
        resultType.setMsg("上传成功");
        return resultType;
    }

    @Override
    public ResultType loadLocalTemplate(Map<String, Object> map) {
        String fileName = map.get("fileName")==null?null:map.get("fileName").toString();
        return LoadFile.Load(fileName, "txt");
    }

    @Override
    public ResultType addTemplate(Map<String, Object> map) {
        //条件值
        String tempalteName = map.get("templateName")==null?null:map.get("templateName").toString();
        int sysId = map.get("sysId")==null?-1:Integer.parseInt(map.get("sysId").toString());
        String describe = map.get("describe")==null?null:map.get("describe").toString();
        int state = map.get("state")==null?-1:Integer.parseInt(map.get("state").toString());
        String filePath = map.get("filePath")==null?null:map.get("filePath").toString();
        String tags = map.get("tagList")==null?null:map.get("tagList").toString();
        int creatorId = map.get("creatorId")==null?-1:Integer.parseInt(map.get("creatorId").toString());

        //获得表中的id号，使得序号自增
        List<IDEntity> id = idDao.getID("template");
        //新的id
        int newId = id.get(0).getId()+1;

        int result = templateDao.addTemplate(newId,tempalteName,describe,filePath,tags,sysId,state,creatorId);

        ResultType resultType;
        if(result==1){
            resultType = ResultUtil.success(201, "添加成功",null);
            //设置新的id号
            idDao.updateID(newId,"template");
        } else {
            resultType = ResultUtil.error(202, "添加失败");
        }
        return resultType;
    }

    @Override
    public ResultType getTagsByTemplateId(int id) {
        List<TemplateEntity> result = templateDao.getTagsByTemplateId(id);
        if(result.size()!=0){
            return ResultUtil.success(200,"获取成功",result.get(0).getTemplateTags());
        } else {
            return ResultUtil.error(200,"获取成功但无值");
        }
    }
}
