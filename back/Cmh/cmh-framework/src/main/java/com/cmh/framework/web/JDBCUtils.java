package com.cmh.framework.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    private static final String connectionURL = "jdbc:mysql://localhost:3306/cmh-v2?serverTimezone=UTC"; //连接数据库的路径url
    private static final String username = "root";  //数据库账号
    private static final String password = "1217";  //数据库密码


    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  //驱动
            return   DriverManager.getConnection(connectionURL,username,password);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }


    public static void close(ResultSet rs,Statement stmt,Connection con) throws SQLException {
        if(rs!=null)
            rs.close();
        if(stmt!=null)
            stmt.close();
        if(con!=null)
            con.close();
    }
}

