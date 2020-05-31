package Billboard;

import java.awt.dnd.DragSourceDragEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Database {
    static Connection connection;




    public Database() throws SQLException {};
    public static void init() throws FileNotFoundException, SQLException {
               // // read KeyWestTemp.txt

        // create token
        String token1 = "";

        // for-each loop for calculating heat index of May - October

        // create script Scanner
        Scanner scriptScanner = new Scanner(new File(System.getProperty("user.dir")+"\\electronicBB.sql")).useDelimiter("\\$\\$");

        // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
        // List<String> temps = new LinkedList<String>();
        List<String> temps = new ArrayList<String>();

        // while loop
        while (scriptScanner.hasNext()) {
            // find next line
            token1 = scriptScanner.next();
            temps.add(token1);
        }
        scriptScanner.close();

        String[] tempsArray = temps.toArray(new String[0]);
        Statement statement = connection.createStatement();

        for (String s : tempsArray) {
            statement.execute(s);
        }
        statement.close();
    }
    public static String[] RetrieveColumn(Statement st, String query) throws SQLException {
        String[] column;
        // get all current entries
        ResultSet rs = st.executeQuery(query);

        // use metadata to get the number of columns
        column = new String[rs.getMetaData().getColumnDisplaySize(1)];
        int pointer=0;
        // output each row
        while (rs.next()) {
            column[pointer] = rs.getString(1);
            if(column[pointer] != null){
                pointer++;
            }
        }
        String[] result = new String[pointer];
        for(int i = 0; i < column.length; i ++){
            if(column[i] != null){
                result[i] = column[i];
            }
        }
        System.out.println(Arrays.toString(result));
        return column;
    }

    //TODO: RUN THIS VOID MAIN TO INITIALISE DATABASE.
    // WHEN APPLY TO PROGRAM REMOVE THIS AND ADD TO BILLBOARD SERVER
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
        Statement statement = connection.createStatement();
        Database.init();
        Database.RetrieveColumn(statement, "Call DisplayUserColumnName()");
    }
}
