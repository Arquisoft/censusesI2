package es.uniovi.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXlsx implements VoterFileReader{
	private XSSFRow row;
	private List<Voter> voters = new ArrayList<Voter>();
	private PasswordGenerator paswdGen = new PasswordGenerator();

	
	public List<Voter> read(String fileName) throws IOException{
		FileInputStream fis = new FileInputStream(new File(fileName));
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator < Row > rowIterator = spreadsheet.iterator();
        row = (XSSFRow) rowIterator.next();
        while (rowIterator.hasNext()) 
        {
			try {
				row = (XSSFRow) rowIterator.next();
				String name = row.getCell(0).getStringCellValue();
				String nif = row.getCell(1).getStringCellValue();
				String email = row.getCell(2).getStringCellValue();
				int pollStCode = (int) row.getCell(3).getNumericCellValue();
				voters.add(new Voter(name, email, nif, pollStCode, paswdGen.getNewPassword()));
			}catch (IllegalStateException e){
				System.out.println("Error in line: "+ row.getRowNum() + "\t" + e.getMessage());
			}
        }
        workbook.close();
        return voters;
	}

}
