package br.senai.sp.info.pweb.jucacontrol.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("br.senai.sp.info.pweb.jucacontrol")
@Import(PersistenceConfig.class)
public class WebConfig {

	
	
}