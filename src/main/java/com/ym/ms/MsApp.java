package com.ym.ms;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 文件微服务
 *
 */
@SpringBootApplication
@ServletComponentScan
@EnableAutoConfiguration
public class MsApp
{
    public static void main( String[] args )
    {
    	SpringApplication app = new SpringApplication(MsApp.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		System.err.println("started");
    }
}
