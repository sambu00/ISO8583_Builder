package com.isobuilder.exceptions;

/**
 * Exception raised when a data element value does not match the value pattern
 * of the format
 * 
 * @author Federico Alaimo
 *
 */
public class PatternException extends Exception {
	private static final long serialVersionUID = 1L;

	public PatternException(String arg0) {
		super(arg0);
	}

}
