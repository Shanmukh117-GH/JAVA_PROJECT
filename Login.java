import java.sql.*;
import java.util.Scanner;

public class Login {     //login class to login the new user with their account number and password

    public static int login() {    //login method to login the user by taking their acc_no,password
        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Account No: ");
            int accNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Password: ");
            String pass = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(           // it is interface of jdbc to execute parameterized query of sql
                "SELECT acc_no FROM users WHERE acc_no=? AND password=?"  
            );
            ps.setInt(1, accNo);       // setting the account number in the prepared statement
            ps.setString(2, pass);      // setting the password in the prepared statement

            ResultSet rs = ps.executeQuery();        // it is also interface of jdbc to execute the query and get the result from database

            if (rs.next()) {                        // it is a method of result set and it moves another row in database
                System.out.println("Login successful");
                return accNo;
            } else {
                System.out.println("Invalid login");
            }
            con.close();                              // closing the connection with database
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage()); // Print the error message for debugging
        }
        return -1;
    }
}
