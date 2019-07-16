package com.ocean;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ocean
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.ocean.mapper")
public class OceanssmApplication extends SpringBootServletInitializer {
	public static void main (String[] args) {
		SpringApplication.run(OceanssmApplication.class, args);
	}



}
