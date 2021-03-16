package com.doemais.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
	public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("https://localhost:80",
						"http://localhost:80",
						"http://localhost:3000",
						"https://localhost:3000",
						"https://ec2-54-207-207-110.sa-east-1.compute.amazonaws.com:80",
						"http://ec2-54-207-207-110.sa-east-1.compute.amazonaws.com:80",
						"https://ec2-54-207-207-110.sa-east-1.compute.amazonaws.com:5000",
						"http://ec2-54-207-207-110.sa-east-1.compute.amazonaws.com:5000",
						"*")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
}
}
