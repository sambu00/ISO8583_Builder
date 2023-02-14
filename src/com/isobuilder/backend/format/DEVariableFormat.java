package com.isobuilder.backend.format;

import com.isobuilder.backend.dataelement.MessageElementValue;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * Class to represent a VARIABLE format for the data element. The VARIABLE
 * format can contain a variable length value, so a numeric string is placed
 * before the effective value to show the real length of the subsequent data.
 * The length of this numeric field is stored in lengthBytes.
 * 
 * @author Federico Alaimo
 *
 */
public class DEVariableFormat extends DEFormat {

	private static final long serialVersionUID = 1L;
	private DEFormatEnum format;
	private int lengthBytes;

	/**
	 * Class Constructor
	 * 
	 * @param vp
	 * @param maxLength
	 * @param LengthBytes
	 */
	public DEVariableFormat(ValuePatternEnum vp, int maxLength, int lengthBytes) {
		super(vp, maxLength);
		this.lengthBytes = lengthBytes;
		format = DEFormatEnum.VARIABLE;
	}

	/**
	 * lengthBytes getter
	 * 
	 * @return lengthBytes
	 */
	public int getLengthBytes() {
		return lengthBytes;
	}

	/**
	 * lengthBytes setter
	 * 
	 * @param lengthBytes
	 */
	public void setLengthBytes(int lengthBytes) {
		this.lengthBytes = lengthBytes;
	}

	/**
	 * format getter
	 */
	@Override
	public DEFormatEnum getFormat() {
		return format;
	}

	/**
	 * VARIABLE format ensure that the data element value length MUST be greater
	 * than zero.
	 * @throws FixedLengthNotHonoredException 
	 * @throws PatternException 
	 * @throws MaximumLengthExceededException 
	 * @throws ZeroLengthException 
	 * 
	 */
	@Override
	public void validate(String value) throws MaximumLengthExceededException, PatternException, FixedLengthNotHonoredException, ZeroLengthException {
		super.validate(value);
		checkZeroLength(MessageElementValue.valueLength(value));
	}
	
	
	private void checkZeroLength(int length) throws ZeroLengthException {
		if (length == 0) {
			throw new ZeroLengthException("value length MUST be greater than zero");
		}
	}
}
