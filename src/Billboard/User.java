package Billboard;

import java.util.ArrayList;

public class User {

    private String userName;
    private String passWord;
    private ArrayList<String> permission;
    private String privilege;

    public User(String name, String password, ArrayList<String> permission, String privilege){
        userName = name;
        passWord = password;
        this.permission = new ArrayList<>(permission.size());
        for(int i = 0 ; i < permission.size(); i++){
            this.permission.set(i, permission.get(i));
        }
        this.privilege = privilege;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean checkPassword(String enteredPass) {return enteredPass == passWord; }

    public ArrayList<String> getPermission() {
        return permission;
    }

    public void setPermission(ArrayList<String> permission) {
        this.permission = permission;
    }

    public String getPrivilege() {
        return privilege;
    }

    /* To update the user's privilege
    * @param acquired privilege
    */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
