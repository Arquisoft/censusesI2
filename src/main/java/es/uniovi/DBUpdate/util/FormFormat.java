package es.uniovi.DBUpdate.util;

import java.util.regex.*;

public class FormFormat {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern VALID_NIF_REGEX =
		Pattern.compile("(^[0-9]{8}[A-Z]{1}$)");
	
	public static boolean validateEmail(String emailStr) {
	    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	    return matcher.find();
	}
	
	public static boolean validateNif(String nifStr){
		Matcher matcher = VALID_NIF_REGEX .matcher(nifStr);
		return matcher.find();
	}
}
