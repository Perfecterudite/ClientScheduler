package Company.Model;

public class Contacts {
    private int contactID;
    private String contactName;
    private String contactEmail;

    public Contacts(int contactID,String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public int getContactID(){
        return contactID;
    }
    public String toString(){
        return contactName;
    }
    public String getContactEmail(){
        return contactEmail;
    }

    public void setContactID(){
        this.contactID = contactID;
    }
    public void setContactName(){
        this.contactName = contactName;
    }
    public void setContactEmail(){
        this.contactEmail = contactEmail;
    }
}
