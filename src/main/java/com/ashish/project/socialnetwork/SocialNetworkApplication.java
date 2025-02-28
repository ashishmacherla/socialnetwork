package com.ashish.project.socialnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.ashish.project.socialnetwork")
@EnableJpaRepositories(basePackages = "com.ashish.project.socialnetwork.repository")
@EntityScan(basePackages = "com.ashish.project.socialnetwork.repository")
public class SocialNetworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args);
	}

}
