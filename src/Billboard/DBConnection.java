package Billboard;
import javax.swing.*;
import java.sql.*;
public class DBConnection {
    public static Connection mariaDBConn(){
    Connection conn = null;
    try{
        Class.forName("");
        conn = DriverManager.getConnection();

        JOptionPane.showMessageDialog(null, "Success Login..");
        return conn;
    }
    catch(Exception E){
        JOptionPane.showMessageDialog(null,E);
        return null;
    }
    }
}
