package com.gggggght.formatterspringbootstarter.autoconfiguration;

import com.gggggght.formatterspringbootstarter.service.Formatter;
import com.gggggght.formatterspringbootstarter.service.impl.DefaultFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterAutoConfiguration {
	@Bean
	public Formatter defaultFormatter() {
		return new DefaultFormatter();
	}
}

