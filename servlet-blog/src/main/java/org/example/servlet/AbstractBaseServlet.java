package org.example.servlet;

import org.example.model.JSONResponse;
import org.example.exception.AppException;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-14;   Time: 21:58
 */
//模板设计模式
public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);//将两个合并一下
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        JSONResponse json = new JSONResponse();//前后端统一一下数据格式对象  json
        //process解析请求数据
        try{
            Object data = process(req,resp);//process成功后data获取到
            json.setSuccess(true);//设置success
            json.setData(data);//设置数据
        }catch(Exception e){//操作失败，但是要找出错误
            e.printStackTrace();//返回出错误信息
            //业务操作失败，设置success=false，code错误码，message
            //处理自定义异常（code，message），2.其他异常（错误消息可能是英文）
            String code = "unknown";//
            String message = "未知错误请联系管理员";
            if (e instanceof AppException){//运行时出错
                code = ((AppException) e).getCode();
                message = e.getMessage();
            }
            json.setCode(code);
            json.setMessage(message);
        }
        //后端提交给前端数据，统一一下数据对象：序列化为json字符串
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(json));
        pw.flush();
        pw.close();
    }

    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
