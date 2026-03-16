import java.sql.*;
import java.util.Scanner;

public class CreateAccount {

    public static void create() {
        try {
            Connection con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);

            System.out.print("Username: ");
            String name = sc.nextLine();

            System.out.print("Password: ");
            String pass = sc.nextLine();

            System.out.print("Aadhaar: ");
            long aadhaar = sc.nextLong();
            sc.nextLine();

            System.out.print("Mobile: ");
            long mobile = sc.nextLong();
            sc.nextLine();

            System.out.print("Account Type: ");
            String type = sc.nextLine();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users (username,password,aadhaar_no,mobile_no,acc_type,balance) VALUES (?,?,?,?,?,0)",
                Statement.RETURN_GENERATED_KEYS    // to get the generated account number after insertion
            );

            ps.setString(1, name);   // setting the values by usind setstring methods of prepared statement
            ps.setString(2, pass);
            ps.setLong(3, aadhaar);
            ps.setLong(4, mobile);
            ps.setString(5, type);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();      // to get the generated account number after insertion by using getGeneratedKeys method of prepared statement
            if (rs.next()) {
                System.out.println("Account Created");
                System.out.println("Account Number: " + rs.getInt(1));
            }
            con.close();

        } catch (Exception e) {
            System.out.println("Error creating account:" + e.getMessage()); // Print the error message for debugging
    }
}
}
