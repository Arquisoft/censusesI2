package es.uniovi.asw;


import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import es.uniovi.parser.LetterWriter;
import es.uniovi.parser.Voter;


public class LetterTest {
	
	
	Voter voter = new Voter("testname", "testemail", "testNIF", 123, "testPassword");
	LetterWriter testLW = new LetterWriter();
	
	@Test
	public void test() {
		testLW.writeLetter(voter);
		File testF = new File(testLW.getLetterDirectory()+"/testNIF.txt");
		assertTrue(testF.exists());
		try {
			@SuppressWarnings("resource")
			BufferedReader testR = new BufferedReader(new FileReader(testF));
			testR.readLine();
			testR.readLine();
			assertTrue(testR.readLine().contains("testemail"));
			assertTrue(testR.readLine().contains("testPassword"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
