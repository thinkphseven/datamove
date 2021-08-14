package com.longtu.datamove;

import com.longtu.datamove.entity.User;
import com.longtu.datamove.repositiory.UserRepositiory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatamoveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatamoveApplication.class, args);
    }

    @Bean
    InitializingBean saveData(UserRepositiory repo){
        return ()->{
            repo.save(new User((long) 1,"admin","系统管理员", "123456"));
        };
    }
}
