package es.uniovi.ReportWriter;

import java.sql.SQLException;

import es.uniovi.parser.Voter;

public interface WriteReport {

	public void write(Voter voter, SQLException e);
	
}
