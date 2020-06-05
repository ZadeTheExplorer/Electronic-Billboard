package Server;

import java.util.ArrayList;
import java.util.Arrays;

public class SessionToken {
    private static String[] privileges;
    private static SessionToken instance;
    public SessionToken(ArrayList<String> listOfPrivileges){
        if(instance == null){
            privileges = listOfPrivileges.toArray(new String[listOfPrivileges.size()]);
            instance = this;
        }
    }
    public static SessionToken getInstance() {
        return instance;
    }
    public static boolean canEditUser(){
        return Arrays.asList(privileges).contains("Edit Users");
    }
    public static boolean canCreateBillboard(){
        return Arrays.asList(privileges).contains("Create Billboard");
    }
    public static boolean canEditBillboards(){
        return Arrays.asList(privileges).contains("Edit All Billboards");
    }
    public static boolean canScheduleBillboard(){
        return Arrays.asList(privileges).contains("Schedule Billboard");
    }

    @Override
    public String toString() {
        return Arrays.toString(privileges);
    }
}
