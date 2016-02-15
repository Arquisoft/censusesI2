package es.uniovi.parser;

import java.security.SecureRandom;
import java.math.BigInteger;

public final class PasswordGenerator {
	private SecureRandom random = new SecureRandom();
	
	public String getNewPassword(){
		return new BigInteger(130, random).toString(32);
	}
}
