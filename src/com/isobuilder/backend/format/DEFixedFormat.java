package com.isobuilder.backend.format;

import com.isobuilder.backend.dataelement.MessageElementValue;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * Class to represent a FIXED format for the data element
 * 
 * @author Federico Alaimo
 *
 */
public class DEFixedFormat extends DEFormat {

	private static final long serialVersionUID = 1L;
	private DEFormatEnum format;

	/**
	 * Class Contructor
	 * 
	 * @param vp
	 * @param maxLength
	 */
	public DEFixedFormat(ValuePatternEnum vp, int maxLength) {
		super(vp, maxLength);
		format = DEFormatEnum.FIXED;
	}

	/**
	 * format getter
	 */
	@Override
	public DEFormatEnum getFormat() {
		return format;
	}

	/**
	 * FIXED format ensure that the data element value length MUST be equal to
	 * max length.
	 * @throws ZeroLengthException 
	 * 
	 */
	@Override
	public void validate(String value) throws MaximumLengthExceededException,
			PatternException, FixedLengthNotHonoredException, ZeroLengthException {
		super.validate(value);
		checkFixedLength(MessageElementValue.valueLength(value));
	}

	/**
	 * Check for FIXED length
	 * 
	 * @param length
	 * @throws FixedLengthNotHonoredException
	 */
	private void checkFixedLength(int length)
			throws FixedLengthNotHonoredException {
		if (length < getMaxLength()) {
			throw new FixedLengthNotHonoredException("value length: " + length
					+ ", fixed length required: " + getMaxLength());
		}
	}

}
