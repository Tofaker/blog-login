package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-14;   Time: 23:22
 */

@WebServlet("/articleAdd")//映射路径
public class ArticleAddServlet extends AbstractBaseServlet {
    @Override
    protected Object process (HttpServletRequest req, HttpServletResponse resp) throws Exception{
        HttpSession session = req.getSession(false);//session拿到用户id
        User user = (User)session.getAttribute("user");
        InputStream is = req.getInputStream();//因为是stream来交接数据
        Article article = JSONUtil.deserialize(is,Article.class);//交接的数据需要反序列化，请求数据类型为application/json，
        article.setUserId(user.getId());//插入文章设置下userid
        int num = ArticleDAO.insert(article);//文章放入数据库中
        return null;
    }
}
