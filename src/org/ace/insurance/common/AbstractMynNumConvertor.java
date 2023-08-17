package org.ace.insurance.common;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class AbstractMynNumConvertor {
	protected static Properties mymNumberConfig;
	static {
		try {
			mymNumberConfig = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream in = classLoader.getResourceAsStream("/mym-number.properties");
			mymNumberConfig.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load mym-number.properties");
		}
	}
	protected final String SEPARATOR = " ";
	protected final int NO_VALUE = -1;
	protected final String KYAT = mymNumberConfig.getProperty(MymNumConstant.KYAT);
	protected final String PYA = mymNumberConfig.getProperty(MymNumConstant.PYA);
	protected final String DOLLAR = mymNumberConfig.getProperty(MymNumConstant.DOLLAR);
	protected final String CENT = mymNumberConfig.getProperty(MymNumConstant.CENT);
	protected final String AND = mymNumberConfig.getProperty(MymNumConstant.AND);
	protected final String MINUS = mymNumberConfig.getProperty(MymNumConstant.MINUS);
	protected final String CURPOSTFIX = mymNumberConfig.getProperty(MymNumConstant.CURPOSTFIX);

	protected List<Integer> getDigits(long value) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		if (value == 0) {
			digits.add(0);
		} else {
			while (value > 0) {
				digits.add(0, (int) value % 10);
				value /= 10;
			}
		}
		return digits;
	}

	public String getName(long value) {
		return getName(Long.toString(value));
	}

	public String getName(double value) {
		String dString = new DecimalFormat("#").format(value);
		return getName(dString);
	}

	/** 20.05 -> 20 kyats 5 pya */
	/** 20.56 -> 20 kyats 56 pya */
	public String getNameWithDecimal(double amount) {
		String value = new DecimalFormat("##.00").format(amount);
		long totalPya = 0;
		long totalKyat = 0;
		boolean negative = false;
		if (value.startsWith("-")) {
			negative = true;
			value = value.substring(1);
		}
		int decimals = value.indexOf(".");
		if (0 <= decimals) {
			String pya = value.substring(decimals + 1);
			// if (pya.startsWith("0") && pya.length() > 1) {
			// pya = pya.substring(1);
			// totalPya = Long.parseLong(pya);
			// totalPya = totalPya >= 5 ? 1 : 0;
			// } else
			totalPya = Long.parseLong(pya);
			if (!value.substring(0, decimals).isEmpty())
				totalKyat = Long.parseLong(value.substring(0, decimals));
		}

		String totalAmountKyat = totalKyat > 0 ? getName(totalKyat) : "";
		String totalAmountPya = totalPya > 0 ? getName(totalPya) : "";
		totalAmountKyat = totalKyat > 0 ? KYAT.concat(SEPARATOR).concat(totalAmountKyat).concat(SEPARATOR) : "";
		totalAmountPya = totalPya > 0 ? totalAmountPya.concat(PYA.concat(SEPARATOR)) : "";

		if (negative) {
			if (totalKyat > 0 && totalPya > 0) {
				return MINUS.concat(SEPARATOR).concat(totalAmountKyat).concat(AND).concat(SEPARATOR).concat(totalAmountPya);
			} else
				return MINUS.concat(SEPARATOR).concat(totalAmountKyat).concat(totalAmountPya);
		} else {
			if (totalKyat > 0 && totalPya > 0) {
				return totalAmountKyat.concat(AND).concat(SEPARATOR).concat(totalAmountPya);
			} else
				return totalAmountKyat.concat(totalAmountPya);
		}
	}

	/** 20.05 -> 20 kyats 5 pya */
	/** 20.56 -> 20 kyats 56 pya */
	public String getNameWithDollarDecimal(double amount) {
		String value = new DecimalFormat("##.00").format(amount);
		long totalPya = 0;
		long totalKyat = 0;
		boolean negative = false;
		if (value.startsWith("-")) {
			negative = true;
			value = value.substring(1);
		}
		int decimals = value.indexOf(".");
		if (0 <= decimals) {
			String pya = value.substring(decimals + 1);
			// if (pya.startsWith("0") && pya.length() > 1) {
			// pya = pya.substring(1);
			// totalPya = Long.parseLong(pya);
			// totalPya = totalPya >= 5 ? 1 : 0;
			// } else
			totalPya = Long.parseLong(pya);
			if (!value.substring(0, decimals).isEmpty())
				totalKyat = Long.parseLong(value.substring(0, decimals));
		}

		String totalAmountKyat = totalKyat > 0 ? getName(totalKyat) : "";
		String totalAmountPya = totalPya > 0 ? getName(totalPya) : "";
		totalAmountKyat = totalKyat > 0 ? DOLLAR.concat(SEPARATOR).concat(totalAmountKyat).concat(SEPARATOR).concat(CURPOSTFIX) : "";
		totalAmountPya = totalPya > 0 ? totalAmountPya.concat(CENT.concat(SEPARATOR)) : "";

		if (negative) {
			if (totalKyat > 0 && totalPya > 0) {
				return MINUS.concat(SEPARATOR).concat(totalAmountKyat).concat(AND).concat(SEPARATOR).concat(totalAmountPya);
			} else
				return MINUS.concat(SEPARATOR).concat(totalAmountKyat).concat(totalAmountPya);
		} else {
			if (totalKyat > 0 && totalPya > 0) {
				return totalAmountKyat.concat(AND).concat(SEPARATOR).concat(totalAmountPya);
			} else
				return totalAmountKyat.concat(totalAmountPya);
		}
	}

	abstract public String getName(String value);

	public String getConcateDollorAndStampFee(double usdValue, double mmkValue) {
		String usdInWord = getNameWithDollarDecimal(usdValue);
		String mmkInWord = getNameWithDecimal(mmkValue);
		return usdInWord.concat(AND).concat(SEPARATOR).concat(mmkInWord);
	}
	
	public static String getAmountWithMyanmar(String value) {
		String genName = "";
//		String year = org.ace.insurance.common.Utils.getYearFormat(date);
		for (int i = 0; i < value.length(); i++) {
			switch (String.valueOf((value.charAt(i)))) {
				case "0":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_ZERO);
					break;
				case "1":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_ONE);
					break;
				case "2":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_TWO);
					break;
				case "3":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_TRHEE);
					break;
				case "4":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_FOUR);
					break;
				case "5":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_FIVE);
					break;
				case "6":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_SIX);
					break;
				case "7":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_SEVEN);
					break;
				case "8":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_EIGHT);
					break;
				case "9":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_NINE);
					break;
				default:
					genName += ",";
			}
		}
		return genName;
	}
	
	public static String getDateWithMyanmar(String value) {
		String genName = "";
//		String year = org.ace.insurance.common.Utils.getYearFormat(date);
		for (int i = 0; i < value.length(); i++) {
			switch (String.valueOf((value.charAt(i)))) {
				case "0":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_ZERO);
					break;
				case "1":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_ONE);
					break;
				case "2":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_TWO);
					break;
				case "3":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_TRHEE);
					break;
				case "4":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_FOUR);
					break;
				case "5":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_FIVE);
					break;
				case "6":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_SIX);
					break;
				case "7":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_SEVEN);
					break;
				case "8":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_EIGHT);
					break;
				case "9":
					genName += (String) mymNumberConfig.getProperty(MymNumConstant.MYANMAR_FONT_NUMBER_NINE);
					break;
				default:
					genName += ".";
			}
		}
		return genName;
	}
}
