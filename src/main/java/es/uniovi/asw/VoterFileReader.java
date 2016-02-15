package es.uniovi.asw;

import java.io.IOException;
import java.util.List;

public interface VoterFileReader {
	public List<Voter> read(String fileName) throws IOException;
}
