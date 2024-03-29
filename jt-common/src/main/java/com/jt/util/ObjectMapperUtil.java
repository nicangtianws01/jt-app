package com.jt.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	private final static ObjectMapper MAPPER = new ObjectMapper();
	public static String toJSON(Object target) {
		String result = null;
		try {
			result = MAPPER.writeValueAsString(target);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public static <T> T toObject(String json,Class<T> targetClass) {
		T object = null;
		try {
			object = MAPPER.readValue(json, targetClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return object;
	}
	
}
