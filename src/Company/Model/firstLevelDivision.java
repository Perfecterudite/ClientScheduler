package Company.Model;

/** This class handles the First Level divisions.
 *
 **/
public class firstLevelDivision {
    private int divisionID;
    private String divisionName;
    private int country_ID;

    /** Constructor for building an division.
     *
     * @param divisionID The id of the division.
     * @param divisionName The name of the division.
     * @param country_ID The country id for the division.
     */

    public firstLevelDivision(int divisionID,String divisionName, int country_ID){
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.country_ID = country_ID;
    }

    //Getter
    public int getDivisionID(){
        return divisionID;
    }
    public String toString() {
        return divisionName;
    }
    public int getCountry_ID(){
        return country_ID;
    }

    //Setter
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
