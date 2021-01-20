package com.xiebl.common;

/**
 * 正常业务异常
 * 
 * @author train
 *
 */
public class NormalBusiException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -337750267174042596L;

	public NormalBusiException(String message) {
		super(message);
	}

	public NormalBusiException(String message, Throwable cause) {
		super(message, cause);
	}

}
