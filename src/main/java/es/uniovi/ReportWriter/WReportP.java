package es.uniovi.ReportWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.uniovi.DBUpdate.Bussines.VerificationException;
import es.uniovi.parser.Voter;

public class WReportP implements WriteReport {

    private File logFile;

    public WReportP() {
        File parentDir = new File("reports");
        parentDir.mkdir();
        logFile = new File(parentDir, "report.txt");
        try {
            System.out.println(logFile.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Voter voter, Exception exc) {
        BufferedWriter writer = null;
        String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        try {
            if (exc instanceof SQLException) {
                System.out.println("Error in Database insertion(check report.txt)");
            }
            if (exc instanceof VerificationException) {
                System.out.println("Error in input data format(check report.txt)");
            }
            writer = new BufferedWriter(new FileWriter(logFile, true));
            writer.append("[" + timeLog + "]:Error when inserting voter: " + voter.toString() +
                    "\tError message: " + exc.getMessage());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getReportDirectory() {
		try {
			return logFile.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
