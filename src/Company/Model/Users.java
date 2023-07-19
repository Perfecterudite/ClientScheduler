package Company.Model;


/** This class handles the Users.
 *
 **/
public class Users {
    private int userID;
    private String userName;
    private String userPassword;

    /** Constructor for building an user.
     *
     * @param userID The id of the user.
     * @param userName The name of the user.
     * @param userPassword The password of the user.
     */
    public Users(int userID,String userName, String userPassword){
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    //Getter
    public int getUserID(){
        return userID;
    }
    public String toString(){
        return userName;
    }
    public String getUserPassword(){
        return userPassword;
    }

    //Setter
    public void setUserID(){
        this.userID = userID;
    }
    public void setUserName(){
        this.userName = userName;
    }
    public void setUserPassword(){
        this.userPassword = userPassword;
    }
}
