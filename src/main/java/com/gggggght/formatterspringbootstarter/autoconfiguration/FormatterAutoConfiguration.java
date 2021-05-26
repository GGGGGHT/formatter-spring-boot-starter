package com.gggggght.formatterspringbootstarter.autoconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gggggght.formatterspringbootstarter.service.Formatter;
import com.gggggght.formatterspringbootstarter.service.JsonFormatter;
import com.gggggght.formatterspringbootstarter.service.impl.DefaultFormatter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当ObjectMapper类不存在时 会自动注入DefaultFormatter
 * 当ObjectMapper类存在时但Bean不存在时 会自动注入JsonFormatter beanName: jsonFormatter
 * 当ObjectMapper类存在时且bean存在 会自动注入JsonFormatter beanName: objectMapperFormatter
 */
@Configuration
public class FormatterAutoConfiguration {
	/**
	 * 构建 {@link DefaultFormatter} Bean
	 *
	 * @return {@link DefaultFormatter}
	 */
	@Bean
	@ConditionalOnMissingClass(value = "com.fasterxml.jackson.databind.ObjectMapper")
	public Formatter defaultFormatter() {
		return new DefaultFormatter();
	}

	/**
	 * JSON 格式 {@link Formatter} Bean
	 *
	 * @return {@link JsonFormatter}
	 */
	@Bean
	@ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
	@ConditionalOnMissingBean(type = "com.fasterxml.jackson.databind.ObjectMapper")
	public Formatter jsonFormatter() {
		return new JsonFormatter();
	}

	@Bean
	@ConditionalOnClass(name = "com.fasterxml.jackson.databind.ObjectMapper")
	@ConditionalOnBean(type = "com.fasterxml.jackson.databind.ObjectMapper")
	public Formatter objectMapperFormatter(ObjectMapper jacksonObjectMapper) {
		return new JsonFormatter(jacksonObjectMapper);
	}
}

