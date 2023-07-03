package com.museum.handler;

import com.museum.vo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
*名称:AllExceptionHandler
*描述:
*@author:Nagisa
*@datetime:2023-06-29 11:25
*@version 1.0
*/
public class AllExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public com.museum.vo.Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }
}
