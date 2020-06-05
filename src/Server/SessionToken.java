package Server;

import java.util.ArrayList;
import java.util.Arrays;

public class SessionToken {
    private String[] privileges;
    private SessionToken instance;
    public SessionToken(ArrayList<String> listOfPrivileges){
        if(instance == null){
            this.privileges = listOfPrivileges.toArray(new String[listOfPrivileges.size()]);
        }
    }
    public SessionToken getInstance() {
        return instance;
    }
    public boolean canEditUser(){
        return Arrays.asList(privileges).contains("Edit Users");
    }
    public boolean canCreateBillboard(){
        return Arrays.asList(privileges).contains("Create Billboard");
    }
    public boolean canEditBillboards(){
        return Arrays.asList(privileges).contains("Edit All Billboards");
    }
    public boolean canScheduleBillboard(){
        return Arrays.asList(privileges).contains("Schedule Billboard");
    }

}
