import java.sql.*;
import java.util.Scanner;

public class Deposit {                   // it is deposit class to deposit the money

    public static void deposit(int accNo) {            // deposit method to deposit the money by passing account number as parameter
        try {
            Connection con = DBConnection.getConnection();        
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter deposit amount: ");
            double amt = sc.nextDouble();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET balance = balance + ? WHERE acc_no = ?"
            );
            ps.setDouble(1, amt);
            ps.setInt(2, accNo);

            int rows = ps.executeUpdate();  // it affects number of rows will created in database table

            if (rows > 0) {
                TransactionHistory.show(accNo, "Deposit", amt);
                System.out.println(amt+" deposited successfully");
            } else {
                System.out.println("Account not found");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error during deposit: " + e.getMessage()); // Print the error message for debugging
        }
    }
}
