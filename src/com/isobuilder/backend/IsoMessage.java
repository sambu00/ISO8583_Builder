package com.isobuilder.backend;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import com.isobuilder.backend.dataelement.DEFactory;
import com.isobuilder.backend.dataelement.DataElement;
import com.isobuilder.backend.dataelement.MessageElementValue;
import com.isobuilder.exceptions.FixedLengthNotHonoredException;
import com.isobuilder.exceptions.PatternException;
import com.isobuilder.exceptions.InvalidDataElementException;
import com.isobuilder.exceptions.InvalidMessageTypeException;
import com.isobuilder.exceptions.MaximumLengthExceededException;
import com.isobuilder.exceptions.ReservedDataElementException;
import com.isobuilder.exceptions.ZeroLengthException;

/**
 * IsoMessage is the class that represent an ISO8583 message. It's build by a
 * message type, two bitmaps and a list of data elements. Bitmaps data are
 * mapped by the dedicated Bitmap class. Data Elements list is represented by a
 * TreeMap class where the key (Integer) is the data element position and the
 * value (DataElement) is a DataElement object
 * 
 * @author Federico Alaimo
 *
 */
public class IsoMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String messageType;

	private Bitmap bitmap1;
	private Bitmap bitmap2;

	private TreeMap<Integer, DataElement> deMap;

	/**
	 * Class constructor providing a message type
	 * 
	 * @param messageType
	 *            String to be set in message type field
	 * @throws InvalidMessageTypeException
	 */
	public IsoMessage(String messageType) throws InvalidMessageTypeException {
		setMessageType(messageType);
		initBitmaps();
		deMap = new TreeMap<Integer, DataElement>();

	}

	/**
	 * Class constructor without parameters. message type field is defaulted to
	 * "0000"
	 * 
	 */
	public IsoMessage() {
		this.messageType = "0000";
		initBitmaps();
		deMap = new TreeMap<Integer, DataElement>();

	}

	/**
	 * message type getter
	 * 
	 * @return the message type of the ISO message
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * message type setter
	 * 
	 * @param messageType
	 *            String to be set in message type
	 * @throws InvalidMessageTypeException
	 *             in case messageType is not numeric
	 */
	public void setMessageType(String messageType)
			throws InvalidMessageTypeException {

		if (!isNumeric(messageType)) {
			throw new InvalidMessageTypeException("Message type " + messageType
					+ " is invalid");
		}

		this.messageType = messageType;

	}

	/**
	 * bitmap1 getter
	 * 
	 * @return bitmap1
	 */
	public Bitmap getBitmap1() {
		return bitmap1;
	}

	/**
	 * bitmap1 setter
	 * 
	 * @param bitmap1
	 */
	public void setBitmap1(Bitmap bitmap1) {
		this.bitmap1 = bitmap1;
	}

	/**
	 * bitmap2 getter
	 * 
	 * @return bitmap2
	 */
	public Bitmap getBitmap2() {
		return bitmap2;
	}

	/**
	 * bitmap2 setter
	 * 
	 * @param bitmap2
	 */
	public void setBitmap2(Bitmap bitmap2) {
		this.bitmap2 = bitmap2;
	}

	/**
	 * deMap getter
	 * 
	 * @return deMap
	 */
	public TreeMap<Integer, DataElement> getDeMap() {
		return deMap;
	}

	/**
	 * deMap setter
	 * 
	 * @param deMap
	 */
	public void setDeMap(TreeMap<Integer, DataElement> deMap) {
		this.deMap = deMap;
	}

	/**
	 * Add a data element to the ISO message. If the data element is already
	 * present it will be replaced. The bitmaps are modified accoringly
	 * 
	 * @param position
	 *            position of the data element
	 * @param de
	 *            Data Element to be added
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws ReservedDataElementException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	public void addDataElement(int position, DataElement de)
			throws ReservedDataElementException,
			MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		if (position == 1) {
			throw new ReservedDataElementException("DataElement " + position
					+ " is reserved for internal use");
		}

		// select appropriate bitmap
		Bitmap bm = bitmapChooser(position);

		// add/replace Data Element to map structure
		if (!bm.getSingleBit(position)) {
			deMap.put(de.getPosition(), de);
			// set to true the relative bit in the bitmap
			bm.setSingleBit(position, true);

			// if bitmap2 -> add bitmap2 as data element 1
			if (bm == bitmap2) {
				addDataElement1();
			}

		} else {
			deMap.replace(de.getPosition(), de);
		}

	}

	/**
	 * Remove a Data Element from the ISO message. The bitmaps are modified
	 * accordingly
	 * 
	 * @param position
	 *            position of the data element to be removed
	 * @throws ReservedDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws InvalidDataElementException
	 * @throws PatternException
	 * @throws MaximumLengthExceededException
	 * @throws ZeroLengthException
	 */
	public void removeDataElement(int position)
			throws ReservedDataElementException,
			MaximumLengthExceededException, PatternException,
			InvalidDataElementException, FixedLengthNotHonoredException,
			ZeroLengthException {
		if (position == 1) {
			throw new ReservedDataElementException("DataElement " + position
					+ " is reserved for internal use");
		}

		Bitmap bm = bitmapChooser(position);

		if (bm.getSingleBit(position)) {
			deMap.remove(position);
			bm.setSingleBit(position, false);

			// if bitmap2, data element 1 must be modified accordingly
			if (bm == bitmap2) {
				// bitmap2 all zeroes -> remove data element 1
				if (bm.getBitsOnCount() == 0) {
					removeDataElement1();
				} else {
					// other wise update data element 1 value
					addDataElement1();
				}
			}
		}

	}

	/**
	 * Get a data element from the ISO message
	 * 
	 * @param position
	 *            position of the data element
	 * @return data element
	 */
	public DataElement getDataElement(int position) {
		return deMap.get(position);
	}

	/**
	 * Get the whole ISO message as a String
	 * 
	 * @return iso message
	 */
	public String getIsoExtendedMessage() {
		String ms = "";

		Set<Integer> keys = deMap.keySet();
		Iterator<Integer> keysIterator = keys.iterator();

		while (keysIterator.hasNext()) {
			Integer key = keysIterator.next();
			DataElement de = deMap.get(key);
			ms = ms + MessageElementValue.extendedValue(de.getValue());
		}

		return MessageElementValue.extendedValue(messageType)
				+ MessageElementValue.extendedValue(bitmap1.toHexString()) + ms;
	}

	/**
	 * Get the whole ISO message as a String encoded in the specified charset
	 * 
	 * @param charsetName
	 * @return encoded iso message
	 * @throws UnsupportedEncodingException
	 */
	public String getIsoExtendedMessage(String charsetName)
			throws UnsupportedEncodingException {
		String ms = "";

		Set<Integer> keys = deMap.keySet();
		Iterator<Integer> keysIterator = keys.iterator();

		while (keysIterator.hasNext()) {
			Integer key = keysIterator.next();
			DataElement de = deMap.get(key);
			ms = ms
					+ MessageElementValue.extendedValue(de.getValue(),
							charsetName);
		}

		return MessageElementValue.extendedValue(messageType, charsetName)
				+ MessageElementValue.extendedValue(bitmap1.toHexString(),
						charsetName) + ms;
	}

	/**
	 * initialize the bitmaps with all zeroes
	 */
	private void initBitmaps() {
		bitmap1 = new Bitmap(0);
		bitmap2 = new Bitmap(1);
	}

	/**
	 * Return the bitmap to be used depending on the position specified
	 * 
	 * @param position
	 * @return bitmap to be used
	 */
	private Bitmap bitmapChooser(int position) {
		if (position < 65) {
			return bitmap1;
		} else {
			return bitmap2;
		}
	}

	/**
	 * Add reserved data element 1
	 * 
	 * @throws MaximumLengthExceededException
	 * @throws PatternException
	 * @throws InvalidDataElementException
	 * @throws FixedLengthNotHonoredException
	 * @throws ZeroLengthException
	 */
	private void addDataElement1() throws MaximumLengthExceededException,
			PatternException, InvalidDataElementException,
			FixedLengthNotHonoredException, ZeroLengthException {
		deMap.put(1, DEFactory.generateDataElement(1, bitmap2.toHexString()));
		bitmap1.setSingleBit(1, true); // set to true the relative bit
	}

	/**
	 * Remove reserved data element 1
	 * 
	 */
	private void removeDataElement1() {
		deMap.remove(1);
		bitmap1.setSingleBit(1, false); // set to false the relative bit
	}

	/**
	 * Check the input String for numeric value.
	 * 
	 * @param str
	 *            string to be checked
	 * @return true if str is numeric, false otherwise
	 */
	private boolean isNumeric(String str) {
		char[] charSequence = str.toCharArray();

		boolean digitCehck = true;

		for (int i = 0; i < charSequence.length; i++) {
			if (!Character.isDigit(charSequence[i])) {
				digitCehck = false;
				break;
			}
		}

		return digitCehck;

	}

}
