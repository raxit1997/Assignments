package org.zilker.raxit;
import java.util.*;
import java.util.logging.*;

public class DigitSumInString {
	
	private static final Logger logger = Logger.getLogger(DigitSumInString.class.getName());
	
	public static void main(String args[]) {
		Scanner scn = new Scanner(System.in);
		logger.info("Enter the string : ");
		String s = scn.next();
		int c = 0, j, sum = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) <= 57 && s.charAt(i) >= 48) {
				j = s.charAt(i) - 48;
				sum = sum + j;
			}
		}
		logger.info("The sum is : " + sum);
	}

}
