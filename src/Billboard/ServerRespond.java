package Billboard;

import Billboard.Request.AddBillboardRequest;
import Billboard.Request.DeleteBillboardRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerRespond {
    private Object request;
    private final Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
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
    }

    public void addBillboard(Billboard billboard) throws SQLException {
        String query = "call addBillboard(" + billboard.getCreatorId()+billboard.getBackgroundColor()+billboard.getMessageColor()+
                billboard.getInformationColor()+billboard.getPicture()+billboard.getMessage()+billboard.getInformation() + ");";
        statement.execute(query);
    }
    public void deleteBillboard(Billboard billboard) throws SQLException {
        String query = "call deleteBillboard("+billboard.getName()  +");";
        statement.execute(query);
        System.out.println("delete billboard " +billboard.getName());
    }
}
