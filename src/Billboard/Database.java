package Billboard;

import java.awt.dnd.DragSourceDragEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    public Database() {};
    public static void init() throws FileNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");

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
        statement.execute("CREATE DATABASE IF NOT EXISTS electronicBB;");
        statement.execute("USE electronicBB;");
        statement.execute("CREATE TABLE  IF NOT EXISTS `electronicBB`.`users`(`id` int(3) unsigned NOT NULL default '0',`name` varchar(45) NOT NULL default '',\n" +
                "  `username` varchar(30) NOT NULL default 'username',\n" +
                "  `password` varchar(50) NOT NULL default 'password',\n" +
                "  `privilege` VARCHAR(50) default 'edit user, edit schedule, edit billboard',\n" +
                "  PRIMARY KEY  (`id`)\n" +
                ") ENGINE=MyISAM DEFAULT CHARSET=latin1;");
        //TODO: Implement stored procedure
        statement.execute("DROP PROCEDURE IF EXISTS `electronicBB`.`displayUsers`\n" +
                "CREATE PROCEDURE `electronicBB`.`displayUsers` ()\n" +
                "BEGIN\n" +
                "  SELECT * FROM users;\n" +
                "END");
//        String[] statementARR = {
//                "CREATE DATABASE IF NOT EXISTS electronicBB;"
//                "USE electronicBB;"
//        };

//        for (String s : tempsArray) {
//            ResultSet rs = statement.executeQuery(s + "$$");
//            rs.next();
//        }
        statement.close();
    }

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        Database.init();
    }
}
