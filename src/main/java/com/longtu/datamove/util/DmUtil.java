package com.longtu.datamove.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @Title: DmUtil
 * @description:
 * @author: hk
 * @date: 2021-05-13 10:26
 **/
public class DmUtil {

    public static String DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";

    public static String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";

    public static String DRIVER_POSTGRESQL = "org.postgresql.Driver";

    public static String DRIVER_SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String DRIVER_H2 = "org.h2.Driver";

    public static Connection getConnection(String driver, String url, String user, String pwd) {
        Connection conn = null;//声明连接对象
        try {
            Class.forName(driver);// 注册(加载)驱动程序
            conn = DriverManager.getConnection(url, user, pwd);// 获取数据库连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载数据库驱动失败");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return conn;
    }
}
