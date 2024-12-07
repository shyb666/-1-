package com.bite.book.model;

import com.bite.book.enums.ResultStatus;
import lombok.Data;

@Data
public class Result<T> {
    private ResultStatus code; //业务码  不是Http状态码  200-成功  -2 失败  -1 未登录
    private String errMsg; //错误信息, 如果业务成功, errMsg为空
    private T data;

    public static <T> Result success(T data){
        Result result = new Result<>();
        result.setCode(ResultStatus.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result nologin(){
        Result result = new Result<>();
        result.setCode(ResultStatus.NOLOGIN);
        result.setErrMsg("用户未登录");
        return result;
    }

    public static Result fail(String msg){
        Result result = new Result<>();
        result.setCode(ResultStatus.FAIl);
        result.setErrMsg(msg);
        return result;
    }

    public static Result fail(ResultStatus resultStatus, String msg){
        Result result = new Result<>();
        result.setCode(resultStatus);
        result.setErrMsg(msg);
        return result;
    }
}
