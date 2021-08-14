package com.longtu.datamove.strategy;

import com.longtu.datamove.entity.Rule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ConnectDB {

    Connection getConnection(String user, String pwd, String port, String ip, String db);

    String getUrl(String ip, String port, String db);

    List<Map<String, Object>> query(Connection conn, String sql);

    boolean execute(Connection conn, String sql) throws SQLException;

    boolean insertBatch(Connection conn, List<Map<String, Object>> datas, Rule rule) throws SQLException;
}
