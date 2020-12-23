package org.example.dao;

import org.example.exception.AppException;
import org.example.model.Article;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-11;   Time: 23:39
 */
public class ArticleDAO {
    public static List<Article> queryByUserId(int userId){
        List<Article> articles = new ArrayList<>();
        Connection c = null;//先占着（就是这个意思）
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();//获取连接
            String sql = "select id, title, content, user_id, create_time,"+
                    " view_count from article where user_id=?";
            ps = c.prepareStatement(sql);
            ps.setInt(1,userId);//设置占位符（就是补充sql的？）
            rs = ps.executeQuery();
            while (rs.next()){
                Article a = new Article();
                a.setUserId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                articles.add(a);
            }
            return articles;
        }catch (Exception e){
            throw new AppException("art001","查询文章列表出错",e);
        }finally {
            DBUtil.close(c,ps,rs);//关闭sql连接，
        }
    }

    public static int insert(Article article) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DBUtil.getConnection();
            String sql = "insert into article(title,content,user_id)"+
                    "values (?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1,article.getTitle());
            ps.setString(2,article.getTitle());
            ps.setString(3,article.getTitle());
            return ps.executeUpdate();
        }catch(Exception e){
            throw new AppException("art005","插入文章出错",e);
        }finally {
            DBUtil.close(c,ps);
        }
    }

    public static int delete(String[] ids) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DBUtil.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in (");
            for (int i = 0; i < ids.length; i++) {
                if (i != 0)
                    sql.append(",");
                sql.append("?");
            }
            sql.append(")");
            ps = c.prepareStatement(sql.toString());
            for (int i = 0; i < ids.length; i++) {
                ps.setInt(i+1,Integer.parseInt(ids[i]));
            }
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("art004","删除文章出错",e);
        }finally {
            DBUtil.close(c,ps);
        }
    }

    public static Article query(int id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "";
            ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            Article article = null;
            while (rs.next()){
                article = new Article();
                article.setId(id);
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
            }
            return article;
        }catch (Exception e){
            throw new AppException("art006","查询文章出错",e);
        }finally{
            DBUtil.close(c,ps,rs);
        }
    }

    public static int update(Article article) {
        Connection c = null;
        PreparedStatement ps = null;
        try{
            c = DBUtil.getConnection();
            String sql = "update article set title=?, content=? where id=?";
            ps = c.prepareStatement(sql);
            ps.setString(1, article.getTitle());
            ps.setString(2, article.getContent());
            ps.setInt(3, article.getId());
            return ps.executeUpdate();
        }catch (Exception e){
            throw new AppException("ART007", "修改文章操作出错", e);
        }finally {
            DBUtil.close(c, ps);
        }
    }
}
