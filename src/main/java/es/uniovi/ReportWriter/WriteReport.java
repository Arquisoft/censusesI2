package es.uniovi.ReportWriter;


import es.uniovi.parser.Voter;

public interface WriteReport {

	public void write(Voter voter, Exception e);
	public String getReportDirectory();
	
}
