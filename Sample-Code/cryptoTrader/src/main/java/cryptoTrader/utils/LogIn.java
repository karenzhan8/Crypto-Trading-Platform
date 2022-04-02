package cryptoTrader.utils;

import java.io.*;
import java.util.*;

public class LogIn {
	public boolean verify (String user, String pass) {
		try {
			File file = new File("login.txt");
			Scanner input = new Scanner(file);
			String[] curr = {"hello", "world"};
			
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
}
