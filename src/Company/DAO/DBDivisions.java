package Company.DAO;


import Company.ConnectionDB.DatabaseConn;
import Company.Model.firstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBDivisions {



    /**
     * This method returns all divisions in the database that are located in the United States.
     *
     * @return all divisions that have a country id of 1
     */
    public static ObservableList<firstLevelDivision> getUSDivisions()
    {
        ObservableList<firstLevelDivision> USDivisions = FXCollections.observableArrayList();

        try
        {
            String sql = "SELECT * from first_level_divisions where COUNTRY_ID = 1";

            PreparedStatement ps = DatabaseConn.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                firstLevelDivision D = new firstLevelDivision(divisionId, divisionName, countryId);
                USDivisions.add(D);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return USDivisions;
    }

    /**
     * This method returns all divisions in the database that are located in the United Kingdom.
     *
     * @return all divisions that have a country id of 2
     */
    public static ObservableList<firstLevelDivision> getUKDivisions()
    {
        ObservableList<firstLevelDivision> UKDivisions = FXCollections.observableArrayList();

        try
        {
            String sql = "SELECT * from first_level_divisions where COUNTRY_ID = 2";

            PreparedStatement ps = DatabaseConn.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                firstLevelDivision D = new firstLevelDivision(divisionId, divisionName, countryId);
                UKDivisions.add(D);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return UKDivisions;
    }

    /**
     * This method returns all divisions in the database that are located in Canada.
     *
     * @return all divisions that have a country id of 3
     */
    public static ObservableList<firstLevelDivision> getCADivisions()
    {
        ObservableList<firstLevelDivision> CADivisions = FXCollections.observableArrayList();

        try
        {
            String sql = "SELECT * from first_level_divisions where COUNTRY_ID = 3";

            PreparedStatement ps = DatabaseConn.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("COUNTRY_ID");
                firstLevelDivision D = new firstLevelDivision(divisionId, divisionName, countryId);
                CADivisions.add(D);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return CADivisions;
    }

}
