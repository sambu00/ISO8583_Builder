package com.isobuilder.exceptions;

/**
 * Exception raised when a the data element requested is not included in the
 * final class FormatStaticMap
 * 
 * @author Federico Alaimo
 *
 */
public class InvalidDataElementException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDataElementException(String arg0) {
		super(arg0);
	}

}
