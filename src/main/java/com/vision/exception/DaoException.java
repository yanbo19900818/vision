package com.vision.exception;

public class DaoException extends RuntimeException {
	public DaoException(String message) {
		super(message);
	}

	public DaoException(String message, Exception e) {
		super(message, e);
	}
}
