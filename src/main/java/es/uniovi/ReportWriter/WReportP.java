package es.uniovi.ReportWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.uniovi.parser.Voter;

public class WReportP implements WriteReport{

	public void write(Voter voter, SQLException sql) {
		BufferedWriter writer = null;
		try {
            //create a temporary file
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(timeLog);

            // This will output the full path where the file will be written to...
            System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("Error when inserting voter: " +voter.toString() + "\nError message: " +sql.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
	}
	
}
