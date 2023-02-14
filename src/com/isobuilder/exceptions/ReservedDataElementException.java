package com.isobuilder.exceptions;

/**
 * Exception raised when a reserved an internally managed data elment is used by
 * the user
 * 
 * @author Federico Alaimo
 *
 */
public class ReservedDataElementException extends Exception {

	private static final long serialVersionUID = 1L;

	public ReservedDataElementException(String arg0) {
		super(arg0);
	}

}
