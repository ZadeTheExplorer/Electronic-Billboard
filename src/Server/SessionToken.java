package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The type Session token.
 */
public class SessionToken implements Serializable {
    private String[] privileges;

    /**
     * Instantiates a new Session token.
     *
     * @param listOfPrivileges the list of privileges
     */
    public SessionToken(ArrayList<String> listOfPrivileges){
        this.privileges = listOfPrivileges.toArray(new String[listOfPrivileges.size()]);
    }

    /**
     * Can edit user boolean.
     *
     * @return the boolean
     */
    public boolean canEditUser(){
        return Arrays.asList(privileges).contains("Edit Users");
    }

    /**
     * Can create billboard boolean.
     *
     * @return the boolean
     */
    public boolean canCreateBillboard(){
        return Arrays.asList(privileges).contains("Create Billboard");
    }

    /**
     * Can edit billboards boolean.
     *
     * @return the boolean
     */
    public boolean canEditBillboards(){
        return Arrays.asList(privileges).contains("Edit All Billboards");
    }

    /**
     * Can schedule billboard boolean.
     *
     * @return the boolean
     */
    public boolean canScheduleBillboard(){
        return Arrays.asList(privileges).contains("Schedule Billboard");
    }

    @Override
    public String toString() {
        return Arrays.toString(privileges);
    }
}
