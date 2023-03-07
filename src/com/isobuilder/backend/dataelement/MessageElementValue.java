package com.isobuilder.backend.dataelement;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * * The value stored as attribute in DataElement class can be defined as
 * 'plain'. This value can contain binary quantities. The binary quantities are
 * exposed in a simil-xml format as follows: <.hex>0123456789ABCDEF</.hex>
 * 
 * This final class to manage the data element value. It takes care of the
 * binary quantities and calculates the effective length accordingly.
 * 
 * Moreover this class is used to convert a 'plain value' to an 'extended
 * value': each character in 'plain value' is represented by 2 characters in
 * 'extended value' that are the HEXADECIMAL conversion of the original one.
 * 
 * 
 * @author Federico Alaimo
 *
 */
public final class MessageElementValue {

	public static final String HEX_TAG_OPEN = "<.hex>";
	public static final String HEX_TAG_CLOSE = "</.hex>";

	private static final String REGEX = "(?<befHex>.*)<\\.hex>(?<effHex>.*)<\\/\\.hex>(?<aftHex>.*)";
	private static final String BEF_HEX_GROUP = "befHex";
	private static final String EFF_HEX_GROUP = "effHex";
	private static final String AFT_HEX_GROUP = "aftHex";

	/**
	 * return effective length (compressing hex data)
	 * 
	 * @param value
	 * @return effective value length
	 */
	public static int valueLength(String value) {
		if (value.contains(HEX_TAG_OPEN)) {
			Pattern pattern = Pattern.compile(REGEX);
			Matcher matcher = pattern.matcher(value);

			int incrementalLength = 0;
			int befHexLen = 0;

			// recursively apply regex to find LAST HEX_DATA
			while (matcher.find()) {

				// add string length after HEX DATA
				incrementalLength += matcher.group(AFT_HEX_GROUP).length();
				// add HEX DATA string length / 2
				incrementalLength += matcher.group(EFF_HEX_GROUP).length() / 2;

				// save string length before HEX DATA
				befHexLen = matcher.group(BEF_HEX_GROUP).length();

				// apply regex to string before HEX DATA
				matcher = pattern.matcher(matcher.group(BEF_HEX_GROUP));
			}

			// add string length before HEX DATA (saved before)
			incrementalLength += befHexLen;

			return incrementalLength;

		} else {
			return value.length();
		}
	}

	/**
	 * return extended value expanding HEX DATA
	 * 
	 * @return extended value
	 */
	public static String extendedValue(String value) {
		String extendedValue = "";

		if (value.contains(HEX_TAG_OPEN)) {
			Pattern pattern = Pattern.compile(REGEX);
			Matcher matcher = pattern.matcher(value);

			String befHexString = "";

			// recursively apply regex to find LAST HEX_DATA
			while (matcher.find()) {

				// add string extended after HEX DATA
				extendedValue = extend(matcher.group(AFT_HEX_GROUP).getBytes())
						+ extendedValue;

				// add HEX DATA string
				extendedValue = matcher.group(EFF_HEX_GROUP) + extendedValue;

				// save string extended before HEX DATA
				befHexString = extend(matcher.group(BEF_HEX_GROUP).getBytes());

				// apply regex to string before HEX DATA
				matcher = pattern.matcher(matcher.group(BEF_HEX_GROUP));
			}

			// add string extended before HEX DATA (saved before)
			extendedValue = befHexString + extendedValue;

		} else {
			extendedValue = extend(value.getBytes());
		}

		return extendedValue;
	}

	/**
	 * return extended value expanding HEX DATA, converted in charsetName
	 * 
	 * @param value
	 * @param charsetName
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String extendedValue(String value, String charsetName)
			throws UnsupportedEncodingException {
		String extendedValue = "";

		if (value.contains(HEX_TAG_OPEN)) {
			Pattern pattern = Pattern.compile(REGEX);
			Matcher matcher = pattern.matcher(value);

			String befHexString = "";

			// recursively apply regex to find LAST HEX_DATA
			while (matcher.find()) {

				// add string extended after HEX DATA
				String origString = matcher.group(AFT_HEX_GROUP);
				extendedValue = extend(origString.getBytes(charsetName)) + extendedValue;

				// add HEX DATA string
				extendedValue = matcher.group(EFF_HEX_GROUP) + extendedValue;

				// save string extended before HEX DATA
				origString = matcher.group(BEF_HEX_GROUP);
				befHexString = extend(origString.getBytes(charsetName));

				// apply regex to string before HEX DATA
				matcher = pattern.matcher(matcher.group(BEF_HEX_GROUP));
			}

			// add string extended before HEX DATA (saved before)
			extendedValue = befHexString + extendedValue;

		} else {
			extendedValue = extend(value.getBytes(charsetName));
		}

		return extendedValue;
	}

	/**
	 * return a String removing the smil-xml tags used to identify a binary
	 * quantity (<.hex> </.hex>)
	 * 
	 * @param str
	 * @return
	 */
	public static String stripHexTags(String str) {
		return str.replace(HEX_TAG_OPEN, "").replace(HEX_TAG_CLOSE, "");
	}

	/**
	 * return the extended value for the provided input string. For each
	 * character of originalValue, in output is provided the relative
	 * HEXADECIMAL value
	 * 
	 * @param originalValue
	 * @return
	 */
	private static String extend(byte[] byteSequence) {

		String extended = "";

		for (int i = 0; i < byteSequence.length; i++) {
			String byteHexValue = Integer.toHexString(Byte.toUnsignedInt(byteSequence[i])).toUpperCase();

			extended += byteHexValue;
		}

		return extended;
	}
}
