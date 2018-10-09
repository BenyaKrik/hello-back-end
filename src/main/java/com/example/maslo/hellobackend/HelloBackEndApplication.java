package com.example.maslo.hellobackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HelloBackEndApplication {
	private static final Logger logger = LoggerFactory.getLogger(HelloBackEndApplication.class);

	public static void main(String[] args) {
	SpringApplication.run(HelloBackEndApplication.class, args);
	 }
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource);
		return validatorFactoryBean;
	}
}
