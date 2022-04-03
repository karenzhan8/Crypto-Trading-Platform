package cryptoTrader.utils;

import java.io.*;
import java.util.*;

public class LogIn {
	public boolean verify (String user, String pass) {
		try {
			//read input from file
			Scanner input = new Scanner(new File("login.txt"));
			String[] curr = {"", ""}; 
			
			while (input.hasNextLine()) {
				curr = input.nextLine().split(" ");
				
				//if user and password matches, return true 
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
}
