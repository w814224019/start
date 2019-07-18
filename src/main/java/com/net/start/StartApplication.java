package com.net.start;


import java.io.IOException;
import java.util.Timer;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.net.start.controller.TimerTask;



@SpringBootApplication
@MapperScan("com.net.start.mapper")
public class StartApplication {
	private static Logger log = LoggerFactory.getLogger(StartApplication.class);
	public static void main(String[] args) throws IOException {
		SpringApplication.run(StartApplication.class, args);
		log.info("------------成功启动springboot-----------");
		Timer timer = new Timer();
        System.out.println("创建");
timer.schedule(new TimerTask(),0,5*1000);//每隔5秒将一个新点的坐标写入txt文件
		
		
		
		
		
		
	}

}
