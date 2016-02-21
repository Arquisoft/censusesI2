package es.uniovi.parser;

import es.uniovi.Bussines.InsertVoters;

import java.io.IOException;
import java.util.List;

public class Parser {
	
	public void ReadFile(String file, VoterFileReader reader){
		try {
			List<Voter> voters = reader.read(file);
			InsertVoters iv = new InsertVoters();
			voters = iv.insert(voters);
			LetterWriter writer= new LetterWriter();
			for (Voter voter :
					voters) {
				writer.writeLetter(voter);
			}
		} catch (IOException e) {
			System.out.println("Error trying to read the file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
