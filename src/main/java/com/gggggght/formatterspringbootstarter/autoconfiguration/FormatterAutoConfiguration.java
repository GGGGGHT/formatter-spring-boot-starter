package com.gggggght.formatterspringbootstarter.autoconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gggggght.formatterspringbootstarter.service.Formatter;
import com.gggggght.formatterspringbootstarter.service.JsonFormatter;
import com.gggggght.formatterspringbootstarter.service.impl.DefaultFormatter;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当ObjectMapper类不存在时 会自动注入DefaultFormatter
 * 当ObjectMapper类存在时但Bean不存在时 会自动注入JsonFormatter beanName: jsonFormatter
 * 当ObjectMapper类存在时且bean存在 会自动注入JsonFormatter beanName: objectMapperFormatter
 */
@Configuration
// 当系统属性formatter.enabled为true时，才开始自动装配
@ConditionalOnProperty(prefix = "formatter", name = "enabled", havingValue = "true",matchIfMissing = true)
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
	@ConditionalOnClass(value = ObjectMapper.class)
	@ConditionalOnMissingBean(name = "com.fasterxml.jackson.databind.ObjectMapper")
	public Formatter jsonFormatter() {
		return new JsonFormatter();
	}

	@Bean
	@ConditionalOnClass(value = ObjectMapper.class)
	@ConditionalOnBean(type = "com.fasterxml.jackson.databind.ObjectMapper")
	public Formatter objectMapperFormatter(ObjectMapper jacksonObjectMapper) {
		return new JsonFormatter(jacksonObjectMapper);
	}
}

