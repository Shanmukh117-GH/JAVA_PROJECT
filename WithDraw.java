import java.sql.*;
import java.util.Scanner;

public class WithDraw {                               // with draw class to perform withdraw operation

    public static void withdraw(int accNo) {           // method to perform withdraw operation by passing account number as parameter
        try {
            Connection con = DBConnection.getConnection();  // establishing connection with database by calling getConnection method of DBConnection class
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter withdraw amount: ");
            double amt = sc.nextDouble();

            PreparedStatement ps = con.prepareStatement(         // to get the current balance of the account from database
                "SELECT balance FROM users WHERE acc_no = ?"      
            );
            ps.setInt(1, accNo);          // setting the account number in the prepared statement
            ResultSet rs = ps.executeQuery();          

            if (rs.next()) {
                double balance = rs.getDouble("balance");   //by using getDouble method of result set to get the balance from database

                if (balance >= amt) {
                    PreparedStatement up = con.prepareStatement(
                        "UPDATE users SET balance = balance - ? WHERE acc_no = ?"  // to update the balance in database after withdraw operation
                    );
                    up.setDouble(1, amt);       // setting the withdraw amount in the prepared statement
                    up.setInt(2, accNo);        // setting the account number in the prepared statement  
                    up.executeUpdate();                        // executing the update query to update the balance in database     

                    TransactionHistory.show(accNo, "Withdraw", amt);   // calling the show method of TransactionHistory
                    System.out.println(amt +" withdrawn successfully");
                } else {
                    System.out.println("Insufficient balance");
                }
            } else {
                System.out.println("Account not found");
            }

            con.close();               // closing the connection with database
        } catch (Exception e) {
            System.out.println("Error during withdrawal: " + e.getMessage()); // Print the error message for debugging
        }
    }
}
