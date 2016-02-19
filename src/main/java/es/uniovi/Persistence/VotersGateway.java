package es.uniovi.Persistence;

import es.uniovi.parser.Voter;
import es.uniovi.util.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dax on 18-Feb-16.
 */
public class VotersGateway {

    private static final String INSERT_VOTERS = "INSERT INTO voters(name,email,nif,polling_station_number)" +
            " VALUES(?,?,?,?)";

    private Connection conn;


    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public void insertVoters(List<Voter> voters) {
        PreparedStatement pst = null;
        try{
            pst = conn.prepareStatement(INSERT_VOTERS);
            for (Voter voter :
                    voters) {
                pst.setString(1,voter.getName());
                pst.setString(2,voter.getEmail());
                pst.setString(3,voter.getNif());
                pst.setInt(4,voter.getPollStCode());
                pst.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Jdbc.close(pst);
        }

    }
}
