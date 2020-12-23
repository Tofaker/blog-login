package org.example.servlet;

import org.example.dao.LoginDAO;
import org.example.exception.AppException;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-17;   Time: 0:39
 */
@WebServlet("/login")
public class LoginServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //常规操作
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = LoginDAO.query(username);//查询账号密码
        if (user == null)
            throw new AppException("log002","用户不存在");
        if (!user.getPassword().equals(password)) {
            throw new AppException("log003", "账号或密码错误");
        }
        HttpSession session = req.getSession();//获取session对象
        session.setAttribute("user",user);//设置session
        return null;
    }
}
