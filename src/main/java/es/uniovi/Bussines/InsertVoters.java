package es.uniovi.Bussines;

import es.uniovi.Persistence.VotersGateway;
import es.uniovi.parser.Voter;
import es.uniovi.util.Jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dax on 18-Feb-16.
 */
public class InsertVoters {

    public void insert(List<Voter> voters) {
        Connection conn = null;
        try {
            conn = Jdbc.getConnection();
            conn.setAutoCommit(false);
            VotersGateway vg = new VotersGateway();
            vg.setConnection(conn);
            vg.insertVoters(voters);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Jdbc.close(conn);
        }
    }
}
