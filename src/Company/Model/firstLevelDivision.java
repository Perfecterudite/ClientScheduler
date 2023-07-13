package Company.Model;

public class firstLevelDivision {
    private int divisionID;
    private String divisionName;
    private int country_ID;

    public firstLevelDivision(int divisionID,String divisionName, int country_ID){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.country_ID = country_ID;
    }

    public int getDivisionID(){
        return divisionID;
    }
    public String toString() {
        return divisionName;
    }
    public int getCountry_ID(){
        return country_ID;
    }

    public void setDivisionID(){
        this.divisionID = divisionID;
    }
    public void setDivisionName(){
        this.divisionName = divisionName;
    }
    public void setCountry_ID(){
        this.country_ID = country_ID;
    }
}
