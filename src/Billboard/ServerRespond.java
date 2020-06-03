package Billboard;

import Billboard.Request.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServerRespond {
    private Object request;
    private ObjectOutputStream oos;
    private final Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/electronicbb", "root", "");
    private final Statement statement = connection.createStatement();

    public ServerRespond(Object request, ObjectOutputStream oos) throws SQLException {
        this.request = request;
        this.oos = oos;
    }

    public void handle() throws SQLException, IOException {
        if (request instanceof AddBillboardRequest){
            AddBillboardRequest post = (AddBillboardRequest) request;
            addBillboard(post.getBillboard());
        }
        if (request instanceof DeleteBillboardRequest){
            DeleteBillboardRequest post = (DeleteBillboardRequest) request;
            deleteBillboard(post.getBillboard());
        }
        if(request instanceof DisplayBillboardRequest){
            displayBillboard();
        }
        if(request instanceof CurrentBillboardRequest){
            CurrentBillboardRequest post = (CurrentBillboardRequest) request;
            currentBillboard(post.getTime());
        }
        if(request instanceof EditBillboardRequest) {
            EditBillboardRequest post = (EditBillboardRequest) request;
            EditBillboard(post.getBillboard());
        }
        if(request instanceof AddUserResquest) {
            AddUserResquest post = (AddUserResquest) request;
            addUser(post.getUser());
        }
        if(request instanceof DeleteUserRequest) {
            DeleteUserRequest post = (DeleteUserRequest) request;
            deleteUser(post.getUsername());
        }
        if(request instanceof AddUserResquest) {
            AddUserResquest post = (AddUserResquest) request;
            addUser(post.getUser());
        }
    }
    //TODO: Do this later on
    public void currentBillboard(LocalDateTime time) {
        DayOfWeek weekDay = time.getDayOfWeek();

    }
    public void displayBillboard() throws SQLException, IOException {
        String[][] allBillboards = Database.RetrieveData(statement, "Call displayAllBillboards()");
        System.out.println("retrieved all billboards");
        oos.writeObject(allBillboards);
        oos.flush();
    }
    public void displayUsers() throws SQLException, IOException {
        String[][] allUsers = Database.RetrieveData(statement, "Call displayUsers();");
        System.out.println("Retrieved all users");
        oos.writeObject(allUsers);
        oos.flush();

    }


    public void addBillboard(Billboard billboard) throws SQLException {
        String query = "call addBillboard(" + billboard.getCreatorId()+billboard.getBackgroundColor()+billboard.getMessageColor()+
                billboard.getInformationColor()+billboard.getPicture()+billboard.getMessage()+billboard.getInformation() + ");";
        statement.execute(query);
    }
    public void deleteBillboard(String billboard) throws SQLException {
        String query = "call deleteBillboard('"+billboard  +"');";
        statement.execute(query);
        System.out.println("delete billboard " +billboard);
    }

    public void EditBillboard(Billboard billboard) throws SQLException {
        String query = "call editBillboard(" + billboard.getCreatorId()+billboard.getBackgroundColor()+billboard.getMessageColor()+
                billboard.getInformationColor()+billboard.getPicture()+billboard.getMessage()+billboard.getInformation() + ");";
        statement.execute(query);
    }
    public void addUser(User user) throws SQLException {
        String addUserQuery = "Call addUser('"+user.getUserName()+ "', '"+user.getSalt() +"', '"+user.getSaltPass()+"', '"+ user.getPrivilege()+"')";
        statement.execute(addUserQuery);
    }
    public void deleteUser(String username) throws SQLException {
        String deleteUserQuery = "Call deleteUser('" +username +"');";
        statement.execute(deleteUserQuery);
    }


}
