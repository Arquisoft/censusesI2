package es.uniovi.parser;

import es.uniovi.Bussines.InsertVoters;

import java.io.IOException;
import java.util.List;

public class Parser {
	
	public void ReadFile(String file, VoterFileReader reader){
		try {
			List<Voter> voters = reader.read(file);
			InsertVoters iv = new InsertVoters();
			iv.insert(voters);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//update database...(reader.getVoters())
	}
	//public update database...
}
