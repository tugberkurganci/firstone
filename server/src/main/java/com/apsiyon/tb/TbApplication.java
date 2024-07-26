package com.apsiyon.tb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.apsiyon.tb.repositories")
@EntityScan(basePackages = "com.apsiyon.tb.entities")
public class TbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TbApplication.class, args);
	}

}
