package com.springframework.boot.embeddedinfinispan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;

@SpringBootApplication
@Cacheable
public class EmbeddedInfinispanApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmbeddedInfinispanApplication.class, args);
	}

}
