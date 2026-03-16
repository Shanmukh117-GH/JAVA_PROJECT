import java.sql.*;

public class TransactionHistory {   //class to show the transaction history of the account holder

    public static void show(int accNo, String acc_type, double amt) {   //show method with parameters to show the transaction history by passing account number, account type and amount as parameters
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO transactions(acc_no, acc_type, amount) VALUES (?, ?, ?)"
            );
            ps.setInt(1, accNo);
            ps.setString(2, acc_type);
            ps.setDouble(3, amt);
            ps.executeUpdate();                          // executing the insert query to insert the transaction details in database

            con.close();
        } catch (Exception e) {
            System.out.println("Error recording transaction: " + e.getMessage()); // Print the error message for debugging
        }
    }

    public static void show(int accNo) {    // overloaded show method by passing accno as parameter to viewing of transaction history
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT acc_type, amount, date FROM transactions WHERE acc_no = ?"
            );
            ps.setInt(1, accNo);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n---- TRANSACTION HISTORY ----");

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getString("acc_type") + " | ₹ " +
                        rs.getDouble("amount") + " | " +
                        rs.getTimestamp("date")
                );
            }

            if (!found) {
                System.out.println("No transactions found.");
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error fetching transaction history: " + e.getMessage()); // Print the error message for debugging
        }
    }
}
