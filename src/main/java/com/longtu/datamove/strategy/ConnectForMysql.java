package com.longtu.datamove.strategy;

import com.longtu.datamove.util.DmUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;


@Component
public class ConnectForMysql extends AConnectDB {


    @Override
    public Connection getConnection(String user, String pwd, String port, String ip, String db) {
        return DmUtil.getConnection(DmUtil.DRIVER_MYSQL, getUrl(ip, port, db), user, pwd);
    }

    @Override
    public String getUrl(String ip, String port, String db) {
        return "jdbc:mysql://" + ip + ":" + port + "/" + db + "?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
    }
}
