package com.cms.service.impl;

import com.cms.dao.DataDao;
import com.cms.dao.IdDao;
import com.cms.entity.DataEntity;
import com.cms.entity.IDEntity;
import com.cms.service.DataService;
import com.cms.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataDao dataDao;
    @Autowired
    IdDao idDao;

    @Override
    public ResultType<DataEntity> getDataBySiteId(int siteId) {
        List<DataEntity> result = dataDao.getDataBySiteId(siteId);
        ResultType resultType = new ResultType();
        resultType.setCode(200);
        List data = new ArrayList();
        for(int i=0; i<result.size();i++){
            DataEntity tempData = result.get(i);
            Map tempMap = new HashMap();
            tempMap.put("id",tempData.getDataId());
            tempMap.put("title",tempData.getTitle());
            data.add(tempMap);
        }
        resultType.setData(data);
        return resultType;
    }

    /**
     * 根据条件获取内容
     * @param map
     * @return
     */
    @Override
    public ResultType<DataEntity> getDataByCondition(Map<String, Object> map) {
        //条件值
        String contentTitle = map.get("contentTitle")==null?null:map.get("contentTitle").toString();
        String siteName = map.get("siteName")==null?null:map.get("siteName").toString();
        int sysId = map.get("sysId")==null?-1:Integer.parseInt(map.get("sysId").toString());
        int parentId = Integer.parseInt(map.get("parentId").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage,number);
        List<DataEntity> result = dataDao.getDataByCondition(contentTitle,siteName,sysId, parentId);
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
                tempMap.put("describe", tempData.getDescribe());
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

    @Override
    public ResultType loadLocalContent(Map<String, Object> map) {
        String fileName = map.get("path")==null?null:map.get("path").toString();
        String type = map.get("type")==null?null:map.get("type").toString();
        return LoadFile.Load(fileName,type);
    }

    /**
     *
     * @param content
     * @return
     */
    @Override
    public ResultType<DataEntity> contentUpload(MultipartFile content) {
        return LoadFile.uploadFile(Route.DARAPATH, content);
    }

    @Override
    public ResultType<DataEntity> addContent(Map<String, Object> map) {
        //获得表中的id号，使得序号自增
        List<IDEntity> id = idDao.getID("data");
        //新的id
        int newId = id.get(0).getId()+1;

        //条件值
        String title = map.get("title")==null?null:map.get("title").toString();
        String path = map.get("path")==null?null:map.get("path").toString();
        String describe = map.get("describe")==null?null:map.get("describe").toString();
        int siteId = map.get("siteId")==null?-1:Integer.parseInt(map.get("siteId").toString());
        int creatorId = map.get("creatorId")==null?-1:Integer.parseInt(map.get("creatorId").toString());

        int result = dataDao.addContent(newId, title, path, describe, siteId, creatorId);
        ResultType resultType;
        if(result==1){
            resultType = ResultUtil.success(201, "添加成功",null);
            //成功之后设置新的id号
            idDao.updateID(newId,"data");
        } else {
            resultType = ResultUtil.error(202, "添加失败");
        }
        return resultType;
    }

    @Override
    public ResultType<DataEntity> updateContent(Map<String, Object> map) {
        //条件值
        String textValue = map.get("textValue")==null?null:map.get("textValue").toString();
        String path = map.get("path")==null?null:map.get("path").toString();
        int contentId = map.get("contentId")==null?-1:Integer.parseInt(map.get("contentId").toString());
        int state = 2;

        try {
            String fileResult = WriteFile.writeByFileOutputStream(path, textValue);
            if("SUCCESS".equals(fileResult)){
                dataDao.updateTextValue(state, contentId);
                return ResultUtil.success(207,"内容修改成功，请重新生成页面！", null);
            }
            return ResultUtil.error(208, "文件写入失败！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultUtil.error(208, "内容修改失败！");
    }

    @Override
    public ResultType<DataEntity> deleteContent(Map<String, Object> map) {
        //条件值
        int contentId = Integer.parseInt(map.get("contentId").toString());
        String path = map.get("path")==null?null:map.get("path").toString();

        int result = dataDao.deleteContent(contentId);
        if(result == 1){
            File file = new File(path);
            if(file.exists()){
                file.delete();
                return ResultUtil.success(204, "内容删除成功", null);
            }else{
                return ResultUtil.error(205, "内容文件不存在");
            }
        } else{
            return ResultUtil.error(205, "删除失败");
        }
    }
}
