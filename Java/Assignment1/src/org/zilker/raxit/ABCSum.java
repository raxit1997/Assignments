package org.zilker.raxit;
import java.util.*;

public class ABCSum {
	public static void main(String[] args) {
		int a, b, c, sum = 0;
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the three numbers : ");
		a = scn.nextInt();
		b = scn.nextInt();
		c = scn.nextInt();
		if(a == b) {
			if(b == c) sum = c;
			else sum = b + c;
		}
		else {
			if(b == c) sum = a + b;
			else sum = a + b + c;
		}
		System.out.println("The sum is : " + sum);
	}
}
