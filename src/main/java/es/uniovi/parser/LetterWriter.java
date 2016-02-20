package es.uniovi.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LetterWriter {

	public static void writeLetter(Voter voter) {
		BufferedWriter writer = null;
		try {
            File letter = new File("./letters/"+voter.getNif()+".txt");

            writer = new BufferedWriter(new FileWriter(letter));
            writer.write("You have been correctly inserted into our system. You may now access our web service using the following information:\n\r\n\r\tlogin:" + voter.getEmail() + "\n\r\tpassword:" + voter.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
	}

}
