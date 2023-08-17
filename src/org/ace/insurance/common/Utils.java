package org.ace.insurance.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utils {

	public static double getPercentOf(double percent, double amount) {
		return Utils.divide(amount * percent, 100);
	}

	public static double divide(double dividend, double divisor) {
		BigDecimal dividendBig = new BigDecimal(dividend);
		BigDecimal divisorBig = new BigDecimal(divisor);
		return dividendBig.divide(divisorBig, 2, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * Return value with two decimal point. Round mode.
	 * 
	 * @param value
	 * @return two decimal point value
	 */
	public static double getTwoDecimalPoint(double value) {
		BigDecimal bigDecimal = new BigDecimal(value);
		bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
		return bigDecimal.doubleValue();
	}

	public static Date resetStartDate(Date startDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String getRandomActivatedCode() {
		String result = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(9) + 1;
		}
		return result;
	}

	public static Date getDate(String stringDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formatter.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDateFormatString(Date date) {
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			return formatter.format(date);
		} else
			return "";
	}

	public static <T> boolean isNull(T t) {
		if (t == null) {
			return true;
		}
		return false;
	}
	public static String formattedCurrency(double value) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
		StringBuffer buffer = new StringBuffer(numberFormat.format(value));
		if (value < 0) {
			return "- " + buffer.substring(2, buffer.length() - 1);
		} else {
			return buffer.substring(1).toString();
		}
	}
	public static int getAgeForNextYear(Date dateOfBirth) {
		Calendar cal_1 = Calendar.getInstance();
		int currentYear = cal_1.get(Calendar.YEAR);
		Calendar cal_2 = Calendar.getInstance();
		cal_2.setTime(dateOfBirth);
		cal_2.set(Calendar.YEAR, currentYear);

		if (new Date().after(cal_2.getTime())) {
			Calendar cal_3 = Calendar.getInstance();
			cal_3.setTime(dateOfBirth);
			int year_1 = cal_3.get(Calendar.YEAR);
			int year_2 = cal_1.get(Calendar.YEAR) + 1;
			return year_2 - year_1;
		} else {
			Calendar cal_3 = Calendar.getInstance();
			cal_3.setTime(dateOfBirth);
			int year_1 = cal_3.get(Calendar.YEAR);
			int year_2 = cal_1.get(Calendar.YEAR);
			return year_2 - year_1;
		}
	}
	public static String getCurrencyFormatString(Double value) {
		DecimalFormat formatter = new DecimalFormat("##,###.00");
		return formatter.format(value);
	}
	
    /**
     * @param Date
     * @param String return Date string with dateFormat.
     */
    public static String formattedDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static double getPercentOn(double percent, double amount, double baseValue) {
        return (amount / baseValue) * percent;
    }
    
    /**
     * @param Date : if Date, return Date eg. 12-30-2000.
     */
    public static String formattedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
