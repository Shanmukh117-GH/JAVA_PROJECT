import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {   // method to establish connection with database
        return DriverManager.getConnection(                       // using DriverManager class to get the connection 
            "jdbc:mysql://localhost:3306/bankingportal",       // by passing url, username and password of mysql database
            "root",
            "Dudi@2006"                                                       
           
        );
    }
}
