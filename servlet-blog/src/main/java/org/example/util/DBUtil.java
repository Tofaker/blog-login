package org.example.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.example.exception.AppException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    //准备连接数据库
    private static final DataSource DS = new MysqlDataSource();
    static{
        ((MysqlDataSource) DS).setUrl("jdbc:mysql://localhost:3306/chong_blog?user=root&password=123456&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
    }

    public static Connection getConnection(){//连接数据库
        try {
            return DS.getConnection();
        } catch (SQLException e) {
            throw new AppException("DB001", "获取数据库连接失败", e);
        }
    }

    public static void close(Connection c, Statement s){
        close(c, s, null);
    }

    public static void close(Connection c, Statement s, ResultSet r){
        try {
            if(r != null)
                r.close();
            if(s != null)
                s.close();
            if(c != null)
                c.close();
        } catch (SQLException e) {
            throw new AppException("DB002", "释放数据库资源失败", e);
        }
    }
}
