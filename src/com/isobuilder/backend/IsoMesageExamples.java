package com.isobuilder.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.isobuilder.backend.dataelement.DEFactory;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.InvalidMessageTypeException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ReservedDataElementException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * Final class used to generate ISO message examples
 * 
 * @author Federico Alaimo
 * */
public final class IsoMesageExamples {

	/**
	 * Generate MasterCard 0100 purchase message example
	 * 
	 * @return MasterCard 0100 purchase message example
	 * @throws InvalidMessageTypeException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws ReservedDataElementException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public static IsoMessage genMastercardExample()
			throws InvalidMessageTypeException, MaximumLengthExceededException,
			PatternException, ReservedDataElementException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();

		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0100");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "5555555555555555"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "000000"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000100"));
		isoMsg.addDataElement(6,
				DEFactory.generateDataElement(6, "000000000100"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(15,
				DEFactory.generateDataElement(15, genDE015(currentDate)));
		isoMsg.addDataElement(16,
				DEFactory.generateDataElement(16, genDE016(currentDate)));
		isoMsg.addDataElement(17,
				DEFactory.generateDataElement(17, genDE017(currentDate)));
		isoMsg.addDataElement(18, DEFactory.generateDataElement(18, "8398"));
		isoMsg.addDataElement(19, DEFactory.generateDataElement(19, "380"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "012"));
		isoMsg.addDataElement(23, DEFactory.generateDataElement(23, "000"));
		isoMsg.addDataElement(25, DEFactory.generateDataElement(25, "55"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(33,
				DEFactory.generateDataElement(33, "12345678901"));
		isoMsg.addDataElement(35, DEFactory.generateDataElement(35,
				"5555555555555555D2512   **********"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(41, DEFactory.generateDataElement(41, "TERMID01"));
		isoMsg.addDataElement(42,
				DEFactory.generateDataElement(42, "123456789012345"));
		isoMsg.addDataElement(43, DEFactory.generateDataElement(43,
				"JAVA MSG BUILDER       MILANO         IT"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(51, DEFactory.generateDataElement(51, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20EPC"));
		isoMsg.addDataElement(109,
				DEFactory.generateDataElement(109, genDE109Mc()));
		isoMsg.addDataElement(112, DEFactory.generateDataElement(112, "010010"
				+ "020010" + "03003C07" + "04012123456890123"));
		isoMsg.addDataElement(116,
				DEFactory.generateDataElement(116, "00000000000"));
		// isoMsg.addDataElement(118, DEFactory.generateDataElement());
		isoMsg.addDataElement(
				123,
				DEFactory
						.generateDataElement(123,
								"TDMG<.hex>80008000</.hex>IC<.hex>FFFFFFFF00000000</.hex>VVYYX"));

		return isoMsg;
	}

	/**
	 * Generate VISA 0100 purchase message example
	 * 
	 * @return VISA 0100 purchase message example
	 * @throws InvalidMessageTypeException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws ReservedDataElementException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public static IsoMessage genVisaExample()
			throws InvalidMessageTypeException, MaximumLengthExceededException,
			PatternException, ReservedDataElementException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();
		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0100");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "4444444444444444"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "000000"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000100"));
		isoMsg.addDataElement(6,
				DEFactory.generateDataElement(6, "000000000100"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(15,
				DEFactory.generateDataElement(15, genDE015(currentDate)));
		isoMsg.addDataElement(16,
				DEFactory.generateDataElement(16, genDE016(currentDate)));
		isoMsg.addDataElement(17,
				DEFactory.generateDataElement(17, genDE017(currentDate)));
		isoMsg.addDataElement(18, DEFactory.generateDataElement(18, "8398"));
		isoMsg.addDataElement(19, DEFactory.generateDataElement(19, "380"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "012"));
		isoMsg.addDataElement(23, DEFactory.generateDataElement(23, "000"));
		isoMsg.addDataElement(25, DEFactory.generateDataElement(25, "55"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(33,
				DEFactory.generateDataElement(33, "12345678901"));
		isoMsg.addDataElement(35, DEFactory.generateDataElement(35,
				"4444444444444444D2512   **********"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(41, DEFactory.generateDataElement(41, "TERMID01"));
		isoMsg.addDataElement(42,
				DEFactory.generateDataElement(42, "123456789012345"));
		isoMsg.addDataElement(43, DEFactory.generateDataElement(43,
				"JAVA MSG BUILDER       MILANO         IT"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(51, DEFactory.generateDataElement(51, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20VIS"));
		isoMsg.addDataElement(109,
				DEFactory.generateDataElement(109, genDE109Visa()));
		isoMsg.addDataElement(112, DEFactory.generateDataElement(112, "010010"
				+ "020010" + "03003C07" + "04012123456890123"));
		isoMsg.addDataElement(116,
				DEFactory.generateDataElement(116, "00000000000"));
		isoMsg.addDataElement(
				123,
				DEFactory
						.generateDataElement(123,
								"TDMG<.hex>80008000</.hex>IC<.hex>FFFFFFFF00000000</.hex>VVYYX"));

		return isoMsg;

	}

	/**
	 * Generate MDES TAR message example
	 * 
	 * @return MDES TAR message example
	 * @throws InvalidMessageTypeException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws ReservedDataElementException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public static IsoMessage genMdesTARExample()
			throws InvalidMessageTypeException, MaximumLengthExceededException,
			PatternException, ReservedDataElementException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();

		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0100");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "5555555555555555"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "970200"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000000"));
		isoMsg.addDataElement(6,
				DEFactory.generateDataElement(6, "000000000000"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(15,
				DEFactory.generateDataElement(15, genDE015(currentDate)));
		isoMsg.addDataElement(16,
				DEFactory.generateDataElement(16, genDE016(currentDate)));
		isoMsg.addDataElement(17,
				DEFactory.generateDataElement(17, genDE017(currentDate)));
		isoMsg.addDataElement(18, DEFactory.generateDataElement(18, "8398"));
		isoMsg.addDataElement(19, DEFactory.generateDataElement(19, "380"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "010"));
		isoMsg.addDataElement(23, DEFactory.generateDataElement(23, "000"));
		isoMsg.addDataElement(25, DEFactory.generateDataElement(25, "07"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(41, DEFactory.generateDataElement(41, "TERMID01"));
		isoMsg.addDataElement(42,
				DEFactory.generateDataElement(42, "123456789012345"));
		isoMsg.addDataElement(43, DEFactory.generateDataElement(43,
				"JAVA MSG BUILDER v2.0  MILANO         IT"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(51, DEFactory.generateDataElement(51, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20EPC"));
		isoMsg.addDataElement(
				109,
				DEFactory.generateDataElement(109, genDE109Mc()
						+ "131199999999999"));
		isoMsg.addDataElement(
				112,
				DEFactory
						.generateDataElement(
								112,
								"05003103"
										+ "120011"
										+ "1700418C "
										+ "18038H     50120834693                   01"
										+ "21195TAD00057828269812-SH_kjTclsSg-ASsZIYu7crRPKSqxXVtsPXjBY1ExD2InuM7972B1893    123CCB2F30BA420B49A5F207D73BB5D3DA30FFBEFF10EFA170D036F1128790FBAldo                       101350300000129600F0021427C"));
		isoMsg.addDataElement(116, DEFactory.generateDataElement(116,
				"102510900000084063368     "));
		isoMsg.addDataElement(
				118,
				DEFactory
						.generateDataElement(118,
								"000000M          1215230256           21                                   "));
		isoMsg.addDataElement(123,
				DEFactory.generateDataElement(123, "TDCV0711****MHM0000000"));

		return isoMsg;
	}

	/**
	 * Generate MDES TCN message example
	 * 
	 * @return MDES TCN message example
	 * @throws InvalidMessageTypeException
	 * @throws ZeroLengthException
	 * @throws FixedLengthNotHonoredException
	 * @throws InvalidDataElementException
	 * @throws PatternException
	 * @throws MaximumLengthExceededException
	 * @throws ReservedDataElementException
	 */
	public static IsoMessage genMdesTCNExample()
			throws InvalidMessageTypeException, ReservedDataElementException,
			MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();

		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0100");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "5555555555555555"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "970400"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000000"));
		isoMsg.addDataElement(6,
				DEFactory.generateDataElement(6, "000000000000"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(15,
				DEFactory.generateDataElement(15, genDE015(currentDate)));
		isoMsg.addDataElement(16,
				DEFactory.generateDataElement(16, genDE016(currentDate)));
		isoMsg.addDataElement(18, DEFactory.generateDataElement(18, "8398"));
		isoMsg.addDataElement(19, DEFactory.generateDataElement(19, "380"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "010"));
		isoMsg.addDataElement(23, DEFactory.generateDataElement(23, "000"));
		isoMsg.addDataElement(25, DEFactory.generateDataElement(25, "07"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(41, DEFactory.generateDataElement(41, "TERMID01"));
		isoMsg.addDataElement(42,
				DEFactory.generateDataElement(42, "123456789012345"));
		isoMsg.addDataElement(43, DEFactory.generateDataElement(43,
				"JAVA MSG BUILDER v2.0  MILANO         IT"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(51, DEFactory.generateDataElement(51, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20EPC"));
		isoMsg.addDataElement(
				109,
				DEFactory.generateDataElement(109, genDE109Mc()
						+ "08165111111111111111" + "09042612"
						+ "131199999999999"));
		isoMsg.addDataElement(
				112,
				DEFactory
						.generateDataElement(
								112,
								"05003103"
										+ "120011"
										+ "1700418C "
										+ "18038H     50120834693                   01"
										+ "21192TCD0005782826981  0000000003                      12                                           DM4MMC0000168368384a0b0a80094751af51b36e511db057FM4MMC00001683689622a777e6dc4b68bfb0ff1cbacd8178C"));
		isoMsg.addDataElement(116, DEFactory.generateDataElement(116,
				"102510900000084063368     "));
		isoMsg.addDataElement(
				118,
				DEFactory
						.generateDataElement(118,
								"000000M          1215230256           21                                   "));
		isoMsg.addDataElement(123,
				DEFactory.generateDataElement(123, "TDHM0000000RP  N     00"));

		return isoMsg;
	}

	/**
	 * Generate MDES token based purchase example
	 * 
	 * @return MDES token based purchase example
	 * @throws InvalidMessageTypeException
	 * @throws ZeroLengthException
	 * @throws FixedLengthNotHonoredException
	 * @throws InvalidDataElementException
	 * @throws PatternException
	 * @throws MaximumLengthExceededException
	 * @throws ReservedDataElementException
	 */
	public static IsoMessage genMdesPurchaseExample()
			throws InvalidMessageTypeException, ReservedDataElementException,
			MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();

		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0100");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "5555555555555555"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "000000"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000100"));
		isoMsg.addDataElement(6,
				DEFactory.generateDataElement(6, "000000000100"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(15,
				DEFactory.generateDataElement(15, genDE015(currentDate)));
		isoMsg.addDataElement(16,
				DEFactory.generateDataElement(16, genDE016(currentDate)));
		isoMsg.addDataElement(18, DEFactory.generateDataElement(18, "8398"));
		isoMsg.addDataElement(19, DEFactory.generateDataElement(19, "380"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "072"));
		isoMsg.addDataElement(23, DEFactory.generateDataElement(23, "000"));
		isoMsg.addDataElement(25, DEFactory.generateDataElement(25, "02"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(41, DEFactory.generateDataElement(41, "TERMID01"));
		isoMsg.addDataElement(42,
				DEFactory.generateDataElement(42, "123456789012345"));
		isoMsg.addDataElement(43, DEFactory.generateDataElement(43,
				"JAVA MSG BUILDER       MILANO         IT"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(51, DEFactory.generateDataElement(51, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20EPC"));
		isoMsg.addDataElement(
				109,
				DEFactory.generateDataElement(109, genDE109Mc()
						+ "08165111111111111111" + "09042612"));
		isoMsg.addDataElement(112, DEFactory.generateDataElement(112, "010010"
				+ "020010" + "03003C07" + "05003103" + "1701250C 51V 18C "));
		isoMsg.addDataElement(116,
				DEFactory.generateDataElement(116, "00000000000"));
		isoMsg.addDataElement(123,
				DEFactory.generateDataElement(123, "TDHM0000000"));

		return isoMsg;
	}

	/**
	 * Generate VTS TAR message example
	 * 
	 * @return VTS TAR message example
	 * @throws InvalidMessageTypeException
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws ReservedDataElementException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public static IsoMessage genVtsTARExample()
			throws InvalidMessageTypeException, MaximumLengthExceededException,
			PatternException, ReservedDataElementException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();

		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0100");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "4444444444444444"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "970200"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000000"));
		isoMsg.addDataElement(6,
				DEFactory.generateDataElement(6, "000000000000"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(15,
				DEFactory.generateDataElement(15, genDE015(currentDate)));
		isoMsg.addDataElement(18, DEFactory.generateDataElement(18, "8398"));
		isoMsg.addDataElement(19, DEFactory.generateDataElement(19, "380"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "000"));
		isoMsg.addDataElement(23, DEFactory.generateDataElement(23, "000"));
		isoMsg.addDataElement(25, DEFactory.generateDataElement(25, "51"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(41, DEFactory.generateDataElement(41, "TERMID01"));
		isoMsg.addDataElement(42,
				DEFactory.generateDataElement(42, "123456789012345"));
		isoMsg.addDataElement(43, DEFactory.generateDataElement(43,
				"JAVA MSG BUILDER v2.0  MILANO         IT"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(51, DEFactory.generateDataElement(51, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20VSA"));
		isoMsg.addDataElement(109,
				DEFactory.generateDataElement(109, genDE109Visa()));

		isoMsg.addDataElement(111, DEFactory.generateDataElement(111, "05098"
				+ "<.hex>0B18</.hex>V-3821011708469569970409"
				+ "<.hex>1102</.hex>00" + "<.hex>030B</.hex>40020064037"
				+ "<.hex>0202</.hex>  "
				+ "<.hex>0518</.hex>DNITHE302234982890611097"
				+ "<.hex>0702</.hex>01" + "<.hex>1002</.hex>40"
				+ "<.hex>1201</.hex>2" + "<.hex>1301</.hex>0"
				+ "<.hex>1401</.hex>0" + "<.hex>8606060400100728</.hex>"
				+ "06005" + "<.hex>0203</.hex>eng" + "07051"
				+ "<.hex>090B</.hex>40020064037" + "<.hex>0802</.hex>01"
				+ "<.hex>0A20</.hex>hd000000111111222222333333444455"));
		isoMsg.addDataElement(
				118,
				DEFactory
						.generateDataElement(
								118,
								"000000                                                                        3700"));
		isoMsg.addDataElement(123,
				DEFactory.generateDataElement(123, "TDHV0000000RP  N     00"));

		return isoMsg;
	}

	/**
	 * Generate VTS TAA for tokenization complete message example
	 * 
	 * @return VTS TAA for tokenization complete message example
	 * @throws InvalidMessageTypeException
	 * @throws ZeroLengthException
	 * @throws FixedLengthNotHonoredException
	 * @throws InvalidDataElementException
	 * @throws PatternException
	 * @throws MaximumLengthExceededException
	 * @throws ReservedDataElementException
	 */
	public static IsoMessage genVtsTCNExample()
			throws InvalidMessageTypeException, ReservedDataElementException,
			MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();

		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0120");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "4444444444444444"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "970400"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000000"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "010"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(39, DEFactory.generateDataElement(39, "00"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20VSA"));
		isoMsg.addDataElement(
				109,
				DEFactory.generateDataElement(109, genDE109Visa()
						+ "08164111111111111111" + "09042612"));
		isoMsg.addDataElement(111, DEFactory.generateDataElement(111, "05100"
				+ "<.hex>0110</.hex>4044470960539141" + "<.hex>0202</.hex>10"
				+ "<.hex>030B</.hex>40020064037"
				+ "<.hex>0B18</.hex>V-3821011708469569970409"
				+ "<.hex>0518</.hex>DNITHE302234982890611097"
				+ "<.hex>0604</.hex>2711" + "<.hex>0702</.hex>01"
				+ "<.hex>0801</.hex>A" + "06005" + "<.hex>0203</.hex>eng"
				+ "07045" + "<.hex>0917</.hex>AYBFRUreV9e3L7HkPrOmPfA"
				+ "<.hex>0802</.hex>01" + "<.hex>0A0E</.hex>12345678901234"));
		isoMsg.addDataElement(116, DEFactory.generateDataElement(116,
				"102510900000084063368     "));
		isoMsg.addDataElement(
				118,
				DEFactory
						.generateDataElement(
								118,
								"                                                                              3700"));
		isoMsg.addDataElement(123,
				DEFactory.generateDataElement(123, "TDHV0000000"));

		return isoMsg;
	}

	/**
	 * Generate VTS token based purchase example
	 * 
	 * @return VTS token based purchase example
	 * @throws InvalidMessageTypeException
	 * @throws ZeroLengthException
	 * @throws FixedLengthNotHonoredException
	 * @throws InvalidDataElementException
	 * @throws PatternException
	 * @throws MaximumLengthExceededException
	 * @throws ReservedDataElementException
	 */
	public static IsoMessage genVtsPurchaseExample()
			throws InvalidMessageTypeException, ReservedDataElementException,
			MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		Date currentDate = Calendar.getInstance().getTime();
		IsoMessage isoMsg = new IsoMessage();

		isoMsg.setMessageType("0100");

		isoMsg.addDataElement(2,
				DEFactory.generateDataElement(2, "4444444444444444"));
		isoMsg.addDataElement(3, DEFactory.generateDataElement(3, "000000"));
		isoMsg.addDataElement(4,
				DEFactory.generateDataElement(4, "000000000100"));
		isoMsg.addDataElement(6,
				DEFactory.generateDataElement(6, "000000000100"));
		isoMsg.addDataElement(7,
				DEFactory.generateDataElement(7, genDE007(currentDate)));
		isoMsg.addDataElement(11, DEFactory.generateDataElement(11, genDE011()));
		isoMsg.addDataElement(12,
				DEFactory.generateDataElement(12, genDE012(currentDate)));
		isoMsg.addDataElement(13,
				DEFactory.generateDataElement(13, genDE013(currentDate)));
		isoMsg.addDataElement(14, DEFactory.generateDataElement(14, "2612"));
		isoMsg.addDataElement(15,
				DEFactory.generateDataElement(15, genDE015(currentDate)));
		isoMsg.addDataElement(18, DEFactory.generateDataElement(18, "8398"));
		isoMsg.addDataElement(19, DEFactory.generateDataElement(19, "380"));
		isoMsg.addDataElement(22, DEFactory.generateDataElement(22, "012"));
		isoMsg.addDataElement(23, DEFactory.generateDataElement(23, "000"));
		isoMsg.addDataElement(25, DEFactory.generateDataElement(25, "55"));
		isoMsg.addDataElement(32, DEFactory.generateDataElement(32, "08401983"));
		isoMsg.addDataElement(37,
				DEFactory.generateDataElement(37, "123422343234"));
		isoMsg.addDataElement(41, DEFactory.generateDataElement(41, "TERMID01"));
		isoMsg.addDataElement(42,
				DEFactory.generateDataElement(42, "123456789012345"));
		isoMsg.addDataElement(43, DEFactory.generateDataElement(43,
				"JAVA MSG BUILDER       MILANO         IT"));
		isoMsg.addDataElement(49, DEFactory.generateDataElement(49, "978"));
		isoMsg.addDataElement(51, DEFactory.generateDataElement(51, "978"));
		isoMsg.addDataElement(63, DEFactory.generateDataElement(63, "20VSA"));
		isoMsg.addDataElement(
				109,
				DEFactory.generateDataElement(109, genDE109Visa()
						+ "08164111111111111111" + "09042612"));
		isoMsg.addDataElement(111, DEFactory.generateDataElement(111, "05045"
				+ "<.hex>0110</.hex>4044470960539141" + "<.hex>0202</.hex>11"
				+ "<.hex>030B</.hex>40020064037" + "<.hex>0604</.hex>2711"
				+ "<.hex>0702</.hex>01"));

		isoMsg.addDataElement(112, DEFactory.generateDataElement(112, "010010"
				+ "020010" + "03003C07" + "04012123456890123"));
		isoMsg.addDataElement(116,
				DEFactory.generateDataElement(116, "00000000000"));
		isoMsg.addDataElement(
				123,
				DEFactory
						.generateDataElement(123,
								"TDMG<.hex>80008000</.hex>IC<.hex>FFFFFFFF00000000</.hex>VVYYX"));

		return isoMsg;

	}

	/**
	 * Dynamically generate data element 007
	 * 
	 * @param date
	 * @return data element 7 string value
	 */
	private static String genDE007(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		return dateFormat.format(date);
	}

	private static String genDE011() {
		Random rnd = new Random();

		int de011 = rnd.nextInt(1000000);

		return String.format("%06d", de011);
	}

	/**
	 * Dynamically generate data element 012
	 * 
	 * @param date
	 * @return data element 12 string value
	 */
	private static String genDE012(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		return dateFormat.format(date);
	}

	/**
	 * Dynamically generate data element 013
	 * 
	 * @param date
	 * @return data element 13 string value
	 */
	private static String genDE013(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MMdd");
		return dateFormat.format(date);
	}

	/**
	 * Dynamically generate data element 015
	 * 
	 * @param date
	 * @return data element 15 string value
	 */
	private static String genDE015(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MMdd");
		return dateFormat.format(date);
	}

	/**
	 * Dynamically generate data element 016
	 * 
	 * @param date
	 * @return data element 16 string value
	 */
	private static String genDE016(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MMdd");
		return dateFormat.format(date);
	}

	/**
	 * Dynamically generate data element 017
	 * 
	 * @param date
	 * @return data element 17 string value
	 */
	private static String genDE017(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("MMdd");
		return dateFormat.format(date);
	}

	/**
	 * Dynamically generate data element 109 for MasterCard
	 * 
	 * @return data element 109 string value
	 */
	private static String genDE109Mc() {
		Random rnd = new Random();
		int de109t01 = rnd.nextInt(1000000000);
		long de109t02 = rnd.nextLong();
		if (de109t02 < 0) {
			de109t02 = de109t02 * -1;
		}

		String de109t01String = Integer.toString(de109t01);
		String de109t02String = Long.toString(de109t02);

		if (de109t01String.length() > 9) {
			de109t01String = de109t01String.substring(0, 9);
		}

		while (de109t01String.length() < 9) {
			de109t01String = "0" + de109t01String;
		}

		if (de109t02String.length() > 13) {
			de109t02String = de109t02String.substring(0, 13);
		}

		while (de109t02String.length() < 13) {
			de109t02String = "0" + de109t02String;
		}

		return "0112" + de109t01String + "   " + "0215" + de109t02String + "  ";

	}

	/**
	 * Dynamically generate data element 109 for VISA
	 * 
	 * @return data element 109 string value
	 */
	private static String genDE109Visa() {
		Random rnd = new Random();
		long de109t03 = rnd.nextLong();
		if (de109t03 < 0) {
			de109t03 = de109t03 * -1;
		}

		String de109t03String = Long.toString(de109t03);

		if (de109t03String.length() > 15) {
			de109t03String = de109t03String.substring(0, 15);
		}

		while (de109t03String.length() < 15) {
			de109t03String = "0" + de109t03String;
		}

		return "0315" + de109t03String;

	}
}
