package com.bookstore.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;

@ResponseStatus
@Data
public class OrderServiceException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private int StatusCode;
	private String StatusMessage;
	
	public OrderServiceException(int statusCode, String statusMessage) {
		super();
		StatusCode = statusCode;
		StatusMessage = statusMessage;
	}
}
