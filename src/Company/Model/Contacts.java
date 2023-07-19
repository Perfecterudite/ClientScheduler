package Company.Model;

/** This class handles the contacts.
 *
 **/
public class Contacts {
    private int contactID;
    private String contactName;
    private String contactEmail;

    /** Constructor for building a contact.
     *
     * @param contactID The id of the contact.
     * @param contactName The Name of the contact.
     * @param contactEmail The Email of the contact.
     */

    public Contacts(int contactID,String contactName, String contactEmail){
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    //Getter
    public int getContactID(){
        return contactID;
    }
    public String getContactName(){
        return contactName;
    }
    public String toString(){
        return contactName;
    }
    public String getContactEmail(){
        return contactEmail;
    }


    //Setter
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
