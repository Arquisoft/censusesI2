package es.uniovi.parser;

import java.io.File;
import java.io.IOException;

public abstract class LetterWriter {

	protected File parentDir;

	public LetterWriter() {
		parentDir = new File("letters");
		parentDir.mkdir();
		try {
			System.out.println("\nLetter's URL: \n"
					+ parentDir.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void writeLetter(Voter voter);

	public String getLetterDirectory() {
		try {
			return parentDir.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	protected String getText(Voter voter) {
		return "You have been correctly inserted into our system. "
				+ "You may now access our web service using the following information:"
				+ "\n\n\tlogin:" + voter.getEmail() + "\n\tpassword:"
				+ voter.getPassword();
	}
}
