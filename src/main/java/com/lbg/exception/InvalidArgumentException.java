package com.lbg.exception;

public class InvalidArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidArgumentException(String errorMsg) {
		super(errorMsg);
	}

}
