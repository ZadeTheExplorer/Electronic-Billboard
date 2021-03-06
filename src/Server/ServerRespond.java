package Server;

import Database.Database;
import ElectronicBillboardObject.*;
import Request.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * The type Server respond.
 */
public class ServerRespond {
    private Object request;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private final Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/electronicbb", "root", "");
    private final Statement statement = connection.createStatement();

    /**
     * Instantiates a new Server respond.
     *
     * @param request the request
     * @param oos     the oos
     * @param ois     the ois
     * @throws SQLException the sql exception
     */
    public ServerRespond(Object request, ObjectOutputStream oos, ObjectInputStream ois) throws SQLException {
        this.request = request;
        this.oos = oos;
        this.ois = ois;
    }

    /**
     * Handle.
     *
     * @throws SQLException             the sql exception
     * @throws IOException              the io exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public void handle() throws SQLException, IOException, NoSuchAlgorithmException {
        if (request instanceof AddBillboardRequest){
            AddBillboardRequest post = (AddBillboardRequest) request;
            if(!post.getSessionToken().canCreateBillboard()){
                tokenErrorHandler();
            } else{
                addBillboard(post.getBillboard());
            }
        }
        if (request instanceof DeleteBillboardRequest){
            DeleteBillboardRequest post = (DeleteBillboardRequest) request;
            if (!post.getSessionToken().canEditBillboards()){
                tokenErrorHandler();
            } else {
                deleteBillboard(post.getBillboard());
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
            EditBillboardRequest post = (EditBillboardRequest) request;
            if(!post.getSessionToken().canEditBillboards()){
                tokenErrorHandler();
            } else{
                EditBillboard(post.getBillboard());
            }
        }
        if(request instanceof AddUserResquest) {
            AddUserResquest post = (AddUserResquest) request;
            if(!post.getSessionToken().canEditUser()){
                tokenErrorHandler();
            }else {
                addUser(post.getUser());
            }
        }
        if(request instanceof DeleteUserRequest) {
            DeleteUserRequest post = (DeleteUserRequest) request;
            if(!post.getSessionToken().canEditUser()){
                tokenErrorHandler();
            }else {
                deleteUser(post.getUsername());
            }
        }
        if(request instanceof DisplayAllUsersRequest) {
            System.out.println("[ControlPanel] Request display all servers");
            DisplayAllUsersRequest post = (DisplayAllUsersRequest) request;
            if(!post.getSessionToken().canEditUser()){
                tokenErrorHandler();
            }else {
                displayAllUsers();
            }
        }
        if(request instanceof GetUserPrivilegesRequest){
            GetUserPrivilegesRequest post = (GetUserPrivilegesRequest) request;
            if(!post.getSessionToken().canEditUser()){
            tokenErrorHandler();
            }else {
                getUserPrivilege(post.getUsername());
            }
        }
        if(request instanceof SetUserPrivilegesRequest){
            SetUserPrivilegesRequest post = (SetUserPrivilegesRequest) request;
            if(!post.getSessionToken().canEditUser()){
                tokenErrorHandler();
            }else {
                setUserPrivilege(post.getUsername(), post.getPrivileges());
            }
        }
        if(request instanceof SetUserPassword) {
            SetUserPassword post = (SetUserPassword) request;
            if(!post.getSessionToken().canEditUser()){
                tokenErrorHandler();
            }else {
                setUserPassword(post.getUsername(), post.getPassword());
            }
        }
        if(request instanceof DisplayAllSchedulesRequest){
            DisplayAllSchedulesRequest post = (DisplayAllSchedulesRequest) request;
            if(!post.getSessionToken().canScheduleBillboard()){
                tokenErrorHandler();
            }else {
                displayAllSchedules();
            }
        }
        if(request instanceof SetScheduleRequest){
            SetScheduleRequest post = (SetScheduleRequest) request;
            if(!post.getSessionToken().canScheduleBillboard()){
                tokenErrorHandler();
            }else {
                setSchedule(post.getBillboardName(), post.getStartTime(), post.getDuration(), post.getDayOfWeek());
            }
        }
        if(request instanceof DeleteScheduleRequest){

            DeleteScheduleRequest post = (DeleteScheduleRequest) request;
            if(!post.getSessionToken().canScheduleBillboard()){
                tokenErrorHandler();
            }else {
                deleteSchedule(post.getBillboardName(), post.getStartTime());
            }
        }
        if(request instanceof LoginRequest){
            LoginRequest post = (LoginRequest) request;
            login(post.getUsername(), post.getHashPassword());
        }
        if(request instanceof DeleteUserRequest){
            DeleteUserRequest post = (DeleteUserRequest) request;
            if(!post.getSessionToken().canEditUser()){
                tokenErrorHandler();
            } else {
                deleteUser(post.getUsername());
            }
        }
        if(request instanceof ExportBillboardRequest){
            ExportBillboardRequest post = (ExportBillboardRequest) request;
            exportBillboard(post.getBillboardName());
        }
    }

    /**
     * Token error handler.
     *
     * @throws IOException the io exception
     */
    public void tokenErrorHandler() throws IOException {
        System.out.println("[Server] User do not have permission to do this action");
        oos.writeObject("No Permission");
        oos.flush();

    }

    /**
     * Export billboard.
     *
     * @param billboardName the billboard name
     * @throws SQLException the sql exception
     */
    public void exportBillboard(String billboardName) throws SQLException {
        String[][] billboardData = Database.RetrieveData(statement, "Call displayBillboard('" + billboardName + "')");
        Billboard exportBillboard = new Billboard(
                billboardData[1][0],
                billboardData[1][1],
                billboardData[1][2],
                billboardData[1][3],
                billboardData[1][4],
                billboardData[1][5],
                billboardData[1][6],
                billboardData[1][7]
        );
        XMLFile.create(exportBillboard);
        System.out.println("[SERVER] Exported " + exportBillboard.getName());
    }

