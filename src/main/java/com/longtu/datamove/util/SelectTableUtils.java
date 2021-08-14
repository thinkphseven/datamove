package com.longtu.datamove.util;

import java.util.HashMap;
import java.util.Map;

public class SelectTableUtils {

    public static Map<String,String> selectTableByPlanId(String type, String db) {
        HashMap<String, String> map = new HashMap<>();
        String sql = "";
        String mapKey = "TABLE_NAME";
        if("mysql".equals(type)){
            sql = "select table_name from information_schema.tables where table_schema='"+db+"'";
        }else if ("oracle".equals(type)){
            sql = "select table_name from user_tables";
        }else if ("postgresql".equals(type)){
            sql = "SELECT tablename FROM pg_tables WHERE tablename NOT LIKE 'pg%' AND tablename NOT LIKE 'sql_%' ORDER BY tablename";
            mapKey = "tablename";
        }else if ("sqlserver".equals(type)){
            sql = " select name from sysobjects where xtype='U' ORDER BY Name ";
            mapKey = "name";
        }
        map.put("sql",sql);
        map.put("mapKey",mapKey);

        return map;
    }

    public static Map<String,String> selectColumnByTable(String type,String tablename,String db) {
        HashMap<String, String> map = new HashMap<>();
        String sql = "";
        String mapKey = "COLUMN_NAME";
        if("mysql".equals(type)){
            sql = "select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name='"+tablename+"' and table_schema='"+db+"'";
        }else if ("oracle".equals(type)){
            sql = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '"+tablename+"'";
        }else if ("postgresql".equals(type)){
            sql = "select column_name from information_schema.columns where table_schema='public' and table_name='"+tablename+"'";
            mapKey = "column_name";
        }else if ("sqlserver".equals(type)){
            sql = "SELECT name FROM SysColumns WHERE id=Object_Id('"+tablename+"')";
            mapKey = "name";
        }
        map.put("sql",sql);
        map.put("mapKey",mapKey);

        return map;
    }
}
