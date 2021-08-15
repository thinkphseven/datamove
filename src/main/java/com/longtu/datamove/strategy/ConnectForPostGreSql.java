package com.longtu.datamove.strategy;

import com.longtu.datamove.util.DmUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;


@Component
public class ConnectForPostGreSql extends AConnectDB {


    @Override
    public Connection getConnection(String user, String pwd, String port, String ip, String db) {
        return DmUtil.getConnection(DmUtil.DRIVER_POSTGRESQL, getUrl(ip, port, db), user, pwd);
    }

    @Override
    public String getUrl(String ip, String port, String db) {
        return "jdbc:postgresql://" + ip + ":" +port + "/"+ db;
    }


}
