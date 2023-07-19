package Company.Model;

/** This class handles the contries.
 *
 **/
public class Country {
    private int countryID;
    private String countryName;


    /** Constructor for building a country.
     *
     * @param countryID The id of the country.
     * @param countryName The name of the country.
     */
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    //Getter
    public int getCountryID() {
        return countryID;
    }

    public String getCountryName() {
        return countryName;
    }


    /**
     * @return returns a name for the country for use in the combobox
     */
    @Override
    public String toString()
    {
        return (countryName);
    }
}
