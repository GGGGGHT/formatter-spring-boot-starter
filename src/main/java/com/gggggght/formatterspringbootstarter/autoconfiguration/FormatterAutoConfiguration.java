package com.gggggght.formatterspringbootstarter.autoconfiguration;

import com.gggggght.formatterspringbootstarter.service.Formatter;
import com.gggggght.formatterspringbootstarter.service.JsonFormatter;
import com.gggggght.formatterspringbootstarter.service.impl.DefaultFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterAutoConfiguration {
	@Bean
	@ConditionalOnMissingClass(value = "com.fasterxml.jackson.databind.ObjectMapper")
	public Formatter defaultFormatter() {
		return new DefaultFormatter();
	}

	@Bean
	@ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
	public Formatter jsonFormatter() {
		return new JsonFormatter();
	}
}

