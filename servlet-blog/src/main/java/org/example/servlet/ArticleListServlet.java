package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.exception.AppException;
import org.example.model.Article;
import org.example.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-16;   Time: 1:43
 */
@WebServlet("/articleList")
public class ArticleListServlet extends AbstractBaseServlet{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取请求数据：用户id（1） ，前端写定了，先不改变
        //拿到并检查session
        HttpSession session = req.getSession(false);
        if (session == null){
            throw new AppException("art002","没有登录，不允许访问");
        }
        User user = (User) session.getAttribute("user");//
        if (user == null){
            throw new AppException("art002","没有登录，不允许访问");
        }
        //session正确，根据用户id查询文章列表
        List<Article> articles = ArticleDAO.queryByUserId(user.getId());
        return articles;//返回列表
    }
}
