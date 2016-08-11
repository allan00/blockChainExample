package com.webank.blockchain.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

@SpringBootApplication
public class App2 implements EmbeddedServletContainerCustomizer{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
        return builder.sources(App2.class);
    } 

	public static void main(String[] args) {
		SpringApplication.run(App2.class, args);
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		container.setPort(8081);
	}

}