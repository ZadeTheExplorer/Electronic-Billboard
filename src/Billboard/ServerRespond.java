package Billboard;

import Billboard.Request.AddBillboardRequest;
import Billboard.Request.DeleteBillboardRequest;
import Billboard.Request.DisplayBillboardRequest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void displayBillboard() throws SQLException, IOException {
        String[][] allBillboards = Database.RetrieveData(statement, "Call displayAllBillboards()");
        System.out.println("retrieved all billboards");
        oos.writeObject(allBillboards);
        oos.flush();
    }
}
