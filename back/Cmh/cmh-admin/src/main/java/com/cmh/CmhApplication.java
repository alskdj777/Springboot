package com.cmh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author cmh
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class CmhApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CmhApplication.class, args);
        System.out.println("测盟汇启动成功\n");
    }
}
