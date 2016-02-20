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
			for (Voter voter :
					voters) {
				System.out.printf("Voter %s with nif %s and email %s votes in polling station %d\n", voter.getName(), voter.getNif(), voter.getEmail(), voter.getPollStCode());
				LetterWriter.writeLetter(voter);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
