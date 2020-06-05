package Database;

import ElectronicBillboardObject.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The type Database.
 */
public class Database {
    /**
     * The Connection.
     */
    static Connection connection;

    /**
     * Instantiates a new Database.
     */
    public Database() {
        connection = DBConnection.getInstance();
    };


    /**
     * Init.
     *
     * @throws FileNotFoundException the file not found exception
     * @throws SQLException          the sql exception
     */
    public static void init() throws FileNotFoundException, SQLException {
        connection = DBConnection.getInstance();
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
        statement.execute("INSERT INTO billboards VALUES('COVID', 'admin', '#BBF1C8', '#D92027', '#FF9234', 'https://d2v9ipibika81v.cloudfront.net/uploads/sites/40/COVID-19.jpg', 'COVID-19 pandemic', 'Hand washing is recommended to prevent the spread of the disease');");
        statement.execute("INSERT INTO billboards VALUES('DenVau Rapper', 'admin', '#FFC9C9', '#FF4900', '#000000', 'https://image.plo.vn/w800/Uploaded/2020/wopsvun/2019_06_20/den-vau_gsuj.jpg', 'Song: 2 Billion Years', 'Directed by Den\n" +
                "Quay: Khoa Danh, Thai Son\n" +
                "Engsub: Hana Lexis');");

        CallableStatement scheduleStatement = connection.prepareCall("Call addSchedule(?,?,?,?)");
        scheduleStatement.setString(1,"COVID");
        scheduleStatement.setString(4, "Friday");
        scheduleStatement.setTime(2, Time.valueOf("05:30:00"));
        scheduleStatement.setTime(3, Time.valueOf("00:15:00"));
        scheduleStatement.executeQuery();

        scheduleStatement.setString(1,"DenVau Rapper");
        scheduleStatement.setString(4, "Wednesday");
        scheduleStatement.setTime(2, Time.valueOf("10:00:00"));
        scheduleStatement.setTime(3, Time.valueOf("00:30:00"));
        scheduleStatement.executeQuery();

        scheduleStatement.close();
        statement.close();
    }

    /**
     * Retrieve column data string [ ].
     *
     * @param st    the st
     * @param query the query
     * @return the string [ ]
     * @throws SQLException the sql exception
     */
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

        return column;
    }

    /**
     * Retrieve data string [ ] [ ].
     *
     * @param st    the st
     * @param query the query
     * @return the string [ ] [ ]
     * @throws SQLException the sql exception
     */
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
//            for( String s: table.get(i)){
//                System.out.print( " " + s );
//
//            }
            newTable[i] = table.get(i);
//            System.out.println();
        }
//        System.out.println(Arrays.deepToString(newTable));
        return newTable;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        Database.init();
        Statement statement = connection.createStatement();

        String username = "admin";
        String password = "password";
        ArrayList<String> privileges = new ArrayList<>();
        privileges.add("Edit Users");
        privileges.add("Create Billboard");
        privileges.add("Edit All Billboards");
        privileges.add("Schedule Billboard");
        User user = new User(username, password, privileges);

        String addUser = "Call addUser('"+user.getUserName()+ "', '"+user.getSalt() +"', '"+user.getSaltPass()+"', '"+ user.getPrivilege()+"')";

        statement.execute(addUser);
        String [] array = Database.RetrieveColumnData(statement, "Call displayUsers()");
        String [][] userArr = Database.RetrieveData(statement, "Call displayUsers()");

        //String [] array2 = Database.RetrieveColumnData(statement, "Call getScheduleIdByBillboardId(8)");

        String[][] arrayList1 = Database.RetrieveData(statement, "Call displayAllSchedules()");
        String[][] arrayList2 = Database.RetrieveData(statement, "Call getScheduleInfo(1)");

        String[] values = {"AB","BC","CD","AE"};
        boolean contains = Arrays.asList(values).contains("A B");
    }
}
