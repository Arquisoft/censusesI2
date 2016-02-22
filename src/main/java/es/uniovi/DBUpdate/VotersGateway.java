package es.uniovi.DBUpdate;

import es.uniovi.DBUpdate.util.Jdbc;
import es.uniovi.ReportWriter.WReportP;
import es.uniovi.parser.Voter;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dax on 18-Feb-16.
 */
public class VotersGateway {

    private static final String INSERT_VOTERS = "INSERT INTO voters(name,email,nif,polling_station_number,password)" +
            " VALUES(?,?,?,?,?)";

    private Connection conn;


    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public List<Voter> insertVoters(List<Voter> voters, WReportP reportWriter) {
        PreparedStatement pst = null;
        List<Voter> votersAdded = new ArrayList<Voter>();
        try {
            for (Voter voter :
                    voters) {
                pst = conn.prepareStatement(INSERT_VOTERS);
                try {
                    pst.setString(1, voter.getName());
                    pst.setString(2, voter.getEmail());
                    pst.setString(3, voter.getNif());
                    pst.setInt(4, voter.getPollStCode());
                    pst.setString(5, encryptPass(voter));
                    pst.executeUpdate();
                    votersAdded.add(voter);
                } catch (SQLException c) {
                    if (c.getErrorCode() == 0) {
                        reportWriter.write(voter, new SQLException("Voter's NIF already in database"));
                    } else {
                        System.out.println(c.getErrorCode());
                    }
                } catch (RuntimeException r) {
                    reportWriter.write(voter, r);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());
            e.printStackTrace();
        } finally {
            Jdbc.close(pst);
        }

        return votersAdded;
    }

    private String encryptPass(Voter voter){
        // Hash a password for the first time
        String hashed = BCrypt.hashpw(voter.getPassword(), BCrypt.gensalt());

    /*
        Check that an unencrypted password matches one that has
        previously been hashed
    */
        if (!BCrypt.checkpw(voter.getPassword(), hashed))
            throw new RuntimeException("The password failed the encryption process");

        return hashed;
    }
}
