package es.uniovi.parser;

import es.uniovi.DBUpdate.Insert;
import es.uniovi.DBUpdate.InsertP;

import java.io.IOException;
import java.util.List;

public class RCensus implements ReadCensus{
	
	public void ReadFile(String file, VoterFileReader reader, LetterWriter writer){
		try {
			List<Voter> voters = reader.read(file);
			Insert iv = new InsertP();
			voters = iv.insert(voters);
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
