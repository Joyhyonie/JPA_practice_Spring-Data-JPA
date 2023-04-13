package com.greedy.practice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

	/* ModelMapper라이브러리 추가 후, ModelMapper객체를 사용하기 위해 Bean 등록 */
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}
}


