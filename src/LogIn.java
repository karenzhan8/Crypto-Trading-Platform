

public class LogIn {
	
	public boolean verify (String user, String pass) {
		File file = new File("login.txt");
		Scanner input = new Scanner (file);
		
		String[] curr = {"", ""};
		String currUser = "";
		String currPass = "";
		
		while (scanner.hasNextLine()) {
			curr = scanner.next().split(" ");
			if (curr[0].equals(user) && curr[1].equals(pass)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main (String[] args) {
		System.out.println(verify());
	}
	
}
