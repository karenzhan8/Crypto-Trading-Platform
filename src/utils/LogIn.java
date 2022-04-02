package utils;

import java.io.*;
import java.util.*;

public class LogIn {
	public static boolean verify (String user, String pass) {
		try {
			Scanner input = new Scanner(new File("login.txt"));
			String[] curr = {"", ""}; 
			
			while (input.hasNextLine()) {
				curr = input.nextLine().split(" ");
				
				if (curr[0].equals(user) && curr[1].equals(pass)) {
					return true;
				}
			}
			return false;	
		} 
		catch (FileNotFoundException e) {
			return false;
		}
	}
	public static void main (String[] args) {
		System.out.println(verify("karen", "password"));
	}
}
