package com.cms.service.impl;

import com.cms.dao.DataDao;
import com.cms.dao.IdDao;
import com.cms.dao.PageDao;
import com.cms.dao.SiteDao;
import com.cms.entity.*;
import com.cms.service.PageService;
import com.cms.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    PageDao pageDao;

    @Autowired
    SiteDao siteDao;

    @Autowired
    IdDao idDao;

    @Autowired
    DataDao dataDao;

    /***
     * 根据系统id获得站点和页面信息
     * @param sysId
     * @return
     */
    @Override
    public ResultType<PageEntity> getPagesBySysid(int sysId) {
        ResultType pageResult = new ResultType();
        pageResult.setCode(200);
        pageResult.setMsg("获取生成页信息");
        List data = new ArrayList();

        //获取站点信息
        List<SiteEntity> sites = siteDao.getSitesBySysid(sysId);
        List sitesList = new ArrayList();
        for(int i = 0; i < sites.size(); i++){
            Map siteMap = new HashMap();
            siteMap.put("siteId",sites.get(i).getSiteId());
            siteMap.put("siteName",sites.get(i).getSiteName());
            siteMap.put("siteUrl",sites.get(i).getSiteUrl());
            sitesList.add(siteMap);
        }
        data.add(sitesList);

        //获取页面信息
        List<PageEntity> page = pageDao.getPagesBySysid(sysId);
        List pagesList = new ArrayList();
        for(int i=0; i < page.size();i++){
            PageEntity temp = page.get(i);
            Map map = new HashMap();
            map.put("pageId",temp.getPageId());
            map.put("pageName",temp.getPageName());
            map.put("siteName",temp.getSiteEntity().getSiteName());
            map.put("pagePath",temp.getPagePath());
            map.put("state",temp.getState());
            map.put("createTime",temp.getCreateTime());
            pagesList.add(map);
        }
        data.add(pagesList);

        pageResult.setData(data);
        return pageResult;
    }

    @Override
    public ResultType<PageEntity> getPagesByCondition(Map<String, Object> map) {
        //条件值
        String name = map.get("name")==null?null:map.get("name").toString();
        int sysId = map.get("sysId")==null?-1:Integer.parseInt(map.get("sysId").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage, number);
        List<PageEntity> page = pageDao.getPagesByCondition(name,sysId);
        PageInfo<PageEntity> pageInfo = new PageInfo<>(page);

        ResultType pageResult = new ResultType();
        pageResult.setCode(200);
        pageResult.setMsg("获取生成页信息");
        pageResult.setTotal(pageInfo.getTotal());

        List data = new ArrayList();
        for(int i = 0; i < page.size(); i++){
            PageEntity temp = page.get(i);
            Map mapTemp = new HashMap();
            mapTemp.put("pageId",temp.getPageId());
            mapTemp.put("pageName",temp.getPageName());
            mapTemp.put("fileName",temp.getFileName());
            mapTemp.put("pagePath",temp.getPagePath());
            mapTemp.put("state",temp.getState());
            mapTemp.put("creatorId",temp.getCreatorId());
            mapTemp.put("createTime",temp.getCreateTime());
            mapTemp.put("siteId",temp.getSiteEntity().getSiteId());
            mapTemp.put("siteName",temp.getSiteEntity().getSiteName());
            mapTemp.put("templateId",temp.getTemplateEntity().getTemplateId());
            mapTemp.put("templateName",temp.getTemplateEntity().getTemplateName());
            mapTemp.put("sysId",temp.getSystemEntity().getSysId());
            mapTemp.put("sysName",temp.getSystemEntity().getSysName());
            data.add(mapTemp);
        }
        pageResult.setData(data);

        return pageResult;
    }

    @Override
    public ResultType<PageEntity> deletePage(Map<String, Object> map) {
        int pageId = Integer.parseInt(map.get("pageId").toString());
        String pagePath = map.get("pagePath").toString();
        ResultType resultType;
        File file = new File(pagePath);
        if(file.exists()){
            if(file.delete()){
                int result = pageDao.deletePage(pageId);
                if(result==1){
                    resultType = ResultUtil.success(204,"删除成功",null);
                } else {
                    resultType = ResultUtil.error(205,"删除失败");
                }
            } else {
                resultType = ResultUtil.error(205,"删除失败");
            }
        } else {
            resultType = ResultUtil.error(205,"文件不存在");
        }
        return resultType;
    }

    @Override
    public ResultType<PageEntity> uploadPageInfo(Map<String, Object> map) {
        //获得表中的id号，使得序号自增
        List<IDEntity> id = idDao.getID("page");
        //新的id
        int newId = id.get(0).getId()+1;

        //条件值
        int sysId = map.get("sysId")==null?-1:Integer.parseInt(map.get("sysId").toString());
        int templateId = map.get("templateId")==null?-1:Integer.parseInt(map.get("templateId").toString());
        int siteId = map.get("siteId")==null?-1:Integer.parseInt(map.get("siteId").toString());
        int cId = map.get("cId")==null?-1:Integer.parseInt(map.get("cId").toString());
        String path = map.get("path")==null?null:map.get("path").toString();
        String pageFileName = map.get("pageFileName")==null?null:map.get("pageFileName").toString();//页面名
        String url = map.get("url")==null?null:map.get("url").toString();
        List content = map.get("content")==null?null: (ArrayList) map.get("content");
        int state = 0;//还未生成

        //页面存储
        int result = pageDao.addPage(newId, pageFileName, pageFileName, path, siteId, templateId, sysId, state, cId);
        if(result==1){
            //设置新的id号
            idDao.updateID(newId,"page");
            //获得表中的id号，使得序号自增
            List<IDEntity> pdId = idDao.getID("pagedatainfo");
            //新的id
            int pdNewId = pdId.get(0).getId()+1;
            //页面与内容存储
            for(int i = 0; i<content.size(); i++){
                //内容的id
                int did = (int) ((Map)content.get(i)).get("contentId");
                //tag
                String tagName = ((Map)content.get(i)).get("tagName").toString();
                int addResult = pageDao.addPageDataInfo(pdNewId, did, newId);
                if(addResult!=1){
                    return ResultUtil.error(202,"内容部分提交失败，请重新提交");
                }
                pdNewId++;
                dataDao.updateTag(did, tagName);
            }
            idDao.updateID(pdNewId,"pagedatainfo");
            return ResultUtil.success(201, "申请提交成功！", null);
        }
        return ResultUtil.error(202,"申请提交失败，请重新提交");
    }

    @Override
    public ResultType<PageEntity> createNewPage(Map<String, Object> map) {
        //条件值
        List unSavedPageId = (ArrayList) map.get("unSavedPageId");
        //创建页面的结果
        String createPageResult = "";
        for(int i=0; i<unSavedPageId.size(); i++){
            int pageId = (int) unSavedPageId.get(i);
            PageEntity pageInfo = pageDao.getPageinfoByPid(pageId).get(0);
            String filePath = pageInfo.getPagePath();
            //若页面已存在，则删除
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
            //读模板.
            String templatePath = pageInfo.getTemplateEntity().getTemplatePath();
            String templateContent = LoadFile.LoadFile(templatePath);
            //模板不存在的情况
            if(templateContent==null){
                return ResultUtil.error(203,"模板未找到!");
            }

            //找到内容和标签
            List<DataEntity> dataResult = dataDao.getDataByPageId(pageId);
            for(int j=0;j<dataResult.size();j++){
                DataEntity data = dataResult.get(j);
                String dataPath = data.getPath();
                int length = dataPath.length();
                String type = dataPath.substring(dataPath.indexOf(".")+1, length);
                String tag = "#{" + data.getTag() + "}";
                if("txt".equals(type)){
                    String result = LoadFile.LoadFile(dataPath);
                    templateContent = templateContent.replace(tag, result);
                } else {//图片
                    templateContent = templateContent.replace(tag, dataPath);
                }
            }
            //写入文件内容
            try {
                file.createNewFile();
                createPageResult = WriteFile.writeByFileOutputStream(filePath, templateContent);
                if("SUCCESS".equals(createPageResult)){
                    pageDao.updataState(pageId);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if("SUCCESS".equals(createPageResult)){
            return ResultUtil.success(201,"页面创建成功！", null);
        } else {
            return ResultUtil.error(202,"页面创建失败！");
        }
    }

    @Override
    public ResultType<PageEntity> getPageByPageId(Map<String, Object> map) {
        //条件值
        int pageId = map.get("savedPageId")==null?-1:(Integer)map.get("savedPageId");

        PageEntity pageResult = pageDao.getPageByPageId(pageId).get(0);
        List<DataEntity> contentResult = dataDao.getDataByPageId(pageId);
        //存放最后结果
        List result = new ArrayList();

        //处理content
        List dataList = new ArrayList();
        for(int i = 0; i < dataList.size(); i++){
            DataEntity dataEntity = contentResult.get(i);
            Map dataMap = new HashMap();
            dataMap.put("dataId", dataEntity.getDataId());
            dataMap.put("tag", dataEntity.getTag());
            dataList.add(dataMap);
        }

        Map pageMap = new HashMap();
        pageMap.put("fileName", pageResult.getFileName());
        pageMap.put("name", pageResult.getPageName());
        pageMap.put("path", pageResult.getPagePath());
        pageMap.put("siteId", pageResult.getSiteId());
        pageMap.put("templateId", pageResult.getTemplateId());
        pageMap.put("systemId", pageResult.getSysId());
        pageMap.put("content", dataList);

        return ResultUtil.success(200, "获取成功", pageMap);
    }

    @Override
    public ResultType<PageEntity> downloadFile(Map<String, Object> map, HttpServletResponse response) throws Exception {
        //文件夹名
        String fileFolderName = map.get("sysName")==null?null:map.get("sysName").toString();
        if(fileFolderName != null){
            //生成压缩文件
            DownloadFile zipCom = new DownloadFile(Route.ZIPPATH + fileFolderName+".zip", Route.CMSPATH + fileFolderName);
            zipCom.zip();

            response.setContentType("application/x-zip-compressed");
            response.setHeader("Content-Disposition",
                    "attachment;filename=\"" + URLEncoder.encode(fileFolderName+".zip", "UTF-8") + "\"");
            OutputStream outputStream = response.getOutputStream();
            InputStream inputStream = new FileInputStream(new File(Route.ZIPPATH + fileFolderName+".zip"));
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return ResultUtil.success(200,"下载成功", null);
        }
        return ResultUtil.error(206,"下载失败");
    }
}
