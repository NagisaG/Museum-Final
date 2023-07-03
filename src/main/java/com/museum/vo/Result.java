package com.museum.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 名称:Result
 * 描述:
 *
 * @version 1.0
 * @author:Nagisa
 * @datetime:2023-06-29 12:10
 */
@Data
@AllArgsConstructor
public class Result{
    private boolean success;
    private int code;
    private String msg;
    private Object data;
    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }
    public static Result fail(int code,String msg){
        return new Result(false,code,msg,null);
    }
}
