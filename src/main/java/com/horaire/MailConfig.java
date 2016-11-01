package com.horaire;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan(basePackages={"com.horaire.registration"})
@PropertySource("classpath:email.properties")
public class MailConfig {
	
	@Autowired
	Environment environment ;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer(){
		
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	
	
	@Bean
	public JavaMailSenderImpl javaMailSenderImpl(){
		
		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
		mailSenderImpl.setHost(environment.getProperty("smtp.host"));
		mailSenderImpl.setPort(environment.getProperty("smtp.port",Integer.class));
		mailSenderImpl.setProtocol(environment.getProperty("smtp.protocol"));
		mailSenderImpl.setUsername(environment.getProperty("smtp.username"));
		mailSenderImpl.setPassword(environment.getProperty("smtp.password"));
		
		final Properties javaMailProps = new Properties();
		javaMailProps.put("mail.smtp.auth", true);
		javaMailProps.put("mail.smtp.starttls.enable", true);
		
		mailSenderImpl.setJavaMailProperties(javaMailProps);
		
		return mailSenderImpl;
	}

}
