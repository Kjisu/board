package com.zerock.viewerboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ViewerboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ViewerboardApplication.class, args);
	}

}
