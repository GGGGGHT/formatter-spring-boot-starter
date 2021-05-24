package com.gggggght.formatterspringbootstarter.service.impl;

import com.gggggght.formatterspringbootstarter.service.Formatter;

public class DefaultFormatter implements Formatter {
	@Override
	public String format(Object object) {
		return String.valueOf(object);
	}
}
