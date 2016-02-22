package es.uniovi.asw;


import org.junit.Test;


public class CliTest {
	
	String[] s = {"-test", "This is a test.","-ic", "-od"};
	String testText;
	@Test
	public void test() {
		Cli console = new Cli(s);
		console.parse();
	}
}
