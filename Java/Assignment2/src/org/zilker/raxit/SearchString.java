package org.zilker.raxit;

import java.util.*;
import java.util.logging.*;

public class SearchString {

	public static final Logger logger = Logger.getLogger(SearchString.class.getName());

	public static boolean findString(String[] stringArray, String userString, int n) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			// equals() method checks string is totally present or not in another string
			if (stringArray[i].equals(userString)) {
				logger.info("The user string " + userString + " is completely present in " + stringArray[i]);
				count++;
			}
			// contains() method checks string is partially5! present or not in another
			// string
			else if (stringArray[i].contains(userString)) {
				logger.info("The user string " + userString + " is partially present in " + stringArray[i]);
				count++;
			}
		}
		if (count > 0) {
			logger.info("The number of occurrences when user string occurs in the array is : " + count);
			return true;
		}
		return false;
	}

	// Driver function
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		String[] stringArray = new String[20];
		int n = 0, i;
		String userString = null;
		logger.info("Enter the number of strings : ");
		try {
			n = scn.nextInt();
			logger.info("Enter the strings : ");
			for (i = 0; i < n; i++) {
				stringArray[i] = scn.next();
			}
			logger.info("Enter the string to search : ");
			userString = scn.next();
		} catch (InputMismatchException e) {
			logger.info("Please enter a number.");
			return;
		} finally {
			scn.close();
		}
		if (!findString(stringArray, userString, n)) {
			logger.info("The user string is absent");
		}
	}

}
