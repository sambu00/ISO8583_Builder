package com.isobuilder.backend.format;

import java.util.HashMap;
import java.util.Map;

import com.isobuilder.exceptions.InvalidDataElementException;

/**
 * Class used to describe each ISO 8583 data element format and pattern
 * 
 * @author Federico Alaimo
 *
 */
public final class FormatStaticMap {

	private static final Map<Integer, DEFormat> FORMAT_MAP = new HashMap<>();
	static {
		FORMAT_MAP.put(1, new DEFixedFormat(ValuePatternEnum.STRING, 8)); // <---bitmap
		FORMAT_MAP
				.put(2, new DEVariableFormat(ValuePatternEnum.NUMERIC, 19, 2));
		FORMAT_MAP.put(3, new DEFixedFormat(ValuePatternEnum.NUMERIC, 6));
		FORMAT_MAP.put(4, new DEFixedFormat(ValuePatternEnum.NUMERIC, 12));
		FORMAT_MAP.put(5, new DEFixedFormat(ValuePatternEnum.NUMERIC, 12));
		FORMAT_MAP.put(6, new DEFixedFormat(ValuePatternEnum.NUMERIC, 12));
		FORMAT_MAP.put(7, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(8, new DEFixedFormat(ValuePatternEnum.NUMERIC, 8));

		FORMAT_MAP.put(9, new DEFixedFormat(ValuePatternEnum.NUMERIC, 8));
		FORMAT_MAP.put(10, new DEFixedFormat(ValuePatternEnum.NUMERIC, 8));
		FORMAT_MAP.put(11, new DEFixedFormat(ValuePatternEnum.NUMERIC, 6));
		FORMAT_MAP.put(12, new DEFixedFormat(ValuePatternEnum.NUMERIC, 6));
		FORMAT_MAP.put(13, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));
		FORMAT_MAP.put(14, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));
		FORMAT_MAP.put(15, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));
		FORMAT_MAP.put(16, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));

		FORMAT_MAP.put(17, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));
		FORMAT_MAP.put(18, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));
		FORMAT_MAP.put(19, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(20, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(21, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(22, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(23, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(24, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));

		FORMAT_MAP.put(25, new DEFixedFormat(ValuePatternEnum.NUMERIC, 2));
		FORMAT_MAP.put(26, new DEFixedFormat(ValuePatternEnum.NUMERIC, 2));
		FORMAT_MAP.put(27, new DEFixedFormat(ValuePatternEnum.NUMERIC, 1));
		FORMAT_MAP.put(28, new DEFixedFormat(ValuePatternEnum.STRING, 9));
		FORMAT_MAP.put(29, new DEFixedFormat(ValuePatternEnum.STRING, 9));
		FORMAT_MAP.put(30, new DEFixedFormat(ValuePatternEnum.STRING, 9));
		FORMAT_MAP.put(31, new DEFixedFormat(ValuePatternEnum.STRING, 9));
		FORMAT_MAP.put(32,
				new DEVariableFormat(ValuePatternEnum.NUMERIC, 11, 2));

		FORMAT_MAP.put(33,
				new DEVariableFormat(ValuePatternEnum.NUMERIC, 11, 2));
		FORMAT_MAP.put(34,
				new DEVariableFormat(ValuePatternEnum.NUMERIC, 28, 2));
		FORMAT_MAP
				.put(35, new DEVariableFormat(ValuePatternEnum.STRING, 37, 2));
		FORMAT_MAP.put(36,
				new DEVariableFormat(ValuePatternEnum.STRING, 104, 3));
		FORMAT_MAP.put(37, new DEFixedFormat(ValuePatternEnum.STRING, 12));
		FORMAT_MAP.put(38, new DEFixedFormat(ValuePatternEnum.STRING, 6));
		FORMAT_MAP.put(39, new DEFixedFormat(ValuePatternEnum.STRING, 2));
		FORMAT_MAP.put(40, new DEFixedFormat(ValuePatternEnum.STRING, 3));

		FORMAT_MAP.put(41, new DEFixedFormat(ValuePatternEnum.STRING, 8));
		FORMAT_MAP.put(42, new DEFixedFormat(ValuePatternEnum.STRING, 15));
		FORMAT_MAP.put(43, new DEFixedFormat(ValuePatternEnum.STRING, 40));
		FORMAT_MAP
				.put(44, new DEVariableFormat(ValuePatternEnum.STRING, 25, 2));
		FORMAT_MAP
				.put(45, new DEVariableFormat(ValuePatternEnum.STRING, 76, 2));
		FORMAT_MAP.put(46,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(47,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(48,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));

		FORMAT_MAP.put(49, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(50, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(51, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(52, new DEFixedFormat(ValuePatternEnum.STRING, 8));
		FORMAT_MAP.put(53, new DEFixedFormat(ValuePatternEnum.NUMERIC, 16));
		FORMAT_MAP.put(54,
				new DEVariableFormat(ValuePatternEnum.STRING, 120, 3));
		FORMAT_MAP.put(55,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(56,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));

		FORMAT_MAP.put(57,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(58,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(59,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(60,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(61,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(62,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(63,
				new DEVariableFormat(ValuePatternEnum.STRING, 999, 3));
		FORMAT_MAP.put(64, new DEFixedFormat(ValuePatternEnum.STRING, 8));

		FORMAT_MAP.put(65, new DEFixedFormat(ValuePatternEnum.STRING, 8));
		FORMAT_MAP.put(66, new DEFixedFormat(ValuePatternEnum.NUMERIC, 1));
		FORMAT_MAP.put(67, new DEFixedFormat(ValuePatternEnum.NUMERIC, 2));
		FORMAT_MAP.put(68, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(69, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(70, new DEFixedFormat(ValuePatternEnum.NUMERIC, 3));
		FORMAT_MAP.put(71, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));
		FORMAT_MAP.put(72, new DEFixedFormat(ValuePatternEnum.NUMERIC, 4));

		FORMAT_MAP.put(73, new DEFixedFormat(ValuePatternEnum.NUMERIC, 6));
		FORMAT_MAP.put(74, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(75, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(76, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(77, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(78, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(79, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(80, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));

		FORMAT_MAP.put(81, new DEFixedFormat(ValuePatternEnum.NUMERIC, 10));
		FORMAT_MAP.put(82, new DEFixedFormat(ValuePatternEnum.NUMERIC, 12));
		FORMAT_MAP.put(83, new DEFixedFormat(ValuePatternEnum.NUMERIC, 12));
		FORMAT_MAP.put(84, new DEFixedFormat(ValuePatternEnum.NUMERIC, 12));
		FORMAT_MAP.put(85, new DEFixedFormat(ValuePatternEnum.NUMERIC, 12));
		FORMAT_MAP.put(86, new DEFixedFormat(ValuePatternEnum.NUMERIC, 16));
		FORMAT_MAP.put(87, new DEFixedFormat(ValuePatternEnum.NUMERIC, 16));
		FORMAT_MAP.put(88, new DEFixedFormat(ValuePatternEnum.NUMERIC, 16));

		FORMAT_MAP.put(89, new DEFixedFormat(ValuePatternEnum.NUMERIC, 16));
		FORMAT_MAP.put(90, new DEFixedFormat(ValuePatternEnum.NUMERIC, 42));
		FORMAT_MAP.put(91, new DEFixedFormat(ValuePatternEnum.STRING, 1));
		FORMAT_MAP.put(92, new DEFixedFormat(ValuePatternEnum.STRING, 2));
		FORMAT_MAP.put(93, new DEFixedFormat(ValuePatternEnum.STRING, 5));
		FORMAT_MAP.put(94, new DEFixedFormat(ValuePatternEnum.STRING, 7));
		FORMAT_MAP.put(95, new DEFixedFormat(ValuePatternEnum.STRING, 42));
		FORMAT_MAP.put(96, new DEFixedFormat(ValuePatternEnum.STRING, 8));

		FORMAT_MAP.put(97, new DEFixedFormat(ValuePatternEnum.STRING, 17));
		FORMAT_MAP.put(98, new DEFixedFormat(ValuePatternEnum.STRING, 25));
		FORMAT_MAP.put(99,
				new DEVariableFormat(ValuePatternEnum.NUMERIC, 11, 2));
		FORMAT_MAP.put(100, new DEVariableFormat(ValuePatternEnum.NUMERIC, 11,
				2));
		FORMAT_MAP.put(101,
				new DEVariableFormat(ValuePatternEnum.STRING, 17, 2));
		FORMAT_MAP.put(102,
				new DEVariableFormat(ValuePatternEnum.STRING, 28, 2));
		FORMAT_MAP.put(103,
				new DEVariableFormat(ValuePatternEnum.STRING, 28, 2));
		FORMAT_MAP.put(104, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));

		FORMAT_MAP.put(105, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(106, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(107, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(108, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(109, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(110, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(111, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(112, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));

		FORMAT_MAP.put(113, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(114, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(115, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(116, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(117, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(118, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(119, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(120, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));

		FORMAT_MAP.put(121, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(122, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(123, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(124, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(125, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(126, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(127, new DEVariableFormat(ValuePatternEnum.STRING, 999,
				3));
		FORMAT_MAP.put(128, new DEFixedFormat(ValuePatternEnum.STRING, 8));

	}

	/**
	 * Method to obtain the format of a data element	 * 
	 * 
	 * @param deNum
	 * @return
	 * @throws InvalidDataElementException
	 */
	public static DEFormat getDeFormat(int deNum)
			throws InvalidDataElementException {
		DEFormat outputDEFormat = FORMAT_MAP.get(deNum);

		if (outputDEFormat == null) {
			throw new InvalidDataElementException("Data Element " + deNum
					+ " is invalid.");
		}

		return outputDEFormat;
	}

}
