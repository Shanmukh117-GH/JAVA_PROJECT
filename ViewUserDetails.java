import java.sql.*;

public class ViewUserDetails {    // class to view the user details by passing account number as parameter

    public static void view(int accNo) throws Exception {  //view method to view user details
        Connection con = DBConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
            "SELECT * FROM users WHERE acc_no=?"
        );
        ps.setInt(1, accNo);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("\n--- USER DETAILS ---");
            System.out.println("Name: " + rs.getString("username"));
            System.out.println("Mobile: " + rs.getString("mobile_no"));
            System.out.println("Balance: ₹ " + rs.getDouble("balance"));
        }
        con.close();
    }
}
