package es.uniovi.asw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXlsx {
	private String fileName;
	private XSSFRow row;
	private List<Voter> voters = new ArrayList<Voter>();
	private PasswordGenerator paswdGen = new PasswordGenerator();

	public ReadXlsx(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public void read() throws IOException{
		FileInputStream fis = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator < Row > rowIterator = spreadsheet.iterator();
        while (rowIterator.hasNext()) 
        {
        	row = (XSSFRow) rowIterator.next();
        	String name=row.getCell(0).getStringCellValue();
        	String email=row.getCell(1).getStringCellValue();
        	String nif=row.getCell(2).getStringCellValue();
        	String pollStCode=row.getCell(3).getStringCellValue();
            voters.add(new Voter(name,email,nif,pollStCode,paswdGen.getNewPassword()));
        }

	}
	
	public List<Voter> getVoters() {
		return voters;
	}

}
