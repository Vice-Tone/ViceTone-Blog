package com.hohai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jin
 */
@MapperScan("com.hohai.dao")
@SpringBootApplication
public class VicetoneBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VicetoneBlogApplication.class, args);
    }
//    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

}
