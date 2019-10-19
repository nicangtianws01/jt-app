package com.jt.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jt.vo.SysResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler{
	@ExceptionHandler(RuntimeException.class)
	public SysResult doHandleRuntimeException(RuntimeException exception) {
		log.error(exception.getMessage());
		exception.printStackTrace();
		return new SysResult(exception);
	}
}
