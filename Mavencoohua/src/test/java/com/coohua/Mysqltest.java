package com.coohua;

import java.sql.*;

public class Mysqltest {
    // JDBC 驱动名及数据库 URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_url ="jdbc:mysql://192.168.127.129:3306/school";
    //用户名密码
    public static final String user="root";
    public static final String password="123456";
    public static void main(String[] args){
        Connection con ;
        Statement sta ;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库...");
            con = DriverManager.getConnection( DB_url,user,password);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            sta = con.createStatement();
            String sql ="select name from class";
            ResultSet rs = sta.executeQuery(sql);

            System.out.println(rs);



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }



    }

}
