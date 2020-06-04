package Database;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private static Connection instance = null;

    private DBConnection(){
        Properties props = new Properties();
        FileInputStream in = null;
        try{
            in = new FileInputStream(("./db.props"));
            props.load(in);
            in.close();

            // specify the data source, username and password
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String schema = props.getProperty("jdbc.schema");

            instance = DriverManager.getConnection(url+ "/" + schema, username, password);

            JOptionPane.showMessageDialog(null, "Login Successful...");

        }
        catch (SQLException | FileNotFoundException sqle) {
            System.err.println(sqle);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DBConnection();
        }
        return instance;
    }
}
