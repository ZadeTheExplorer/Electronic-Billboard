package Billboard;

import Billboard.Request.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ServerRespond {
    private Object request;
    private ObjectOutputStream oos;
    private final Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/electronicbb", "root", "");
    private final Statement statement = connection.createStatement();

    public ServerRespond(Object request, ObjectOutputStream oos) throws SQLException {
        this.request = request;
        this.oos = oos;
    }

    public void handle() throws SQLException, IOException, NoSuchAlgorithmException {
        if (request instanceof AddBillboardRequest){
            addBillboard(((AddBillboardRequest) request).getBillboard());
        }
        if (request instanceof DeleteBillboardRequest){
            deleteBillboard(((DeleteBillboardRequest) request).getBillboard());
        }
        if(request instanceof DisplayAllBillboardsRequest){
            displayAllBillboards();
        }
        if(request instanceof GetBillboardRequest){
            getBillboardData(((GetBillboardRequest) request).getBillboardName());
        }
        if(request instanceof CurrentBillboardRequest){
            currentBillboard(((CurrentBillboardRequest) request).getTime());
        }
        if(request instanceof EditBillboardRequest) {
            EditBillboard(((EditBillboardRequest) request).getBillboard());
        }
        if(request instanceof AddUserResquest) {
            addUser(((AddUserResquest) request).getUser());
        }
        if(request instanceof DeleteUserRequest) {
            deleteUser(((DeleteUserRequest) request).getUsername());
        }
        if(request instanceof DisplayAllUsersRequest) {
            displayAllUsers();
        }
        if(request instanceof GetUserPrivilegesRequest){
            getUserPrivilege(((GetUserPrivilegesRequest) request).getUsername());
        }
        if(request instanceof SetUserPrivilegesRequest){
            setUserPrivilege(((SetUserPrivilegesRequest) request).getUsername());
        }
        if(request instanceof SetUserPassword) {
            setUserPassword(((SetUserPassword) request).getUsername(),((SetUserPassword) request).getPassword());
        }
        if(request instanceof DisplayAllSchedulesRequest){
            displayAllSchedules();
        }
        if(request instanceof SetScheduleRequest){
            SetScheduleRequest post = (SetScheduleRequest) request;
            setSchedule((post.getScheduleId()), post.getBillboardName(), post.getStartTime(), post.getDuration());
        }
        if(request instanceof LoginRequest){
            LoginRequest post = (LoginRequest) request;
            login(post.getUsername(), post.getHashPassword());
        }
    }

    public void displayAllBillboards() throws SQLException, IOException {
        String[][] allBillboards = Database.RetrieveData(statement, "Call displayAllBillboards()");
        System.out.println("retrieved all billboards");
        oos.writeObject(allBillboards);
        oos.flush();
    }
    public void getBillboardData(String billboardName) throws SQLException, IOException {
        String[][] billboardData = Database.RetrieveData(statement, "Call displayBillboard('" + billboardName + "')");
        System.out.println("Retrieved data of billboard: " + billboardName);
        oos.writeObject(billboardData);
        oos.flush();
    }
    public void displayAllUsers() throws SQLException, IOException {
        String[][] allUsers = Database.RetrieveData(statement, "Call displayUsers();");
        System.out.println("Retrieved all users");
        oos.writeObject(allUsers);
        oos.flush();
    }
    //TODO: With this RetrievedData() we only can get start and end time as String.
    // java provided this: with t isInstanceOf java.sql.Time
    // String s = new SimpleDateFormat("HH.mm.ss.SSS").format(t.getTime());
    public void displayAllSchedules() throws SQLException, IOException {
        String[][] allSchedules = Database.RetrieveData(statement, "Call displayAllSchedules();");
        System.out.println("Retrieved all Schedules");
        oos.writeObject(allSchedules);
        oos.flush();
    }

    public void addBillboard(Billboard billboard) throws SQLException {
        String query = "Call addBillboard('"+ billboard.getName()+ "', '"+billboard.getCreator() + "', '"+ billboard.getBackgroundColor()+"', '"+billboard.getMessageColor()+
                "', '" + billboard.getInformationColor() + "', '" +billboard.getPicture()+"', '"+billboard.getMessage()+"', '"+billboard.getInformation() + "');";
        statement.execute(query);
    }
    public void deleteBillboard(String billboard) throws SQLException {
        String query = "Call deleteBillboard('"+billboard  +"');";
        statement.execute(query);
        System.out.println("delete billboard " +billboard);
    }

    public void EditBillboard(Billboard billboard) throws SQLException {
        String query = "Call editBillboard('"+ billboard.getName()+ "', '"+billboard.getCreator() + "', '"+ billboard.getBackgroundColor()+"', '"+billboard.getMessageColor()+
                "', '" + billboard.getInformationColor() + "', '" +billboard.getPicture()+"', '"+billboard.getMessage()+"', '"+billboard.getInformation() + "');";
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
    public void getUserPrivilege(String username) throws SQLException {
        String getUserPrivilegeQuery = "Call getUserPrivileges('" +username +"');";
        statement.execute(getUserPrivilegeQuery);
    }
    public void setUserPrivilege(String username) throws SQLException {
        String setUserPrivilegeQuery = "Call setUserPrivileges('" + username + "');";
        statement.execute(setUserPrivilegeQuery);
    }
    public void setUserPassword(String username,String password) throws SQLException, NoSuchAlgorithmException {
        String salt = User.createSalt();
        String hashedPass = User.hashedPassword(password);
        String saltedPassword = User.saltedPassword(hashedPass, salt);
        String setUserPrivilegeQuery = "Call updatePassword('" + username + "', '" + salt + "', '"+ saltedPassword+"');";
        statement.execute(setUserPrivilegeQuery);
    }
    //TODO: STORE START_TIME AND DURATION AS Java.Sql.Time
    public void setSchedule(int scheduleId, String billboardName, Time start, Time duration) throws SQLException {
        CallableStatement statement = connection.prepareCall("Call addSchedule(?,?,?)");
        statement.setString(1, billboardName);
        statement.setTime(2, start);
        statement.setTime(3, duration);
        statement.executeUpdate();
    }

    public void deleteSchedule(String billboardName, Time start) throws SQLException {
        CallableStatement deleteScheduleStatement = connection.prepareCall("Call deleteSchedule(?,?)");
        deleteScheduleStatement.setString(1, billboardName);
        deleteScheduleStatement.setTime(2, start);
        deleteScheduleStatement.executeUpdate();
    }

    //TODO: Do this later on
    public void currentBillboard(LocalDateTime time) {
        DayOfWeek weekDay = time.getDayOfWeek();

    }
    public void login(String username, String hashedPassword){
        try{
            String[][] userInfo = Database.RetrieveData(statement, "Call getUserInfo('"+username+"')");
            // if password is correct
            if(userInfo[1][2].compareTo(User.saltedPassword(hashedPassword,userInfo[1][1])) == 0){
                oos.writeObject(userInfo[1][3]);
                System.out.println(userInfo[1][3]);
                oos.flush();
            } else {
                oos.writeObject("Fail");
                System.out.println("Login fail!");
                oos.flush();
            }
        } catch (SQLException | NoSuchAlgorithmException | IOException throwable) {
            throwable.printStackTrace();
        }


    }
    public void logout(){}
}