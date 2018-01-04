package org.zilker.raxit;

import java.util.logging.Logger;
import java.util.*;

public class RemoveHTMLTags {

	public static final Logger logger = Logger.getLogger(DayFromDOB.class.getName());

	// Driver function
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		logger.info("Enter the string : ");
		String s = scn.nextLine();
		logger.info("The string without HTML tags is : " + s.replaceAll("\\<.*?\\>", ""));
		scn.close();
	}

}
