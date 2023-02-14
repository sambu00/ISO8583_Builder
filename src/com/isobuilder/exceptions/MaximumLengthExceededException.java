package com.isobuilder.exceptions;

/**
 * Exception raised when a data element value exceeds the maximum length of the
 * data element format
 * 
 * @author Federico Alaimo
 *
 */
public class MaximumLengthExceededException extends Exception {

	private static final long serialVersionUID = 1L;

	public MaximumLengthExceededException(String arg0) {
		super(arg0);
	}

}
