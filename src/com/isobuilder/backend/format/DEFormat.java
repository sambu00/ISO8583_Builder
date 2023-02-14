package com.isobuilder.backend.format;

import java.io.Serializable;

import com.isobuilder.backend.dataelement.MessageElementValue;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * Class used to represent a data element format. This is an abstract class
 * since the format can be FIXED or VARIABLE: DEFixedFormat and DEVariableFormat
 * are the implementig classes.
 * 
 * DEFormat class has the following attributes: maxLength: maximum length of the
 * data element value valuePattern: pattern of the value (described by
 * ValuePatternEnum)
 * 
 * @author Federico Alaimo
 *
 */
public abstract class DEFormat implements Serializable {

	private static final long serialVersionUID = 1L;
	private int maxLength;
	private ValuePatternEnum vp;

	/**
	 * Class Constructor.
	 * 
	 * @param vp
	 * @param maxLength
	 */
	public DEFormat(ValuePatternEnum vp, int maxLength) {
		this.maxLength = maxLength;
		this.vp = vp;
		// format = null;
	}

	/**
	 * max length getter
	 * 
	 * @return format max length
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * max length setter
	 * 
	 * @param maxLength
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * value pattern getter
	 * 
	 * @return value pattern
	 */
	public ValuePatternEnum getVp() {
		return vp;
	}

	/**
	 * value pattern setter
	 * 
	 * @param vp
	 */
	public void setVp(ValuePatternEnum vp) {
		this.vp = vp;
	}

	/**
	 * format getter (implemented in subclasses)
	 * 
	 * @return format
	 */
	public abstract DEFormatEnum getFormat();

	/**
	 * Method used to validate the data element value against the format rules.
	 * 
	 * @param value
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException 
	 */
	public void validate(String value) throws MaximumLengthExceededException,
			PatternException, FixedLengthNotHonoredException, ZeroLengthException {

		checkMaxLength(MessageElementValue.valueLength(value));
		checkPattern(value);
	}

	/**
	 * Method used to ensure that data element value length not exceeds the max
	 * length defined in the format
	 * 
	 * @param value
	 * @throws MaximumLengthExceededException
	 */
	private void checkMaxLength(int length)
			throws MaximumLengthExceededException {
		if (length > maxLength) {
			throw new MaximumLengthExceededException(
					"value length greater than maximum allowed. value length: "
							+ length + " max: " + maxLength);
		}
	}

	/**
	 * Method used to ensure that data element value has the right pattern
	 * 
	 * @param value
	 * @throws PatternException
	 */
	private void checkPattern(String value) throws PatternException {
		switch (vp) {
		case STRING:
			checkSTRING(value);
			break;
		case NUMERIC:
			checkNUMERIC(value);
			break;
		}

	}

	/**
	 * Check for STRING pattern
	 * 
	 * @param value
	 * @throws PatternException
	 */
	private void checkSTRING(String value) throws PatternException {
		boolean checkTBD = false;
		if (checkTBD) {
			throw new PatternException("value not compatible with pattern " + vp);
		}
	}

	/**
	 * Check for NUMERIC pattern
	 * 
	 * @param value
	 * @throws PatternException
	 */
	private void checkNUMERIC(String value) throws PatternException {
		char[] charSequence = value.toCharArray();

		boolean digitCehckFailed = false;

		for (int i = 0; i < charSequence.length; i++) {
			if (!Character.isDigit(charSequence[i])) {
				digitCehckFailed = true;
				break;
			}
		}

		if (digitCehckFailed) {
			throw new PatternException("value not compatible with pattern " + vp);
		}
	}

}
