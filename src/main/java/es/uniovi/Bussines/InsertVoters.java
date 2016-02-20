package es.uniovi.Bussines;

import es.uniovi.Persistence.VotersGateway;
import es.uniovi.ReportWriter.WReportP;
import es.uniovi.parser.Voter;
import es.uniovi.util.FormFormat;
import es.uniovi.util.Jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * Created by Dax on 18-Feb-16.
 */
public class InsertVoters {
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
		}finally {
			Jdbc.close(conn);
			return validVoters;
		}
	}

	private List<Voter> verifyData(List<Voter> voters) {
		List<Voter> validVoters = new ArrayList<>();
		boolean valid;
		for(Voter voter : voters){
			valid = true;
			if(!FormFormat.validateNif(voter.getNif())){
				valid = false;
				reportWriter.write(voter,new VerificationException("Incorrect NIF format"));
			}
			if(!FormFormat.validateEmail(voter.getEmail())){
				valid = false;
				reportWriter.write(voter,new VerificationException("Incorrect email format"));
			}
			if(valid){
				validVoters.add(voter);
			}
		}
		return validVoters;
	}
}
