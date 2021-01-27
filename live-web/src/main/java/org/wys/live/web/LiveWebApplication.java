package org.wys.live.web;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = "org.wys.live")
@MapperScan(value = "org.wys.live")
public class LiveWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiveWebApplication.class, args);
    }

}
