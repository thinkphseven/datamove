package com.longtu.datamove.strategy;

import com.longtu.datamove.strategy.ConnectDB;
import com.longtu.datamove.util.DmUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

/**
 * @Title: ConnectForOracle
 * @description:
 * @author: hk
 * @date: 2021-05-13 10:19
 **/
@Component
public class ConnectForOracle extends AConnectDB {


    @Override
    public Connection getConnection(String user, String pwd, String port, String ip, String db) {
        return DmUtil.getConnection(DmUtil.DRIVER_ORACLE, getUrl(ip, port, db), user, pwd);
    }

    @Override
    public String getUrl(String ip, String port, String db) {
        return "jdbc:oracle:thin:@" + ip + ":" +port + ":"+ db;
    }

}