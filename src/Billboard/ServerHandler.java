package Billboard;

import java.sql.*;

public class ServerHandler {

    public static final String DELETE_SQL = "call deletePerson(?)";

    public static final String INSERT_SQL = "call addName(?, ?)";

    public static final String SELECT_SQL = "call display()";

    public static final String UPDATE_SQL = "call updateAge(?, ?)";

    private Connection connection;
    private CallableStatement deletePerson;
    private CallableStatement insert;
    private CallableStatement select;
    private CallableStatement updateAge;

    public ServerHandler() {
        connection = DBConnection.getInstance();
        try {
            select = connection.prepareCall(SELECT_SQL);
            insert = connection.prepareCall(INSERT_SQL);
            updateAge = connection.prepareCall(UPDATE_SQL);
            deletePerson = connection.prepareCall(DELETE_SQL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void close() {
        try {
            select.close();
            insert.close();
            updateAge.close();
            deletePerson.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    private void run() {
        try {
            insert.setString(1, "Zaphod Beeblebrox");
            insert.setInt(2, 26);
            insert.executeUpdate();
            insert.setString(1, "Arthur Dent");
            insert.setInt(2, 26);
            insert.executeUpdate();
            insert.setString(1, "Trish McMillan");
            insert.setInt(2, 26);
            insert.executeUpdate();


            updateAge.setInt(1, 36);
            updateAge.setString(2, "Zaphod Beeblebrox");
            updateAge.executeUpdate();

            // reset to original database
            deletePerson.setString(1, "Zaphod Beeblebrox");
            deletePerson.executeUpdate();
            deletePerson.setString(1, "Arthur Dent");
            deletePerson.executeUpdate();
            deletePerson.setString(1, "Trish McMillan");
            deletePerson.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
