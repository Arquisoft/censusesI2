package es.uniovi.asw;


import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import es.uniovi.parser.LetterWriter;
import es.uniovi.parser.LetterWriterDoc;
import es.uniovi.parser.LetterWriterTxt;
import es.uniovi.parser.Voter;


public class LetterTest {
	
	
	Voter voter = new Voter("testname", "testemail", "testNIF", 123, "testPassword");
	LetterWriter testTxt = new LetterWriterTxt();
	LetterWriter testDoc = new LetterWriterDoc();
	private XWPFWordExtractor extract;
	
	@Test
	public void testTxt() {
		testTxt.writeLetter(voter);
		File testF = new File(testTxt.getLetterDirectory()+"/testNIF.txt");
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
	
	@Test
	public void testDoc() {
		testDoc.writeLetter(voter);
		File testF = new File(testTxt.getLetterDirectory()+"/testNIF.doc");
		assertTrue(testF.exists());
		try {
			FileInputStream fis = new FileInputStream(new File(testDoc.getLetterDirectory()+"/testNIF.doc"));

	        XWPFDocument doc = new XWPFDocument(fis);
	        extract = new XWPFWordExtractor(doc);
	        String content = extract.getText();
	        assertTrue(content.contains("testemail"));
			assertTrue(content.contains("testPassword"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
