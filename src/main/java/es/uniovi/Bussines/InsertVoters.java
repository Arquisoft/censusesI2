package es.uniovi.Bussines;

import es.uniovi.Persistence.VotersGateway;
import es.uniovi.parser.Voter;
import es.uniovi.util.FormFormat;
import es.uniovi.util.Jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * Created by Dax on 18-Feb-16.
 */
public class InsertVoters {

	public List<Voter> insert(List<Voter> voters) {
		try {
			verifyData(voters);
		} catch (VerificationException e1) {
			//TODO:Create write report writer.
			e1.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = Jdbc.getConnection();
			conn.setAutoCommit(false);
			VotersGateway vg = new VotersGateway();
			vg.setConnection(conn);
			voters = vg.insertVoters(voters);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(conn);
			return voters;
		}
	}

	private void verifyData(List<Voter> voters) throws VerificationException {
		for(Voter voter : voters){
			if(!FormFormat.validateNif(voter.getNif())){
				throw new VerificationException();
			}
			if(!FormFormat.validateEmail(voter.getEmail())){
				throw new VerificationException();
			}
		}
	}
}
