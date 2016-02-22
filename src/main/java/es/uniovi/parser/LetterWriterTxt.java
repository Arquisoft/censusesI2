package es.uniovi.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LetterWriterTxt extends LetterWriter {

	public LetterWriterTxt() {
		super();
	}
	
	public void writeLetter(Voter voter) {
		BufferedWriter writer = null;
		try {
			File letter = new File(parentDir, voter.getNif() + ".txt");

			writer = new BufferedWriter(new FileWriter(letter));
			writer.write( getText(voter));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
