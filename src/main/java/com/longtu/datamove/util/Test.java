package com.longtu.datamove.util;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: Test
 * @description:
 * @author: hk
 * @date: 2021-04-30 09:26
 **/
public class Test {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.getBean("");






       List<Map<String, Object>> datas = new ArrayList<>();
        String driver = "com.mysql.cj.jdbc.Driver";// 驱动程序类名
        String url = "jdbc:mysql://localhost:3306/datamove?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8" ;// 数据库URL
        Connection conn = getConnection("root", "haokui", driver, url);// 获取数据库连接
        query(conn, datas);   //方法名调用数据库连接

        //
        /*for (int i=0; i < 100000; i++) {
            Map<String, Object> m = new HashMap<>();
            m.put("sys_user_name","haokui");
            m.put("sys_user_pwd","123");
            datas.add(m);
        }
        insertBatch(conn,datas);
        releaseConnection(conn);*///释放数据库连接

        //将数据插入oracle
         /*driver = "oracle.jdbc.driver.OracleDriver";// 驱动程序类名
         url = "jdbc:oracle:thin:@10.10.15.26:1521:orcl" ;// 数据库URL
         conn = getConnection("gcfr_20210331", "123", driver, url);// 获取数据库连接
        insertBatch(conn, datas);   //方法名调用数据库连接
        System.out.println("oracle....");
        releaseConnection(conn);*///释放数据库连接

        System.out.println("oracle结束。。。。。");
        //连接postgresql
        driver = "org.postgresql.Driver";// 驱动程序类名
        url = "jdbc:postgresql://112.125.27.123:5432/testdb" ;// 数据库URL
        conn = getConnection("postgres", "postgres", driver, url);// 获取数据库连接
        insertBatch(conn, datas);   //方法名调用数据库连接
        releaseConnection(conn);//释放数据库连接
    }
    //查询数据，定义的query方法
    public static void query(Connection conn, List<Map<String, Object>> datas ){
        String Sql="select id,sys_user_name,sys_user_pwd from base_admin_user";
        try{
            Statement stmt=conn.createStatement(); //也可以使用PreparedStatement来做
            ResultSet rs=stmt.executeQuery(Sql);//执行sql语句并返还结束
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            while(rs.next()){//遍历结果集 ，向下一行
                Map<String,Object> rowData = new HashMap<String,Object>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                datas.add(rowData);
            }
            if(rs !=null){
                try{
                    rs.close();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(stmt !=null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(conn !=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void insertBatch(Connection conn, List<Map<String, Object>> datas ){
        try{
            String sql = "INSERT into base_admin_user(id,sys_user_name,sys_user_pwd) VALUES(?,?,?)";
            PreparedStatement prest = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            for(Map<String, Object> enter:datas){
                prest.setObject(1, enter.get("id"));
                prest.setObject(2, enter.get("sys_user_name"));
                prest.setObject(3, enter.get("sys_user_pwd"));
                prest.addBatch();
            }
            prest.executeBatch();
         //   conn.commit();
            if(conn !=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //数据库连接
    public static Connection getConnection(String user, String pass, String driver, String url) {
        Connection conn = null;//声明连接对象
        try {
            Class.forName(driver);// 注册(加载)驱动程序
            conn = DriverManager.getConnection(url, user, pass);// 获取数据库连接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载数据库驱动失败");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return conn;
    }
    //释放数据库连接
    public static void releaseConnection(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
