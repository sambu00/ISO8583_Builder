package com.isobuilder.backend.dataelement;

import com.isobuilder.backend.format.DEVariableFormat;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * Variable format data element class. The length of the value of a variable
 * data element MUST be greater than zero and MUST NOT exceed the maximum
 * length.
 * 
 * @author Federico Alaimo
 *
 */
public class VariableDE extends DataElement {

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
	public VariableDE(int position, String value)
			throws InvalidDataElementException, MaximumLengthExceededException,
			PatternException, FixedLengthNotHonoredException, ZeroLengthException {
		super(position, value);
		// TODO Auto-generated constructor stub
	}

	/**
	 * data element value getter.
	 * Overrided to provide the effective length of the value
	 * 
	 */
	@Override
	public String getValue() {
		String valueLength = Integer.toString(MessageElementValue.valueLength(super.getPlainValue()));

		DEVariableFormat fmt = (DEVariableFormat) getDeFormat();
		while (fmt.getLengthBytes() - valueLength.length() > 0) {
			valueLength = "0" + valueLength;
		}

		return valueLength + super.getPlainValue();
	}
	
}
