package com.cms.utils;

public class ResultUtil {
    public static ResultType success(Integer code, String msg, Object object) {
        ResultType resultType = new ResultType();
        resultType.setCode(code);
        resultType.setMsg(msg);
        resultType.setData(object);
        return resultType;
    }

    public static ResultType error(Integer code, String msg) {
        ResultType resultType = new ResultType();
        resultType.setCode(code);
        resultType.setMsg(msg);
        return resultType;
    }
}
