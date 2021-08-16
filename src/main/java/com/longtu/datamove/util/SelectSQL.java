package com.longtu.datamove.util;

import com.longtu.datamove.entity.Plan;
import com.longtu.datamove.strategy.ConnectDB;
import com.longtu.datamove.strategy.StrategySimpleFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectSQL {

    /**
     * 根据sql查询这个sql所有字段
     * @param sql
     * @param plan
     * @return
     */
    public static List<String> selectSQLtoDb(String sql, Plan plan) {
        Connection conn = null;
        java.sql.Statement statement = null;
        ResultSet rs = null;
        List<String> columnList = new ArrayList<String>();
        try {
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(sql);
            ConnectDB connectDB = StrategySimpleFactory.getInstance(plan.getSourceDbType());
            conn = connectDB.getConnection(plan.getSourceUsername(), plan.getSourcePwd(), plan.getSourcePort(), plan.getSourceIp(), plan.getSourceDb());
            statement = conn.createStatement();
            rs = statement.executeQuery(sqlBuffer.toString());
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                String columnName = rsmd.getColumnName(i).toLowerCase();
                columnList.add(columnName);
            }

            for (String str : columnList) {
                System.out.print(str + ", ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn) {
                    conn.close();
                }
                if (null != statement) {
                    statement.close();
                }
                if (null != rs) {
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return columnList;
    }

    /**
     * 连接驱动
     * @param plan
     * @param value
     * @return
     */
    public static Connection selectConnection(Plan plan,String value) {
        String dbType = "";
        String db = "";
        String username = "";
        String pwd = "";
        String port = "";
        String ip = "";
        if(value.equals("1")){
            dbType = plan.getSourceDbType();
            db = plan.getSourceDb();
            username = plan.getSourceUsername();
            pwd = plan.getSourcePwd();
            port = plan.getSourcePort();
            ip = plan.getSourceIp();
        }else{
            dbType = plan.getTargetDbType();
            db = plan.getTargetDb();
            username = plan.getTargetUsername();
            pwd = plan.getTargetPwd();
            port = plan.getTargetPort();
            ip = plan.getTargetIp();
        }

        ConnectDB connectDB = StrategySimpleFactory.getInstance(dbType);
        Connection connection = connectDB.getConnection(username, pwd, port, ip, db);

        return connection;
    }

}
