import java.util.Scanner;

public class BankPortalMenu {
    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("\n========== WELCOME TO BANK PORTAL ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    CreateAccount.create();   // it  executes account creation by using CreateAccount class
                    break;

                case 2:
                    int accNo = Login.login();    //  it executes login class 
                    if (accNo != -1) {
                        userMenu(accNo);          // it  shows user menu
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using our services! ");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

        static Scanner sc = new Scanner(System.in);      // taking input from user at cmd 

    static void userMenu(int accNo) throws Exception {           // it shows user menu after successful login and takes input from user 
                                                    // to perform different operations like
                                                 //  deposit, withdraw, apply services, view details and transaction history

    

    while (true) {
        System.out.println("\n========== BANK PORTAL ==========");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Apply Services (ATM / PAN)");
        System.out.println("4. View Account Details");
        System.out.println("5. Transaction History");
        System.out.println("6. Logout");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                Deposit.deposit(accNo);   // it  executes deposit class to deposit the money to account
                break;

            case 2:
                WithDraw.withdraw(accNo);    //  it executes withdraw class to withdraw money from account
                break;

            case 3:
                ApplyServices.apply(accNo);        //   it exceutes ApplyServices class like 
                break;                             // applying for ATM card or PAN card
                

            case 4:
                ViewUserDetails.view(accNo);       // it exceutes viewUserDetails class to view the details of user
                break;

           case 5:
                TransactionHistory.show(accNo);  // it executes TransactionHistory class to show transaction history of user
                break;

            case 6:
                System.out.println("Logout successful ");  
                return;

            default:
                System.out.println("Invalid choice! Try again.");
        }
    }
}
}
