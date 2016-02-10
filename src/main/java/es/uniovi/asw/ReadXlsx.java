package es.uniovi.asw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXlsx {
	private String fileName;
	private XSSFRow row;

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
            Iterator < Cell > cellIterator = row.cellIterator();
            while ( cellIterator.hasNext()) 
            {
               Cell cell = cellIterator.next();
               switch (cell.getCellType()) 
               {
                  case Cell.CELL_TYPE_NUMERIC:
                  //TODO
                  break;
                  case Cell.CELL_TYPE_STRING:
                  //TODO
                  break;
               }
            }
        }

	}

}
