package Company.Model;

public class Customers {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhoneNumber;
    private String divisionName;
    private int customerCountryID;
    private int divisionID;



    public Customers(int customerID,String customerName, String customerAddress, String customerPostalCode, String customerPhoneNumber,
                         String divisionName, int customerCountryID, int divisionID){
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhoneNumber = customerPhoneNumber;
        this.divisionID = customerID;

    }

    public int getCustomerID(){
        return customerID;
    }
    public String getCustomerName(){
        return customerName;
    }

    public String getCustomerAddress(){
        return customerAddress;
    }

    public String getCustomerPostalCode(){
        return customerPostalCode;
    }
    public String getCustomerPhoneNumber(){
        return customerPhoneNumber;
    }
    public String getDivisionName() {
        return divisionName;
    }
    public int getCustomerCountryID(){
        return customerCountryID;
    }

    public int getDivisionID(){
        return divisionID;
    }


    public void setCustomerID(){
        this.customerID = customerID;
    }
    public void setCustomerName(){
        this.customerName = customerName;
    }

    public void setCustomerAddress(){
        this.customerAddress = customerAddress;
    }

    public void setCustomerPostalCode(){
        this.customerPostalCode = customerPostalCode;
    }
    public void setCustomerPhoneNumber(){
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public void setDivisionName(){
        this.divisionName = divisionName;
    }

    public void setCustomerCountryID(){
        this.customerCountryID = customerCountryID;
    }

    public void setDivisionID(){
        this.divisionID = divisionID;
    }

}
