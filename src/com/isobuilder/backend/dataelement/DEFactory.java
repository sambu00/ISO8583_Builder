package com.isobuilder.backend.dataelement;

import com.isobuilder.backend.format.DEFormatEnum;
import com.isobuilder.backend.format.FormatStaticMap;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * Factory class to generate data elements
 * 
 * @author Federico Alaimo
 *
 */
public final class DEFactory {

	/**
	 * 
	 */
	public DEFactory() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Generate a data element using the position and the value provided
	 * 
	 * @param position
	 * @param value
	 * @return
	 * @throws InvalidDataElementException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException 
	 */
	public static DataElement generateDataElement(int position, String value)
			throws InvalidDataElementException, MaximumLengthExceededException,
			PatternException, FixedLengthNotHonoredException, ZeroLengthException {

		if (FormatStaticMap.getDeFormat(position).getFormat() == DEFormatEnum.FIXED) {
			return new FixedDE(position, value);
		} else {
			return new VariableDE(position, value);
		}

	}

	/**
	 * Generate a FIXED format data element using the position and the value provided
	 * 
	 * @param position
	 * @param value
	 * @return
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException 
	 * @throws ZeroLengthException 
	 */
	public static DataElement generateFixedDataElement(int position, String value)
			throws MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException, ZeroLengthException {
		return new FixedDE(position, value);
	}

	/**
	 * Generate a VARIABLE format data element using the position and the value provided
	 * 
	 * @param value
	 * @param deFormat
	 * @return
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws InvalidDataElementException 
	 * @throws FixedLengthNotHonoredException 
	 * @throws ZeroLengthException 
	 */
	public static DataElement generateVariableDataElement(int position, String value)
			throws MaximumLengthExceededException, PatternException, InvalidDataElementException, FixedLengthNotHonoredException, ZeroLengthException {
		return new VariableDE(position, value);
	}

}
