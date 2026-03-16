import java.sql.*;
import java.util.Scanner;

public class ApplyServices {          // it is class to apply for services like atm card and pan card

    public static void apply(int accNo) throws Exception {   // apply method to apply for services by passing account number as parameter
        Connection con = DBConnection.getConnection();
        Scanner sc = new Scanner(System.in);

        System.out.println("1. ATM Card");
        System.out.println("2. PAN Card");
        int ch = sc.nextInt();

        String service = (ch == 1) ? "ATM Card" : "PAN Card";

        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO services(acc_no,service_type) VALUES (?,?)"
        );
        ps.setInt(1, accNo);
        ps.setString(2, service);
        ps.executeUpdate();

        System.out.println(service + " Applied");
        con.close();
    }
}
