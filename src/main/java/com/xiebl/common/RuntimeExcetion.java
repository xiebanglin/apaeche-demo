package com.xiebl.common;

/**
 * 
 * @author train
 *
 */
public class RuntimeExcetion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7780533087852779103L;

	public RuntimeExcetion(String message) {
		super(message);
	}

	public RuntimeExcetion(String message, Throwable cause) {
		super(message, cause);
	}
}
