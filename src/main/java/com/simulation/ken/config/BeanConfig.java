package com.simulation.ken.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean(name = "banApis")
	public List<String> banApis() {
		List<String> banApis = new ArrayList<String>();
		banApis.add("/ken/test");
		return banApis;
	}
}