    /**
     * Display all billboards.
     *
     * @throws IOException the io exception
     */
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

    /**
     * Gets billboard data.
     *
     * @param billboardName the billboard name
     * @throws IOException the io exception
     */
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

    /**
     * Display all users.
     *
     * @throws IOException the io exception
     */
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

    /**
     * Display all schedules.
     *
     * @throws IOException the io exception
     */
//TODO: With this RetrievedData() we only can get start and end time as String.
    // java provided this: with t isInstanceOf java.sql.Time
    // String s = new SimpleDateFormat("HH.mm.ss.SSS").format(t.getTime());
    public void displayAllSchedules() throws IOException {
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

    /**
     * Add billboard.
     *
     * @param billboard the billboard
     * @throws IOException the io exception
     */
    public void addBillboard(Billboard billboard) throws IOException {
        try {
            String query = "Call addBillboard('"+ billboard.getName()+ "', '"+billboard.getCreator() + "', '"+ billboard.getBackgroundColor()+"', '"+billboard.getMessageColor()+
                    "', '" + billboard.getInformationColor() + "', '" +billboard.getPicture()+"', '"+billboard.getMessage()+"', '"+billboard.getInformation() + "');";
            statement.execute(query);
            oos.writeObject("Success");
            oos.flush();
        }  catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Delete billboard.
     *
     * @param billboard the billboard
     * @throws IOException the io exception
     */
    public void deleteBillboard(String billboard) throws IOException {
        try{
            String query = "Call deleteBillboard('"+billboard  +"');";
            statement.execute(query);
            System.out.println("delete billboard " +billboard);
            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Edit billboard.
     *
     * @param billboard the billboard
     * @throws IOException the io exception
     */
    public void EditBillboard(Billboard billboard) throws IOException {
        try{
            String query = "Call editBillboard('"+ billboard.getName()+ "', '"+billboard.getCreator() + "', '"+ billboard.getBackgroundColor()+"', '"+billboard.getMessageColor()+
                    "', '" + billboard.getInformationColor() + "', '" +billboard.getPicture()+"', '"+billboard.getMessage()+"', '"+billboard.getInformation() + "');";
            statement.execute(query);
            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Add user.
     *
     * @param user the user
     * @throws IOException the io exception
     */
    public void addUser(User user) throws IOException {
        try {
            String addUserQuery = "Call addUser('"+user.getUserName()+ "', '"+user.getSalt() +"', '"+user.getSaltPass()+"', '"+ user.getPrivilege()+"')";
            statement.execute(addUserQuery);
            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Delete user.
     *
     * @param username the username
     * @throws IOException the io exception
     */
    public void deleteUser(String username) throws IOException {
        try {
            String deleteUserQuery = "Call deleteUser('" +username +"');";
            statement.execute(deleteUserQuery);
            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Gets user privilege.
     *
     * @param username the username
     * @throws IOException the io exception
     */
    public void getUserPrivilege(String username) throws IOException {
        try {
            String getUserPrivilegeQuery = "Call getUserPrivileges('" +username +"');";
            statement.execute(getUserPrivilegeQuery);
            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Sets user privilege.
     *
     * @param username   the username
     * @param privileges the privileges
     * @throws IOException the io exception
     */
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
            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Sets user password.
     *
     * @param username the username
     * @param password the password
     * @throws NoSuchAlgorithmException the no such algorithm exception
     * @throws IOException              the io exception
     */
    public void setUserPassword(String username,String password) throws NoSuchAlgorithmException, IOException {
        try {
            String salt = User.createSalt();
            String hashedPass = User.hashedPassword(password);
            String saltedPassword = User.saltedPassword(hashedPass, salt);
            String setUserPrivilegeQuery = "Call updatePassword('" + username + "', '" + salt + "', '"+ saltedPassword+"');";
            statement.execute(setUserPrivilegeQuery);
            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e) {
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Sets schedule.
     *
     * @param billboardName the billboard name
     * @param start         the start
     * @param duration      the duration
     * @param dayOfWeek     the day of week
     * @throws IOException the io exception
     */
//TODO: STORE START_TIME AND DURATION AS Java.Sql.Time
    public void setSchedule(String billboardName, Time start, Time duration, String dayOfWeek) throws IOException {
        try {

            CallableStatement statement = connection.prepareCall("Call addSchedule(?,?,?,?)");
            statement.setString(1, billboardName);
            statement.setTime(2, start);
            statement.setTime(3, duration);
            statement.setString(4, dayOfWeek);
            statement.executeUpdate();

            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Delete schedule.
     *
     * @param billboardName the billboard name
     * @param start         the start
     * @throws IOException the io exception
     */
//TODO
    public void deleteSchedule(String billboardName, Time start) throws IOException {
        try {
            CallableStatement deleteScheduleStatement = connection.prepareCall("Call deleteSchedule(?,?)");
            deleteScheduleStatement.setString(1, billboardName);
            deleteScheduleStatement.setTime(2, start);
            deleteScheduleStatement.executeUpdate();

            oos.writeObject("Success");
            oos.flush();
        } catch (SQLException e){
            oos.writeObject(e);
            oos.flush();
        }
    }

    /**
     * Current billboard.
     *
     * @param time the time
     * @throws IOException the io exception
     */
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

    /**
     * Login.
     *
     * @param username       the username
     * @param hashedPassword the hashed password
     * @throws IOException              the io exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
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

    /**
     * Logout.
     */
    public void logout(){}
}