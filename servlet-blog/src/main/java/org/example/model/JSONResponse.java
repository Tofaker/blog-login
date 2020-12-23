package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-06;   Time: 22:02
 */
@Getter
@Setter
@ToString
public class JSONResponse {//前后端统一数据格式
    private boolean success;//业务操作是否成功
    private String code;//状态码
    private String message;//状态信息
    private Object data;//数据 用Object类
}
