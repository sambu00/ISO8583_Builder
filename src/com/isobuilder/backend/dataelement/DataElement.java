package com.isobuilder.backend.dataelement;

import java.io.Serializable;

import com.isobuilder.backend.format.DEFormat;
import com.isobuilder.backend.format.FormatStaticMap;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * DataElement class is used to represent a data element of the ISO message. a
 * data element includes a position, a format and a value.
 * 
 * @author Federico Alaimo
 *
 */
public abstract class DataElement implements Serializable {

	private static final long serialVersionUID = 1L;
	private int position;
	private DEFormat deFormat;
	private String value;

	/**
	 * Constructor using position and value parameters.
	 * 
	 * @param value
	 * @param deFormat
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException 
	 */
	public DataElement(int position, String value)
			throws InvalidDataElementException, MaximumLengthExceededException,
			PatternException, FixedLengthNotHonoredException, ZeroLengthException {
		this.position = position;
		this.deFormat = FormatStaticMap.getDeFormat(position);
		deFormat.validate(value);
		this.value = value;
	}

	/**
	 * data element abstact value getter
	 * 
	 * @return data element value
	 */
	public abstract String getValue();
	
	/**
	 * data element value getter
	 * 
	 * @return data element value
	 */
	public String getPlainValue() {
		return value;
	}

	/**
	 * data element value setter
	 * 
	 * @param value
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException 
	 */
	public void setValue(String value) throws MaximumLengthExceededException,
			PatternException, FixedLengthNotHonoredException, ZeroLengthException {
		deFormat.validate(value);
		this.value = value;
	}

	/**
	 * data element format getter
	 * 
	 * @return data element format
	 */
	public DEFormat getDeFormat() {
		return deFormat;
	}

	/**
	 * data element format setter
	 * 
	 * @param deFormat
	 */
	public void setDeFormat(DEFormat deFormat) {
		this.deFormat = deFormat;
	}

	/**
	 * data element position getter
	 * 
	 * @return data element position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * data element position setter
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

}