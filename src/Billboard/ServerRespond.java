package Billboard;

import Billboard.Request.AddBillboardRequest;
import Billboard.Request.DeleteBillboardRequest;
import Billboard.Request.DisplayBillboardRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServerRespond {
    private Object request;
    private final Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/electronicbb", "root", "");
    private final Statement statement = connection.createStatement();

    public ServerRespond(Object request) throws SQLException {
        this.request = request;
    }

    public void handle() throws SQLException {
        if (request instanceof AddBillboardRequest){
            AddBillboardRequest post = (AddBillboardRequest) request;

            Billboard billboard = post.getBillboard();


        }
        if (request instanceof DeleteBillboardRequest){
            DeleteBillboardRequest post = (DeleteBillboardRequest) request;
            deleteBillboard(post.getBillboard());

        }
        if(request instanceof DisplayBillboardRequest){
            displayBillboard();
        }
    }

    public void addBillboard(Billboard billboard) throws SQLException {
        String query = "call addBillboard(" + billboard.getCreatorId()+billboard.getBackgroundColor()+billboard.getMessageColor()+
                billboard.getInformationColor()+billboard.getPicture()+billboard.getMessage()+billboard.getInformation() + ");";
        statement.execute(query);
    }
    public void deleteBillboard(Billboard billboard) throws SQLException {
        String query = "call deleteBillboard('"+billboard.getName()  +"');";
        statement.execute(query);
        System.out.println("delete billboard " +billboard.getName());
    }
    public void displayBillboard() throws SQLException {
        ArrayList<String[]> allBillboards = Database.RetrieveData(statement, "Call displayAllBillboards()");
        System.out.println("retrieved all billboards");
        //TODO:
        // Send allbillboards to controlpanels
    }
}
