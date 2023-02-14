package com.isobuilder.exceptions;

/**
 * Exception raised when a value used in a FIXED format has the wrong length 
 * 
 * @author Federico Alaimo
 *
 */
public class FixedLengthNotHonoredException extends Exception {

	private static final long serialVersionUID = 1L;

	public FixedLengthNotHonoredException(String arg0) {
		super(arg0);
	}
}
