package org.zilker.raxit;
import java.util.Scanner;
import java.util.logging.Logger;

public class RecursiveCount {
	
	public static final Logger logger = Logger.getLogger(RecursiveCount.class.getName());
	static int[] a = new int[100];
	private static Scanner scn;
	
	public static void main(String[] args) {
		scn = new Scanner(System.in);
		logger.info("Enter the number of elements : ");
		int n = scn.nextInt(), i;
		for(i = 0;i < n;i++) {
			a[i] = scn.nextInt();
		}
		int res = count11s(n);
		logger.info("The count is : " + res);
	}
	
	public static int count11s(int n) {
		if(n == 0) return 0;
		if(a[n-1] == 11) {
			return 1 + count11s(n-1);
		}
		else count11s(n-1);
		return 0;
	}
}
