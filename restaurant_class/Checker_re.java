package restaurant_project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker_re{
	public static boolean isValidName(String n)
	{
		String reg="^[A-Z][a-z]*( [A-Z][a-z]+)*$";
		return Pattern.matches(reg,n);
	}
	public static boolean valEmail(String e)
	{
		String Ereg="^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@" 
		        + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
		Pattern emailPat=Pattern.compile(Ereg,Pattern.CASE_INSENSITIVE);
		Matcher match=emailPat.matcher(e);
		return match.find();
	}
	
	public static boolean isvalPass(String pass)
	{
		String regex="^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(pass);
		return m.matches();
	}
	public static String digestMsg(String str)
	{
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte arr[]=md.digest();
			return new String(arr);
			
		} catch (NoSuchAlgorithmException e) {
		
			e.printStackTrace();
			return "unsuccess";
		}
	}	
	

}
