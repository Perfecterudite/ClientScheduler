package Company;

public class Users {
    private int userID;
    private String userName;
    private String userPassword;

    public Users(int userID,String userName, String userPassword){
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public int getUserID(){
        return userID;
    }
    public String getUserName(){
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
