package com.test;

import java.sql.*;

public class MysqlConnect {
    public static void main(String[] args){
        //创建三个接口对象
        Connection connection = null;
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("获取驱动类失败");
            e.printStackTrace();
        }
        //连接数据库：使用java.sql.DriverManger的getConnection方法已数据库的url加上数据源库的user和passwod做参数
        try {
            connection = DriverManager.getConnection("jdbc:mysql:192.168.127.129:3306/school?characterEncoding=GBK","root","123456");
        } catch (SQLException e) {
            System.out.println("链接数据库失败");
            e.printStackTrace();
        }
        String sql ="select name from CLASS";
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet = preparedStatement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{

            while(resultSet.next()) {
                String name=resultSet.getString("name");
                System.out.println("name:"+name);
                }
          }catch (SQLException e){
            e.printStackTrace();

        }
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    }
