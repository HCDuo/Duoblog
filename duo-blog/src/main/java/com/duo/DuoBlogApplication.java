package com.duo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/7/26 1:49
 */

@SpringBootApplication
@MapperScan("com.duo.mapper")
public class DuoBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(DuoBlogApplication.class,args);
    }
}
