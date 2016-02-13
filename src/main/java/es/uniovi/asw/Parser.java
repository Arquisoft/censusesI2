package es.uniovi.asw;

import java.io.IOException;

public class Parser {
	
	public void ReadFile(String file, VoterFileReader reader){
		try {
			reader.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//update database...(reader.getVoters())
	}
	//public update database...
}
