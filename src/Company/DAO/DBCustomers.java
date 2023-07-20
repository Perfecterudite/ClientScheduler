package Company.DAO;

import Company.ConnectionDB.DatabaseConn;
import Company.Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomers {

    /**
     * This method returns all customers in the database.
     *
     * @return all customers in the database
     */
    public static ObservableList<Customers> getAllCustomers()
    {
        ObservableList<Customers> customerList = FXCollections.observableArrayList();

        try
        {
            String sqlgac = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, " +
                    "first_level_divisions.COUNTRY_ID, first_level_divisions.Division FROM customers, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID ORDER BY Customer_ID";

            PreparedStatement ps = DatabaseConn.getConnection().prepareStatement(sqlgac);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionId = rs.getInt("Division_ID");
                int countryId = rs.getInt("COUNTRY_ID");
                String divisionName = rs.getString("Division");

                Customers c = new Customers(customerId, customerName, address, postalCode, phone, divisionName, countryId, divisionId);
                customerList.add(c);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return customerList;
    }


    /**
     * This method adds a customer to the database.
     *
     * @param customerName The name of the customer.
     * @param address The address of the customer.
     * @param postalCode The postal code of the customer.
     * @param phone The phone number of the customer.
     * @param divisionId The division id of the customer.
     */
    public static void addCustomer(String customerName, String address, String postalCode, String phone, int divisionId)
    {
        try
        {

            String sqlac = "INSERT INTO customers VALUES (NULL, ?, ?, ?, ?, NOW(), 'RZ', NOW(), 'RZ', ?)";

            PreparedStatement psac = DatabaseConn.getConnection().prepareStatement(sqlac);

            psac.setString(1, customerName);
            psac.setString(2, address);
            psac.setString(3, postalCode);
            psac.setString(4, phone  );
            psac.setInt(5, divisionId);

            psac.execute();


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }



    }

    /**
     * This method updates a customer in the database.
     *
     * @param customerName The name of the customer.
     * @param address The address of the customer.
     * @param postalCode The postal code of the customer.
     * @param phone The phone number of the customer.
     * @param divisionId The division id of the customer.
     * @param customerId The id of the customer.
     */
    public static void updateCustomer(String customerName, String address, String postalCode, String phone, int divisionId, int customerId)
    {
        try
        {

            String sqluc = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";


            PreparedStatement psuc = DatabaseConn.getConnection().prepareStatement(sqluc);

            psuc.setString(1, customerName);
            psuc.setString(2, address);
            psuc.setString(3, postalCode);
            psuc.setString(4, phone  );
            psuc.setInt(5, divisionId);
            psuc.setInt(6, customerId);

            psuc.execute();


        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * This method deletes a customer from the database.
     *
     * @param customerId The id of the customer.
     */
    public static void deleteCustomer(int customerId)
    {
        try {

            String sqldc = "DELETE from customers where Customer_ID = ?";

            PreparedStatement psdc = DatabaseConn.getConnection().prepareStatement(sqldc);

            psdc.setInt(1, customerId);

            psdc.execute();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    /**
     * This method delete all appointments for a customer from the database.
     *
     * @param customerId The id of the customer.
     */
    public static void deleteCustomerAppt(int customerId)
    {
        try {

            String sqlda = "DELETE from appointments where Customer_ID = ?";

            PreparedStatement psda = DatabaseConn.getConnection().prepareStatement(sqlda);

            psda.setInt(1, customerId);

            psda.execute();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
