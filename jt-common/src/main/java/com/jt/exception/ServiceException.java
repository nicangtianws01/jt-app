package com.jt.exception;


public class ServiceException extends RuntimeException{
	private static final long serialVersionUID = 9046532718905798474L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
}
