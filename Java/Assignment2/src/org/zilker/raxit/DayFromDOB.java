package org.zilker.raxit;

import java.text.*;
import java.util.*;
import java.util.logging.Logger;

public class DayFromDOB {

	public static final Logger logger = Logger.getLogger(DayFromDOB.class.getName());

	public static void getDayOfWeek(String dob) {
		SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
		Date day_of_week = null;
		try {
			day_of_week = formatDate.parse(dob);
		} catch (ParseException e) {
			logger.info("Enter the date in dd-mm-yyyy format");
		}
		// EEEE gives the day of the week
		SimpleDateFormat getDayName = new SimpleDateFormat("EEEE");
		logger.info("The day of the week from your date of birth is : " + getDayName.format(day_of_week));
		// E represents day of week in short form and aa represents AM/PM
		SimpleDateFormat getFullFormat = new SimpleDateFormat("E yyyy.MM.dd hh:mm:ss aa");
		// Setting time zone to UTC
		getFullFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		logger.info("The given format for your date of your birth is  : " + getFullFormat.format(day_of_week) + " UTC");
	}

	// Driver function
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		logger.info("Enter your date of birth : ");
		String dob = scn.next();
		getDayOfWeek(dob);
		scn.close();
	}

}
