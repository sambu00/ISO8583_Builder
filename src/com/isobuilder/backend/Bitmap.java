package com.isobuilder.backend;

import java.io.Serializable;
import com.isobuilder.backend.dataelement.MessageElementValue;

/**
 * Class used to represent a bitmap for the ISO 8583 message. The bitmap is made
 * by 64 bits, * The order is left to right. The attribute adj is used to adjust
 * the bit position: internally to the class and the counting starts from 0, but
 * externally starts from 1.
 * 
 * @author Federico Alaimo
 *
 */
public class Bitmap implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean[] bits;
	private int adj;

	/**
	 * Class Constructor, parameter level is used to calculate adj attribute
	 * 
	 * @param level
	 */
	public Bitmap(int level) {
		setBits(new boolean[64]);
		for (int i = 0; i < bits.length; i++) {
			bits[i] = false;
		}

		this.adj = 1 + (level * 64);
	}

	/**
	 * bits list getter
	 * 
	 * @return bits list
	 */
	public boolean[] getBits() {
		return bits;
	}

	/**
	 * bits list setter
	 * 
	 * @param bits
	 */
	public void setBits(boolean[] bits) {
		this.bits = bits;
	}

	/**
	 * return single bit value (true or false)
	 * 
	 * @param pos
	 * @return selected bit
	 */
	public boolean getSingleBit(int pos) {
		int selected = adjustPos(pos);
		return bits[selected];
	}

	/**
	 * set single bit value (true or false)
	 * 
	 * @param pos
	 * @param newValue
	 */
	public void setSingleBit(int pos, boolean newValue) {
		int selected = adjustPos(pos);
		bits[selected] = newValue;
	}

	/**
	 * toString: represent the Bitmap as a String of 64 characters where '1' is
	 * a bit turned on and '0' is a bit turned off
	 * 
	 */
	public String toString() {

		String bitsString = "";

		for (int i = 0; i < bits.length; i++) {
			if (bits[i]) {
				bitsString += "1";
			} else {
				bitsString += "0";
			}
		}

		return bitsString;
	}

	/**
	 * Generate a String that is the hexadecimal representation of the bitmap
	 * 
	 * @return bitmap as an hexadecimal string
	 */
	public String toHexString() {
		String hexString = "";

		String bitsString = toString();
		int i = 0;
		do {
			long eachByte = Long.parseLong(bitsString.substring(i, i + 4), 2);
			hexString += Long.toHexString(eachByte).toUpperCase();
			i += 4;
		} while (i < bits.length);

		return MessageElementValue.HEX_TAG_OPEN + hexString
				+ MessageElementValue.HEX_TAG_CLOSE;
	}

	/**
	 * return the total of the bits turned on in the bitmap
	 * 
	 * @return total bits turned on in the bitmap
	 */
	public int getBitsOnCount() {
		int bitsOn = 0;
		for (int i = 0; i < bits.length; i++) {
			if (bits[i]) {
				bitsOn++;
			}
		}

		return bitsOn;
	}

	/**
	 * Adjust position depending on adj parameter. In this way a Bitmap object
	 * can use the same bit position of the related data element. Internally to
	 * the class the position is adjusted to count from 0 to 64
	 * 
	 * @param pos
	 * @return
	 */
	private int adjustPos(int pos) {
		pos = pos - adj;

		return pos;
	}

}
