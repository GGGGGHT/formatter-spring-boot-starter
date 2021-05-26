package com.gggggght.formatterspringbootstarter.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatter implements Formatter {
	private final ObjectMapper objectMapper;

	public JsonFormatter() {
		this(new ObjectMapper());
	}

	public JsonFormatter(ObjectMapper mapper) {this.objectMapper = mapper;}


	@Override
	public String format(Object object) {

		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
