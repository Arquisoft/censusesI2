package es.uniovi.asw;

import es.uniovi.DBUpdate.Insert;
import es.uniovi.DBUpdate.InsertP;
import es.uniovi.DBUpdate.VotersGateway;
import es.uniovi.DBUpdate.util.Jdbc;
import es.uniovi.ReportWriter.WReportP;
import es.uniovi.parser.Voter;
import org.junit.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dax on 22-Feb-16.
 */
public class InsertTest {

    private List<Voter> voters;
    Connection conn = null;
    @Before
    public void setUp(){
        voters = new ArrayList<Voter>();
        try {
            conn = Jdbc.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Couldn't connect with the database " + e.getMessage());
        }
    }

    @After
    public void disconnect() {
        Jdbc.close(conn);
    }

    @AfterClass
    public static void clearDatabase() {
        clearDatabaseInsertions();
    }

    @Test
    public void testInsertion(){
        voters.add(new Voter("test","aga@gmail.com","12345678T",2,"testPass"));
        Insert iv = new InsertP();
        int numberOfVotersInDb = countVoters();
        iv.insert(voters);
        int newNumberOfVotersInDb = countVoters();
        Assert.assertEquals(numberOfVotersInDb+1,newNumberOfVotersInDb);
        numberOfVotersInDb = newNumberOfVotersInDb;
        iv.insert(voters);
        newNumberOfVotersInDb = countVoters();
        Assert.assertEquals(numberOfVotersInDb,newNumberOfVotersInDb);//Repeated NIF, wasn't inserted
    }

    @Test
    public void testMultipleInsertions(){
        voters.add(new Voter("test1","1@gmail.com","11111111T",2,"test1Pass"));
        voters.add(new Voter("test2","2@gmail.com","12222222T",2,"test2Pass"));
        voters.add(new Voter("test3","3@gmail.com","13333333T",2,"test3Pass"));
        voters.add(new Voter("test4","4@gmail.com","14444444T",2,"test4Pass"));
        Insert iv = new InsertP();
        int numberOfVotersInDb = countVoters();
        iv.insert(voters);
        int newNumberOfVotersInDb = countVoters();
        Assert.assertEquals(numberOfVotersInDb+4,newNumberOfVotersInDb);
    }

    @Test
    public void testInvalidFormatInsertions(){
        voters.add(new Voter("test","agagmail.com","12345679T",2,"testPass"));//Wrong email, no @
        voters.add(new Voter("test","aga@gmailcom","12345679T",2,"testPass"));//Wrong email, no .com,es,etc
        voters.add(new Voter("test","aga@gmail.com","1234567T",2,"testPass"));//Wrong nif, less than 8 numbers
        voters.add(new Voter("test","aga@gmail.com","12345679",2,"testPass"));//Wrong nif, no letter
        Insert iv = new InsertP();
        int numberOfVotersInDb = countVoters();
        iv.insert(voters);
        int newNumberOfVotersInDb = countVoters();
        Assert.assertEquals(numberOfVotersInDb,newNumberOfVotersInDb);
    }

    @Test
    public void testVotersGateWay(){
        VotersGateway vg = new VotersGateway();
        Connection conn = null;
        try {
            conn = Jdbc.getConnection();
            vg.setConnection(conn);
            voters.add(new Voter("test1","1@gmail.com","11111111T",2,"test1Pass"));
            voters.add(new Voter("test2","2@gmail.com","12222222T",2,"test2Pass"));
            voters.add(new Voter("test3","3@gmail.com","13333333T",2,"test3Pass"));
            voters.add(new Voter("test4","4@gmail.com","14444444T",2,"test4Pass"));
            vg.insertVoters(voters,new WReportP());
            Assert.assertEquals(4, countVoters());

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            clearDatabase();
            Jdbc.close(conn);
        }
    }


    private static void clearDatabaseInsertions(){
        Connection conn = null;
        Statement stm = null;
        try {
            conn = Jdbc.getConnection();
            stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM voters WHERE nif LIKE '1%'");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Jdbc.close(stm);
            Jdbc.close(conn);
        }
    }

    private int countVoters() {
        int numberOfVoters = 0;
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT COUNT(nif) as voters FROM voters");
            while(rs.next()) {
                numberOfVoters = rs.getInt("voters");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            Jdbc.close(rs,stm);
        }
        return numberOfVoters;
    }
}
