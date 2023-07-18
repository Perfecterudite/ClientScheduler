package Company.Model;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class Appointments {
    private int apptID;
    private String apptTitle;
    private String apptDescription;
    private String apptLocation;
    private String apptType;
    public Timestamp Start;
    public Timestamp End;
    public int customerID;
    public int userID;
    public int contactID;

    public Appointments(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType,
                        Timestamp Start, Timestamp End, int customerID, int userID, int contactID){
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.Start = Start;
        this.End = End;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;

    }

    public int getApptID(){
        return apptID;
    }
    public String getApptTitle(){
        return apptTitle;
    }

    public String getApptDescription(){
        return apptDescription;
    }

    public String getApptLocation(){
        return apptLocation;
    }
    public String getApptType(){
        return apptType;
    }

    public Timestamp getStart(){
        return Start;
    }

    public Timestamp getEnd(){
        return End;
    }

    public int getCustomerID(){
        return customerID;
    }

    public int getUserID(){
        return userID;
    }

    public int getContactID(){
        return contactID;
    }


    //setter
    public void setApptID(){
        this.apptID = apptID;
    }
    public void setApptTitle(){
        this.apptTitle = apptTitle;
    }

    public void setApptDescription(){
        this.apptDescription = apptDescription;
    }

    public void setApptLocation(){
        this.apptLocation = apptLocation;
    }
    public void setApptType(){
        this.apptType = apptType;
    }

    public void setStart(){
        this.Start = Start;
    }

    public void setEnd(){
        this.End = End;
    }

    public void setCustomerID(){
        this.customerID = customerID;
    }

    public void setUserID(){
        this.userID = userID;
    }

    public void setContactID(){
        this.contactID = contactID;
    }

}
