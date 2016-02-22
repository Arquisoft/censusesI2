package es.uniovi.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCsv implements VoterFileReader {
	private List<Voter> voters = new ArrayList<Voter>();
	private PasswordGenerator paswdGen = new PasswordGenerator();

	public List<Voter> read(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = "";
		int count = 1;
		{
			try {
				br.readLine();
				while ((line = br.readLine()) != null) {
					// use comma as separator
					String[] split = line.split(",");

					String name = split[0];
					String nif = split[1];
					String email = split[2];
					int pollStCode = Integer.parseInt(split[3]);
					voters.add(new Voter(name, email, nif, pollStCode, paswdGen
							.getNewPassword()));
					count++;

				}
			} catch (IllegalStateException e) {
				System.out.println("Error in line: " + count + "\t"
						+ e.getMessage());
			}
		}
		br.close();
		return voters;
	}

}
