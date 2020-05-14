package com.cms.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public class LoadFile {
    public static ResultType Load(String path,String type){
        ResultType resultType = new ResultType();
        String result = null;
        if(path==null){
            resultType.setCode(203);
            resultType.setMsg("找不到指定文件");
            return resultType;
        }
        if(type.equals("txt")){
            result = LoadFile(path);
        } else if("jpg".equals(type)){
            result = LoadImg(path);
        }
        if(result!=null){
            resultType.setCode(200);
            resultType.setMsg("成功");
            resultType.setData(result);
        } else {
            resultType.setCode(203);
            resultType.setMsg("找不到指定文件");
        }
        return resultType;
    }

    public static String LoadFile(String path){
        File file = new File(path);
        if(file.exists()){
            return ReadFile.readText(file);
        }
        return null;
    }

    public static String LoadImg(String path){
        return ReadFile.readImg(path);
    }

    public static ResultType uploadFile(String path, MultipartFile file){
        ResultType resultType;

        //为了避免多次上传同一个文件导致命名重复，在文件名前加UUID前缀
        String prefix = UUID.randomUUID().toString();
        prefix = prefix.replace("-", "");
        String filename = prefix + "_" + file.getOriginalFilename();
        File filepath = new File(path);
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
            file.transferTo(new File(path+"/"+filename));
            resultType = ResultUtil.success(201,"文件上传成功", path+"/"+filename);
        } catch (Exception e)
        {
            e.printStackTrace();
            resultType = ResultUtil.error(202,"文件上传失败");
        }
        return resultType;
    }

}
