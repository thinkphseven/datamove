package com.longtu.datamove.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Title: CommandRunMonitor
 * @description:
 * @author: hk
 * @date: 2021-04-28 16:00
 **/
@Component
public class CommandRunMonitor  implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CommandRunMonitor.class);

    @Value("${spring.web.loginurl}")
    private String loginUrl;

    @Value("${spring.web.excute}")
    private String googleExcutePath;

    @Value("${spring.auto.openurl}")
    private boolean isOpen;

    @Override
    public void run(String... args) throws Exception {
        if(isOpen){
            String cmd = googleExcutePath +" "+ loginUrl;
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                logger.debug("启动浏览器打开项目成功");
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
    }
}
