package es.uniovi.asw;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import es.uniovi.parser.ReadCsv;
import es.uniovi.parser.ReadXlsx;
import es.uniovi.parser.Voter;
import es.uniovi.parser.VoterFileReader;

import org.junit.Assert;

public class ReadFileTest {

	private VoterFileReader readerExcel = new ReadXlsx();
	private VoterFileReader readerCSV = new ReadCsv();
	
	List<Voter> testList;
	
	@Test
	public void testReadExcel() {
		try {
			testList=readerExcel.read("src/test/resources/test.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals("Juan Torres Pardo", testList.get(0).getName());
		Assert.assertEquals("90500084Y", testList.get(0).getNif());
		Assert.assertEquals("juantorres@yahoo.com", testList.get(0).getEmail());
		Assert.assertEquals(234, testList.get(0).getPollStCode());
		Assert.assertEquals("Luis López Fernando", testList.get(1).getName());
		Assert.assertEquals("19160962F", testList.get(1).getNif());
		Assert.assertEquals("luislopez@gmail.com", testList.get(1).getEmail());
		Assert.assertEquals(345, testList.get(1).getPollStCode());
		Assert.assertEquals("Ana Torres Pardo", testList.get(2).getName());
		Assert.assertEquals("09940449X", testList.get(2).getNif());
		Assert.assertEquals("anatorres@hotmail.com", testList.get(2).getEmail());
		Assert.assertEquals(234, testList.get(2).getPollStCode());

	}
	
	@Test
	public void testReadCSV() {
		try {
			testList=readerCSV.read("src/test/resources/test.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals("Juan Torres Pardo", testList.get(0).getName());
		Assert.assertEquals("90500084Y", testList.get(0).getNif());
		Assert.assertEquals("juantorres@yahoo.com", testList.get(0).getEmail());
		Assert.assertEquals(234, testList.get(0).getPollStCode());
		Assert.assertEquals("Luis López Fernando", testList.get(1).getName());
		Assert.assertEquals("19160962F", testList.get(1).getNif());
		Assert.assertEquals("luislopez@gmail.com", testList.get(1).getEmail());
		Assert.assertEquals(345, testList.get(1).getPollStCode());
		Assert.assertEquals("Ana Torres Pardo", testList.get(2).getName());
		Assert.assertEquals("09940449X", testList.get(2).getNif());
		Assert.assertEquals("anatorres@hotmail.com", testList.get(2).getEmail());
		Assert.assertEquals(234, testList.get(2).getPollStCode());

	}

}
