package com.uisrael.proyectoconsumo.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConsumoConfig {
	@Bean

	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
