package com.longtu.datamove.strategy;

import com.longtu.datamove.util.SpringBeanUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单工厂模式，提供子类的创建，建立抵消类型和子类的对于关系（避免业务代码if else）
 * 利用jvm类加载特性保证线程安全和单例的实现
 * @ClassName StrategySimpleFactory
 */
public class StrategySimpleFactory {
    public static Map<String, ConnectDB> map ;
    static {
        //初始化所有的策略子类对应关系
        map = new ConcurrentHashMap<String, ConnectDB>();
        map.put("mysql", (ConnectDB) SpringBeanUtil.getBean("connectForMysql"));
        map.put("oracle", (ConnectDB) SpringBeanUtil.getBean("connectForOracle"));
        map.put("postgresql", (ConnectDB) SpringBeanUtil.getBean("connectForPostGreSql"));
        map.put("sqlserver", (ConnectDB) SpringBeanUtil.getBean("connectForSqlServer"));
        map.put("H2", (ConnectDB) SpringBeanUtil.getBean("connectForH2"));
    }

    /**
     * 根据抵消类型获取具体子类
     * @param type - 抵消类型
     * @return
     */
    public static ConnectDB getInstance(String type) {
        if (type == null) {
            throw new RuntimeException("数据库类型不能为空");
        }
        return map.get(type);
    }
}
