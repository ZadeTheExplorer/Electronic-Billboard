package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SessionToken implements Serializable {
    private String[] privileges;
    public SessionToken(ArrayList<String> listOfPrivileges){
        this.privileges = listOfPrivileges.toArray(new String[listOfPrivileges.size()]);
    }

    public boolean canEditUser(){
        System.out.println(Arrays.toString(privileges));
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

    @Override
    public String toString() {
        return Arrays.toString(privileges);
    }
}
