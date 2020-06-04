package Database;

import ElectronicBillboardObject.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Database {
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    };

    public Database() throws SQLException {};

    //TODO: INIT THE DATABASE
    public static void init() throws FileNotFoundException, SQLException {

        // create token
        String token = "";
        // for-each loop for calculating heat index of May - October

        // create script Scanner
        Scanner scriptScanner = new Scanner(new File(System.getProperty("user.dir")+"\\electronicBB.sql")).useDelimiter("\\$\\$");

        // Original answer used LinkedList, but probably preferable to use ArrayList in most cases
        // List<String> temps = new LinkedList<String>();
        List<String> temps = new ArrayList<String>();

        // while loop
        while (scriptScanner.hasNext()) {
            // find next line
            token = scriptScanner.next();
            temps.add(token);
        }
        scriptScanner.close();

        String[] tempsArray = temps.toArray(new String[0]);
        Statement statement = connection.createStatement();

        for (String s : tempsArray) {
            statement.execute(s);
        }
        statement.execute("INSERT INTO billboards VALUES('COVID', 'admin', 'white', 'red', 'black', 'https://d2v9ipibika81v.cloudfront.net/uploads/sites/40/COVID-19.jpg', 'Wash your hand', 'Stay at home!');");
        statement.execute("INSERT INTO billboards VALUES('Alo', 'admin', 'yellow', 'blue', 'green', 'https://lh3.googleusercontent.com/v5ie2VWYRjmDV2b2s_rYrgrWcpFhFiUn0aQlf97W3VkGO84kofOu8psqBir1HZIfQts', 'Say hello', 'Sayhello10times!');");
        statement.execute("call addBillboard('COVID123', 'jadenB', 'white', 'red', 'black', 'https://d2v9ipibika81v.cloudfront.net/uploads/sites/40/COVID-19.jpg', 'Wash your hand', 'Stay at home!')");
        statement.close();
    }
    //TODO: USE THIS WHEN RETURN ONLY 1 COLUMN DATA
    public static String[] RetrieveColumnData(Statement st, String query) throws SQLException {
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
    //TODO: USE THIS WHEN SELECT * FROM A TABLE OR MULTI COLs
    // Return List<String[]> : List of rows (rows is String[])
    public static String[][] RetrieveData(Statement st, String query) throws SQLException {
        // get all current entries
        ResultSet rs = st.executeQuery(query);

        // use metadata to get the number of columns

        int columnCount = rs.getMetaData().getColumnCount();
//        colNames = new String[rowCount+1][columnCount];
        String[] colNames = new String[columnCount];
        for (int i=0; i< columnCount; i++){
            colNames[i] = rs.getMetaData().getColumnName(i + 1);
        }
        ArrayList<String[]> table = new ArrayList<>();
        table.add(colNames);
        while( rs.next()) {
            String[] row = new String[columnCount];
            for( int iCol = 1; iCol <= columnCount; iCol++ ){
                Object obj = rs.getObject( iCol );
                row[iCol-1] = (obj == null) ?null:obj.toString();
            }
            table.add(row);
        }
        //Return this as String[][]
        String[][] newTable = new String[table.size()][columnCount];
        //print result
        for( int i=0; i< table.size(); i++ ){
            for( String s: table.get(i)){
                System.out.print( " " + s );

            }
            newTable[i] = table.get(i);
            System.out.println();
        }
        System.out.println(Arrays.deepToString(newTable));
        return newTable;
    }
    //TODO: USE THIS WHEN PRINT RETURN OF A STATEMENT's EXECUTION
    private static void displayContents(Statement st, String query) throws SQLException {

        // get all current entries
        ResultSet rs = st.executeQuery(query);

        // use metadata to get the number of columns
        int columnCount = rs.getMetaData().getColumnCount();

        // output the column names
        for (int i = 0; i < columnCount; i++) {
            System.out.printf("%-20s", rs.getMetaData().getColumnName(i + 1));
        }
        System.out.printf("%n");

        // output each row
        while (rs.next()) {
            for (int i = 0; i < columnCount; i++) {
                System.out.printf("%-20s", rs.getString(i + 1));
            }
            System.out.printf("%n");
        }
        System.out.printf("%n");
    }

    //TODO: RUN THIS VOID MAIN TO INITIALISE DATABASE.
    // WHEN APPLY TO PROGRAM REMOVE THIS AND ADD TO BILLBOARD SERVER
    public static void main(String[] args) throws Exception {
        connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
        Statement statement = connection.createStatement();
        Database.init();
        String username = "admin";
        String password = "password";
        ArrayList<String> privileges = new ArrayList<>();
        privileges.add("Edit Users");
        privileges.add("Create Billboard");
        privileges.add("Edit All Billboards");
        privileges.add("Schedule Billboard");
        User user = new User(username, password, privileges);

        String addUser = "Call addUser('"+user.getUserName()+ "', '"+user.getSalt() +"', '"+user.getSaltPass()+"', '"+ user.getPrivilege()+"')";
        System.out.println(addUser);
        statement.execute(addUser);
        String [] array = Database.RetrieveColumnData(statement, "Call displayUsers()");
        String [][] userArr = Database.RetrieveData(statement, "Call displayUsers()");

        //String [] array2 = Database.RetrieveColumnData(statement, "Call getScheduleIdByBillboardId(8)");

        String[][] arrayList1 = Database.RetrieveData(statement, "Call displayAllSchedules()");
        String[][] arrayList2 = Database.RetrieveData(statement, "Call getScheduleInfo(1)");

        String[] values = {"AB","BC","CD","AE"};
        boolean contains = Arrays.asList(values).contains("A B");
        System.out.println( String.valueOf(contains) + Arrays.asList(values).contains("AB"));
    }
}
