package org.musibondi.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.musibondi.events")
public class SpringBootWebApplication {

	static public void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);

	}

}