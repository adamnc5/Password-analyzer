package passwordcheck;
import java.util.Scanner;

public class psswrdcheck {
	
	public static void main(String[] args) {
		String[] commonpassword = {"123456789", "password123@", "admin123@", "iloveyou2@", "qwertyuiop123@"};//most used password found in breaches
		
		Scanner in=new Scanner(System.in);
		System.out.println("Welcome to the login page:");
		String password="";
		boolean isValid=false;//We pretend that the password is already not valid and try to meet the condition to make it valid.
		while(!isValid) {
			System.out.println("Please enter your password, your password must be at least 8 characters with at least one digit and one special character:");

			password=in.nextLine();
		if(password.length()<=8) {	
			System.out.println("Your password is too short, try again:");
			continue;//If this condition is false, we will try again to write a new code with the condition considered.
		}
		boolean hasDigit=false;
		for(int i=0;i<password.length();i++) {	
			if(Character.isDigit(password.charAt(i)))
			{
				hasDigit=true;
				break;//break here make the code runs faster and no need to check the next character because the condition is already true.
			}
			}
		
		
		if(hasDigit==false) {	
			System.out.println("There is no number in your password, please try again using a number");
			continue;//If this condition is false, we will try again to write a new code with the condition considered.
		}
		boolean hasSpecial=false;
		for(int i=0;i<password.length();i++) {	
			char s=password.charAt(i);
			if(!Character.isLetterOrDigit(s) && !Character.isWhitespace(s))//if the character is not a space, number and letter then it's a special character.
			{	
			hasSpecial=true;
			break;//break here make the code runs faster and no need to check the next character because the condition is already true.
			}
		}
		if(hasSpecial==false) {	
			System.out.println("There is no special character in your password, please try again using at least one special character:");
			continue;
		}
		boolean isCommon=false;
		for(int i=0;i<commonpassword.length;i++) {	
			if(password.equals(commonpassword[i]))//check all the possibilities of the most used passwords
			{	
				isCommon=true;
				break;
			}
			
			}
		if(isCommon==true) {	
			System.out.println("Your password was been found in the most used password, please use a more secure one:");
			continue;
		}
		isValid=true;
	
	}
		for(int i=0;i<password.length();i++) {	
			System.out.print("*");
			
			
			
		}
		System.out.println();
		System.out.println("Password hidden for security");
	}
}
