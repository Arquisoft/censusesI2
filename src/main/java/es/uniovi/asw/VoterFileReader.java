package es.uniovi.asw;

import java.io.IOException;
import java.util.List;

public interface VoterFileReader {
	public void read(String fileName) throws IOException;
	public List<Voter> getVoters();
}
