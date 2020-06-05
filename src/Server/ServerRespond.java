package Server;

import Database.Database;
import ElectronicBillboardObject.*;
import Request.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.DayOfWeek;
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
            if(!SessionToken.canCreateBillboard()){
                tokenErrorHandler();
            } else{
                addBillboard(((AddBillboardRequest) request).getBillboard());
            }
        }
        if (request instanceof DeleteBillboardRequest){
            if (!SessionToken.canEditBillboards()){
                tokenErrorHandler();
            } else {
                deleteBillboard(((DeleteBillboardRequest) request).getBillboard());
            }
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
            if(!SessionToken.canEditBillboards()){
                tokenErrorHandler();
            } else{
                EditBillboard(((EditBillboardRequest) request).getBillboard());
            }
        }
        if(request instanceof AddUserResquest) {
            if(!SessionToken.canEditUser()){
                tokenErrorHandler();
            }else {
                addUser(((AddUserResquest) request).getUser());
            }
        }
        if(request instanceof DeleteUserRequest) {
            if(!SessionToken.canEditUser()){
                tokenErrorHandler();
            }else {
                deleteUser(((DeleteUserRequest) request).getUsername());
            }
        }
        if(request instanceof DisplayAllUsersRequest) {
            if(!SessionToken.canEditUser()){
                tokenErrorHandler();
            }else {
                displayAllUsers();
            }
        }
        if(request instanceof GetUserPrivilegesRequest){
            if(!SessionToken.canEditUser()){
            tokenErrorHandler();
            }else {
                getUserPrivilege(((GetUserPrivilegesRequest) request).getUsername());
            }
        }
        if(request instanceof SetUserPrivilegesRequest){
            if(!SessionToken.canEditUser()){
                tokenErrorHandler();
            }else {
                setUserPrivilege(((SetUserPrivilegesRequest) request).getUsername(),((SetUserPrivilegesRequest) request).getPrivileges());
            }
        }
        if(request instanceof SetUserPassword) {
            if(!SessionToken.canEditUser()){
                tokenErrorHandler();
            }else {
                setUserPassword(((SetUserPassword) request).getUsername(),((SetUserPassword) request).getPassword());
            }
        }
        if(request instanceof DisplayAllSchedulesRequest){
            if(!SessionToken.canScheduleBillboard()){
                tokenErrorHandler();
            }else {
                displayAllSchedules();
            }
        }
        if(request instanceof SetScheduleRequest){
            if(!SessionToken.canScheduleBillboard()){
                tokenErrorHandler();
            }else {
                SetScheduleRequest post = (SetScheduleRequest) request;
                setSchedule(post.getBillboardName(), post.getStartTime(), post.getDuration());
            }
        }
        if(request instanceof DeleteScheduleRequest){
            if(!SessionToken.canScheduleBillboard()){
                tokenErrorHandler();
            }else {
                DeleteScheduleRequest post = (DeleteScheduleRequest) request;
                deleteSchedule(post.getBillboardName(), post.getStartTime());
            }
        }
        if(request instanceof LoginRequest){
            LoginRequest post = (LoginRequest) request;
            login(post.getUsername(), post.getHashPassword());
        }
        if(request instanceof DeleteUserRequest){
            if(!SessionToken.canEditUser()){
                tokenErrorHandler();
            } else {
                deleteUser(((DeleteUserRequest) request).getUsername());
            }
        }
    }
    public void tokenErrorHandler() throws IOException {
        System.out.println("[Server] User do not have permission to do this action");
        oos.writeObject("No permission");
        oos.flush();
    }
    public void displayAllBillboards() throws IOException {
        try{
            String[][] allBillboards = Database.RetrieveData(statement, "Call displayAllBillboards()");
            System.out.println("retrieved all billboards");
            oos.writeObject(allBillboards);
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void getBillboardData(String billboardName) throws IOException {
        try{
            String[][] billboardData = Database.RetrieveData(statement, "Call displayBillboard('" + billboardName + "')");
            System.out.println("Retrieved data of billboard: " + billboardName);
            oos.writeObject(billboardData);
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void displayAllUsers() throws IOException {
        try{
            String[][] allUsers = Database.RetrieveData(statement, "Call displayUsers();");
            System.out.println("Retrieved all users");
            oos.writeObject(allUsers);
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    //TODO: With this RetrievedData() we only can get start and end time as String.
    // java provided this: with t isInstanceOf java.sql.Time
    // String s = new SimpleDateFormat("HH.mm.ss.SSS").format(t.getTime());
    public void displayAllSchedules() throws SQLException, IOException {
        try{
            String[][] allSchedules = Database.RetrieveData(statement, "Call displayAllSchedules();");
            System.out.println("Retrieved all Schedules");
            oos.writeObject(allSchedules);
            oos.flush();
        }  catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    public void addBillboard(Billboard billboard) throws IOException {
        try {
            String query = "Call addBillboard('"+ billboard.getName()+ "', '"+billboard.getCreator() + "', '"+ billboard.getBackgroundColor()+"', '"+billboard.getMessageColor()+
                    "', '" + billboard.getInformationColor() + "', '" +billboard.getPicture()+"', '"+billboard.getMessage()+"', '"+billboard.getInformation() + "');";
            statement.execute(query);
        }  catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void deleteBillboard(String billboard) throws IOException {
        try{
            String query = "Call deleteBillboard('"+billboard  +"');";
            statement.execute(query);
            System.out.println("delete billboard " +billboard);
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    public void EditBillboard(Billboard billboard) throws IOException {
        try{
            String query = "Call editBillboard('"+ billboard.getName()+ "', '"+billboard.getCreator() + "', '"+ billboard.getBackgroundColor()+"', '"+billboard.getMessageColor()+
                    "', '" + billboard.getInformationColor() + "', '" +billboard.getPicture()+"', '"+billboard.getMessage()+"', '"+billboard.getInformation() + "');";
            statement.execute(query);
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void addUser(User user) throws IOException {
        try {
            String addUserQuery = "Call addUser('"+user.getUserName()+ "', '"+user.getSalt() +"', '"+user.getSaltPass()+"', '"+ user.getPrivilege()+"')";
            statement.execute(addUserQuery);
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void deleteUser(String username) throws IOException {
        try {
            String deleteUserQuery = "Call deleteUser('" +username +"');";
            statement.execute(deleteUserQuery);
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void getUserPrivilege(String username) throws IOException {
        try {
            String getUserPrivilegeQuery = "Call getUserPrivileges('" +username +"');";
            statement.execute(getUserPrivilegeQuery);
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void setUserPrivilege(String username, String[] privileges) throws IOException {
        try {
            StringBuilder permissions = new StringBuilder();
            String prefix = "";
            for (String privilege : privileges){
                permissions.append(prefix).append(privilege);
                prefix = ", ";
            }
            String setUserPrivilegeQuery = "Call setUserPrivileges('" + username +"', '"+ permissions+ "');";
            statement.execute(setUserPrivilegeQuery);
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    public void setUserPassword(String username,String password) throws NoSuchAlgorithmException, IOException {
        try {
            String salt = User.createSalt();
            String hashedPass = User.hashedPassword(password);
            String saltedPassword = User.saltedPassword(hashedPass, salt);
            String setUserPrivilegeQuery = "Call updatePassword('" + username + "', '" + salt + "', '"+ saltedPassword+"');";
            statement.execute(setUserPrivilegeQuery);
        } catch (SQLException e) {
            oos.writeObject(e);
            oos.flush();
        }
    }
    //TODO: STORE START_TIME AND DURATION AS Java.Sql.Time
    public void setSchedule(String billboardName, Time start, Time duration) throws IOException {
        try {
            CallableStatement statement = connection.prepareCall("Call addSchedule(?,?,?)");
            statement.setString(1, billboardName);
            statement.setTime(2, start);
            statement.setTime(3, duration);
            statement.executeUpdate();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }
    //TODO
    public void deleteSchedule(String billboardName, Time start) throws IOException {
        try {
            CallableStatement deleteScheduleStatement = connection.prepareCall("Call deleteSchedule(?,?)");
            deleteScheduleStatement.setString(1, billboardName);
            deleteScheduleStatement.setTime(2, start);
            deleteScheduleStatement.executeUpdate();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    //TODO: Do this later on
    public void currentBillboard(LocalDateTime time) throws IOException {
//        try{
//
//            DayOfWeek weekDay = time.getDayOfWeek();
//        }
//        catch (SQLException e){
//            oos.writeObject(e);
//            oos.flush();
//        }
    }
    public void login(String username, String hashedPassword) throws IOException, NoSuchAlgorithmException {
        String[][] userInfo = null;
        try{
            userInfo = Database.RetrieveData(statement, "Call getUserInfo('"+username+"')");
            System.out.println(Arrays.deepToString(userInfo));
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }

        // if password is correct
        if (userInfo == null || userInfo.length <= 1){
            oos.writeObject("Fail");
            System.out.println("Can not find username");
            oos.flush();
        }
        else if(userInfo[1][2].compareTo(User.saltedPassword(hashedPassword,userInfo[1][1])) == 0){
        oos.writeObject(userInfo[1][3]);
        System.out.println(userInfo[1][3]);
        oos.flush();
        } else {
            oos.writeObject("Fail");
            System.out.println("Login fail!");
            oos.flush();
        }



    }
    public void logout(){}
}