package es.uniovi.asw;


import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import es.uniovi.ReportWriter.WReportP;
import es.uniovi.ReportWriter.WriteReport;
import es.uniovi.parser.Voter;


public class ReportTest {
	
	
	Voter voter = new Voter("testname", "testemail", "testNIF", 123, "testPassword");
	WriteReport testReport = new WReportP();
	
	@Test
	public void test() {
		testReport.write(voter, new Exception("test"));
		File testF = new File(testReport.getReportDirectory());
		assertTrue(testF.exists());
		try {
			@SuppressWarnings("resource")
			BufferedReader testR = new BufferedReader(new FileReader(testF));
			String last = "", line;
			while((line = testR.readLine()) != null)
				last = line;
			assertTrue(last.contains("Error message: test"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
