package Company.ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConn {

    private static String protocol = "jdbc";
    private static String vendorName = ":mysql:";
    private static String ipAddress = "//localhost:3306/";
    private static String dbName = "client_Schedule";


    //JDBC URL
    private static String jdbcURL = protocol + vendorName +ipAddress + dbName;

    //Driver and Connection interface
    private static String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    private static String username = "sqlUser";
    private static String password = "Passw0rd!";

    public static Connection startConnection(){
        try{
            Class.forName(mysqlJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Database Connection successful");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return conn;


    }

    public static Connection getConnection(){
        return conn;
    }

    public static void endConnection() {
        try {
            conn.close();
            System.out.println("Connection terminated");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
