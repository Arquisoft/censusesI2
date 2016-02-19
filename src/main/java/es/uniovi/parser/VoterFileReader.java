package es.uniovi.parser;

import java.io.IOException;
import java.util.List;

import es.uniovi.Bussines.VerificationException;

public interface VoterFileReader {
	public List<Voter> read(String fileName) throws IOException;
}
