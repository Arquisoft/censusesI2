package es.uniovi.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LetterWriter {

    private File parentDir;

    public LetterWriter(){
        parentDir = new File("letters");
        parentDir.mkdir();
        try {
            System.out.println("\nLetter's URL: \n" + parentDir.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void writeLetter(Voter voter) {
		BufferedWriter writer = null;
		try {
            File letter = new File(parentDir,voter.getNif()+".txt");

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
