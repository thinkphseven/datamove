package com.longtu.datamove.strategy;

import com.longtu.datamove.entity.Rule;
import com.longtu.datamove.util.StringUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AConnectDB implements ConnectDB{

    public abstract Connection getConnection(String user, String pwd, String port, String ip, String db);

    public abstract String getUrl(String ip, String port, String db);

    @Override
    public List<Map<String, Object>> query(Connection conn, String sql) {
        List<Map<String, Object>> datas = new ArrayList<>();
        // String Sql="select id,sys_user_name,sys_user_pwd from base_admin_user";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement(); //也可以使用PreparedStatement来做
            rs = stmt.executeQuery(sql);//执行sql语句并返还结束
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数

            while (rs.next()) {//遍历结果集 ，向下一行
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    if (String.valueOf(rs.getObject(i)).indexOf("99999999999999999999")==-1) {
                        rowData.put(md.getColumnName(i), String.valueOf(rs.getObject(i)).replaceAll("99999999999999999999","xtgly"));
                    }else {
                        rowData.put(md.getColumnName(i), rs.getObject(i));
                    }
                }
                datas.add(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return datas;
        }
    }

    @Override
    public boolean execute(Connection conn, String sql) throws SQLException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement(); //也可以使用PreparedStatement来做
            boolean flag = stmt.execute(sql);//执行sql语句并返还结束
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public boolean insertBatch(Connection conn, List<Map<String, Object>> datas, Rule rule,String targetStr) throws SQLException {
        String targetSql = rule.getTargetSql();
        targetSql = targetSql.replace("#{fzhs}", targetStr.substring(0, targetStr.length() - 1));
        String sql = targetSql.toLowerCase();//转成小写
        //insert into table(aa,bb,cc);
        String tableName = "";
        //截取表名
        tableName= sql.split("into")[1].split("[(]")[0] + " ";

        //给？赋值
        String [] var1 = sql.split("[(]");
        String var2 = var1[1].trim();
        String var3 = var2.substring(0, var2.length() - 1);
        String [] columns = var3.split(",");
        int length = columns.length;
        sql += "values(";
        for (int i=1; i <= length; i++) {
            sql += "?,";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ")";

        try{
            //先执行删除语句  在执行insert
            String delSql = "delete from "+tableName+" where mof_div_code = ? and agency_code=? ";

            PreparedStatement prest = conn.prepareStatement(delSql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //给delSql问号赋值
            for(Map<String, Object> d : datas){
                for (int i=1; i <= length; i++) {
                    if("mof_div_code".equals(columns[i-1].trim().toLowerCase())){
                        prest.setObject(1, d.get(columns[i-1].trim().toLowerCase()) == null ? d.get(columns[i-1].trim().toUpperCase()) : d.get(columns[i-1].trim().toLowerCase()));
                    }
                    if("agency_code".equals(columns[i-1].trim().toLowerCase())){
                        prest.setObject(2, d.get(columns[i-1].trim().toLowerCase()) == null ? d.get(columns[i-1].trim().toUpperCase()) : d.get(columns[i-1].trim().toLowerCase()));
                    }
                }
                prest.addBatch();
            }
            prest.executeBatch();
//sql.indexOf("")
//            sql = sql.replaceAll(sql, "" )
            String sqlselect = "select "+var3+" from "+tableName;
            Statement statement = conn.createStatement();//也可以使用PreparedStatement来做
            ResultSet rs = statement.executeQuery(sqlselect);//执行sql语句并返还结束
            ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
            int columnCount = md.getColumnCount();   //获得列数
            HashMap<String, String> ms = new HashMap<>();
            for (int i = 1; i < columnCount + 1; i++) {
                //字段，类型
                ms.put(md.getColumnName(i).toUpperCase(), md.getColumnClassName(i));
            }
                    //执行插入逻辑
            prest = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            for(Map<String, Object> d : datas){
                for (int i=1; i <= length; i++) {
                    if (String.valueOf(d.get(columns[i - 1].trim().toLowerCase())).indexOf("99999999999999999999")==-1) {
                        Object obj = d.get(columns[i - 1].trim().toLowerCase()) == null ? d.get(columns[i - 1].trim().toUpperCase()) : d.get(columns[i - 1].trim().toLowerCase());
                        String mscolumn = ms.get(columns[i - 1].trim().toUpperCase());
                        StringUtil.getTypeConversion(prest,obj,mscolumn,i);
                    } else {
                        prest.setObject(i, String.valueOf(d.get(columns[i - 1].trim().toLowerCase())).replaceAll("99999999999999999999","xtgly"));
                    }
                }
                prest.addBatch();
            }
            prest.executeBatch();
            if(conn !=null){
                try{
                    conn.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            conn.rollback();
            return false;
        }
        return true;
    }
}
