package com.simulation.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 *
 * @author hedongzhou
 * @date 2018/11/17
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.simulation.my"})
@EnableAspectJAutoProxy
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
