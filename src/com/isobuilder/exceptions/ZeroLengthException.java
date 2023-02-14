package com.isobuilder.exceptions;

/**
 * Exception raised when a value used in a VARIABLE format has length equal to ZERO. 
 * 
 * @author Federico Alaimo
 *
 */

public class ZeroLengthException extends Exception {

	private static final long serialVersionUID = 1L;

	public ZeroLengthException(String arg0) {
		super(arg0);
	}

}
