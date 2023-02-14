package com.isobuilder.backend.dataelement;

import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * Fixed format data element class.
 * The value of a fixed data element must have the exact length 
 * 
 * @author Federico Alaimo
 *
 */
public class FixedDE extends DataElement {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor using parameters position and value provided
	 * 
	 * @param position
	 * @param value
	 * @throws InvalidDataElementException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException 
	 */
	public FixedDE(int position, String value) throws InvalidDataElementException, MaximumLengthExceededException, PatternException, FixedLengthNotHonoredException, ZeroLengthException {
		super(position, value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return super.getPlainValue();
	}

}
