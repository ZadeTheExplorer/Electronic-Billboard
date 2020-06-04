package Server;

import java.util.Arrays;

public class SessionToken {
    private final String[] privileges;
    public SessionToken(String[] privileges){
        this.privileges = privileges;
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
