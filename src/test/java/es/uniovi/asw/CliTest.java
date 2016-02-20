package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.parser.Parser;
import es.uniovi.parser.ReadXlsx;

public class CliTest {
	
	String[] s = {"-test", "This is a test."};
	String testText;
	@Test
	public void test() {
		Cli console = new Cli(s);
		console.parse();
	}
}
