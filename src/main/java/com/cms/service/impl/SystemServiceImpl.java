package com.cms.service.impl;

import com.cms.dao.IdDao;
import com.cms.dao.SystemDao;
import com.cms.entity.IDEntity;
import com.cms.entity.SystemEntity;
import com.cms.service.SystemService;
import com.cms.utils.LoadFile;
import com.cms.utils.ResultType;
import com.cms.utils.ResultUtil;
import com.cms.utils.Route;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    SystemDao systemDao;
    @Autowired
    IdDao idDao;

    @Override
    public ResultType<SystemEntity> getSysDescribeByCondition(Map<String, Object> map) {
        //条件值
        String sysName = map.get("sysName")==null?null:map.get("sysName").toString();
        String url = map.get("url")==null?null:map.get("url").toString();
        int parentId = Integer.parseInt(map.get("parentId").toString());
        int currentPage = Integer.parseInt(map.get("currentPage").toString());
        int number = Integer.parseInt(map.get("number").toString());

        PageHelper.startPage(currentPage,number);
        List<SystemEntity> result = systemDao.getSysDescribeByCondition(sysName, url, parentId);
        PageInfo<SystemEntity> pageInfo = new PageInfo<>(result);

        //查询
        ResultType resultType = new ResultType();
        resultType.setCode(200);
        resultType.setTotal(pageInfo.getTotal());

        if(result.size()>0){
            resultType.setMsg("得到数据");
            List data = new ArrayList();
            for(int i = 0; i < result.size(); i++){
                SystemEntity temp = result.get(i);
                Map tempMap = new HashMap();
                tempMap.put("sysId", temp.getSysId());
                tempMap.put("sysName", temp.getSysName());
                tempMap.put("sysIconPath", temp.getSysIconPath());
                tempMap.put("sysUrl", temp.getSysUrl());
                data.add(tempMap);
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
    public ResultType<SystemEntity> getSysName(int cid) {
        List<SystemEntity> result = systemDao.getSysName(cid);

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

    @Override
    public ResultType<SystemEntity> iconUpload(MultipartFile content) {
        //判断cms文件夹是否存在
        File cmsFile = new File(Route.CMSPATH);
        if(!cmsFile.exists()){
            cmsFile.mkdirs();
        }
        String path = Route.ICONPATH;
        return LoadFile.uploadFile(path, content);
    }

    @Override
    public ResultType<SystemEntity> addSystem(Map<String, Object> map) {
        //条件值
        String copyRight = map.get("copyRight")==null?null:map.get("copyRight").toString();
        String sysName = map.get("sysName")==null?null:map.get("sysName").toString();
        String email = map.get("email")==null?null:map.get("email").toString();
        String phone = map.get("phone")==null?null:map.get("phone").toString();
        String saveName = map.get("saveName")==null?null:map.get("saveName").toString();
        String url = map.get("url")==null?null:map.get("url").toString();
        String path = map.get("path")==null?null:map.get("path").toString();
        int creatorId = Integer.parseInt(map.get("creatorId").toString());

        //获得表中的id号，使得序号自增
        List<IDEntity> id = idDao.getID("systeminfo");
        //新的id
        int newId = id.get(0).getId()+1;

        //判断cms文件夹是否存在
        File cmsFile = new File(Route.CMSPATH);
        if(!cmsFile.exists()){
            cmsFile.mkdirs();
        }
        //新建本系统的存储文件夹
        File saveFile = new File(Route.CMSPATH + "/" + saveName);
        saveFile.mkdirs();

        //向数据库添加
        int result = systemDao.addSystem(newId, copyRight, sysName, path, url, saveName, phone, email, creatorId);

        if(result==1){
            //成功之后设置新的id号
            idDao.updateID(newId,"systeminfo");
            return ResultUtil.success(201, "添加成功",null);
        } else {
            //添加失败的情况下将已生成的文件夹删除
            saveFile.delete();
            return ResultUtil.error(202, "添加失败");
        }
    }
}
