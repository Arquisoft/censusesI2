package es.uniovi.DBUpdate;

import es.uniovi.DBUpdate.Bussines.VerificationException;
import es.uniovi.DBUpdate.util.FormFormat;
import es.uniovi.DBUpdate.util.Jdbc;
import es.uniovi.ReportWriter.WReportP;
import es.uniovi.parser.Voter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dax on 18-Feb-16.
 */
public class InsertP implements Insert{
	WReportP reportWriter = new WReportP();

	public List<Voter> insert(List<Voter> voters) {
		List<Voter> validVoters = verifyData(voters);
		Connection conn = null;
		try {
			conn = Jdbc.getConnection();
			conn.setAutoCommit(false);
			VotersGateway vg = new VotersGateway();
			vg.setConnection(conn);
			validVoters = vg.insertVoters(validVoters, reportWriter);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(conn);
		}

		return validVoters;
	}

	private List<Voter> verifyData(List<Voter> voters) {
		List<Voter> validVoters = new ArrayList<Voter>();
		boolean valid;
		for (Voter voter : voters) {
			valid = true;
			if (!FormFormat.validateNif(voter.getNif())) {
				valid = false;
				reportWriter.write(voter, new VerificationException(
						"Incorrect NIF format"));
			}
			if (!FormFormat.validateEmail(voter.getEmail())) {
				valid = false;
				reportWriter.write(voter, new VerificationException(
						"Incorrect email format"));
			}
			if (valid) {
				validVoters.add(voter);
			}
		}
		return validVoters;
	}
}
