package org.example.dao;

import org.example.exception.AppException;
import org.example.model.User;
import org.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: madala
 * Date: 2020-12-11;   Time: 0:43
 */
public class LoginDAO {
    public static User query(String username) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            c = DBUtil.getConnection();
            String sql = "select id, username, password, nickname, birthday, phone_number, email, head" +
                    "        from user" +
                    "    where username = ?";
            ps = c.prepareStatement(sql);
            //设置占位符的值 TODO
            ps.setString(1,username);//先用id等于1来做出结果
            rs = ps.executeQuery();
            //处理结果集
            User user = null;
            while (rs.next()){//rs.next()遍历结果集，把每个属性啥的都拿出来给user新对象
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                java.sql.Date birthday = rs.getDate("birthday");
                if(birthday != null)
                    user.setBirthday(new Date(birthday.getTime()));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setHead(rs.getString("head"));
            }
            return user;
        }catch(Exception e){
            throw new AppException("Log 001","登录操作数据库执行错误",e);
        }finally {
            DBUtil.close(c,ps,rs);
        }
    }
}
