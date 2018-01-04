package org.zilker.raxit;

import java.text.ParseException;
import java.text.*;
import java.util.*;
import java.util.logging.Logger;

public class CompareDate {

	public static final Logger logger = Logger.getLogger(CompareDate.class.getName());

	public static void compareDates(String date1, String date2) {
		// SimpleDateFormat class for formatting the date.
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		Date d1 = null, d2 = null;
		try {
			d1 = formatDate.parse(date1);
			// compareTo() method compares two dates
		} catch (ParseException e) {
			logger.info("Enter the date1 in dd-mm-yyyy format");
		}
		try{
			d2 = formatDate.parse(date2);
		} catch (Exception e) {
			logger.info("Enter the date2 in dd-mm-yyyy format");
		}
		if (d1.compareTo(d2) < 0) {
			logger.info("Date1 is before Date2");
		} else if (d1.compareTo(d2) > 0) {
			logger.info("Date1 is after Date2");
		} else {
			logger.info("Date1 is same as Date2");
		}

	}

	// Driver function
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String date1, date2;
		logger.info("Enter date1 in the format dd-mm-yyyy : ");
		date1 = scn.next();
		logger.info("Enter date2 in the format dd-mm-yyyy : ");
		date2 = scn.next();
		compareDates(date1, date2);
		scn.close();
	}

}
