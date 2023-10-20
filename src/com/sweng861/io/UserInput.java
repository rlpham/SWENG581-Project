package com.sweng861.io;

import java.util.Scanner;

public class UserInput {
	
	String q = null;
	Scanner scanner = new Scanner(System.in);

	
	// Gets user input and does client side validation on the input.
	public String getUserInput() throws Exception {
	
		System.out.print("Enter Song or Artist or exit: ");
		q = scanner.nextLine();
		while(!isValidInput(q)) {
			System.out.print("Invalid input. Enter Song or Artist or exit:");
			q = scanner.nextLine();
		}
		return q;
	}
	
	// Checks if the input is an empty string and returns boolean value
	boolean isValidInput(String q) {
		if(q.equals("")) {
			return false;
		}
		
		return true;
	}

}
