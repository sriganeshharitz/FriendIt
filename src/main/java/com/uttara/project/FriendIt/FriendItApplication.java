package com.uttara.project.FriendIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.util.Set;
import java.util.TreeSet;

@SpringBootApplication
public class FriendItApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendItApplication.class, args);
	}
//	@Bean
//	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher(){
//		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
//	}
}
