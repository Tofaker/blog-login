package org.example.exception;

import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-11;   Time: 22:16
 */
@Getter
public class AppException extends RuntimeException{//后面的代码中药形成异常链
    private String code;//出错时抛异常给定错误码，方便排查问题
    public AppException(String code,String message){
        this(code,message,null);
    }
    public AppException(String code,String message,Throwable cause){
        super(message,cause);
        this.code = code;
    }
}
