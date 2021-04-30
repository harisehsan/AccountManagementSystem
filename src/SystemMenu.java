import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SystemMenu extends Properties {
    int numOfCustomer = 0;
    int accountTypeChoice;
    String accountNumber;
    Properties properties = new Properties();


    AccountsOperations accountsOperations = new AccountsOperations();
    int accountnumber;
    int phoneNumber;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
     Transactions transactions = new Transactions();
    LoggedInCustomerOperations loggedInCustomerOperations = new LoggedInCustomerOperations();
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public void menu() throws IOException {
        System.out.println("\nAccount Management System Menu\n");
        System.out.println("1. Open a New Account");
        System.out.println("2. Login to an existing account");
        System.out.println("3. Close an existing account ");
        System.out.println("4. Open a closed account ");
        System.out.println("5. Display all accounts details");
        System.out.println("6. Display all accounts deductions");
        System.out.println("Please select the menu option: ");
        int choice = Integer.parseInt(in.readLine());
        switch (choice) {
            case 1: {
                accountsOperations.accountTypeSelection();
                accountsOperations.enterCustomerDetails();
            }
            break;
            case 2: {
                System.out.println("Enter the account number: ");
                accountNumber = in.readLine();
                accountsOperations.LoginUser(accountNumber);

            }
            break;
            case 3: {
                System.out.println("Enter the account number: ");
                accountNumber = in.readLine();
                accountsOperations.closeAnExistingAccount(accountNumber);
            }
            break;
            case 4: {
                System.out.println("Enter the account number: ");
                accountNumber = in.readLine();
                accountsOperations.openAnExistingAccount(accountNumber);
            }
            break;

            case 5: {
                accountsOperations.displayAllAccounts();
            }
            break;

            case 6: {
                transactions.displayAllAccountsDeductions();
            }
            break;
            default:
            {
                System.out.println("Invalid choice");
            }
        }
    }


}

