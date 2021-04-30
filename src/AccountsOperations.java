import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AccountsOperations extends Properties {

    int accountnumber;
    int phoneNumber;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    LoggedInCustomerOperations loggedInCustomerOperations = new LoggedInCustomerOperations();

    static String[][] accountTable = new String[50][9];
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Random rnd = new Random();

    public void enterCustomerDetails() throws IOException {
        int col = 0;
        accountnumber = 100000 + rnd.nextInt(800000);
        AccountsOperations.accountTable[getAccount_Row()][col] = String.valueOf(accountnumber);
        col++;
        AccountsOperations.accountTable[getAccount_Row()][col] = getAccount_type();
        col++;
        System.out.println("\nEnter the new account details\n");
        System.out.println("\nEnter Name: ");
        AccountsOperations.accountTable[getAccount_Row()][col] = in.readLine();
        col++;
        System.out.println("\nEnter Address: ");
        AccountsOperations.accountTable[getAccount_Row()][col] = in.readLine();
        col++;
        System.out.println("Enter Phone Number: ");
        phoneNumber = in.read();
        AccountsOperations.accountTable[getAccount_Row()][col] = String.valueOf(phoneNumber);
        col++;
        AccountsOperations.accountTable[getAccount_Row()][col] = formatter.format(date);
        col++;
        AccountsOperations.accountTable[getAccount_Row()][col] = String.valueOf(0);
        col++;
        AccountsOperations.accountTable[getAccount_Row()][col] = String.valueOf(0);
        col++;
        AccountsOperations.accountTable[getAccount_Row()][col] = "Opened";
        System.out.println("The new customer has been successfully registered with account number: " + accountnumber);
        setAccount_Row(getAccount_Row() + 1);


    }

    public void accountTypeSelection() throws IOException {
        int accountTypeChoice;
        do {
            System.out.println("\nAccount types\n");
            System.out.println("1. Checking Account");
            System.out.println("2. Saving Account");
            System.out.println("Please select the account type: ");
            accountTypeChoice = Integer.parseInt(in.readLine());
            if (accountTypeChoice == 1)
                setAccount_type("Checking");
            else if (accountTypeChoice == 2)
                setAccount_type("Saving");
            else
                System.out.println("Invalid choice!");
        } while (accountTypeChoice != 1 && accountTypeChoice != 2);
    }

    public void openAnExistingAccount(String accountNumber) {
        for (int i = 0; i < 50; i++) {
            try {
                if (AccountsOperations.accountTable[i][0].equalsIgnoreCase(accountNumber)) {
                    if ((AccountsOperations.accountTable[i][8].equalsIgnoreCase("Closed")))
                        AccountsOperations.accountTable[i][8] = "Opened";
                    return;
                } else {
                    System.out.println("The Account is already opened!");
                    return;
                }

            } catch (Exception e) {
                continue;
            }
        }

        System.out.println("The account number does not exist!");
    }

    public void closeAnExistingAccount(String accountNumber) {
        for (int i = 0; i < 50; i++) {
            try {
                if (AccountsOperations.accountTable[i][0].equalsIgnoreCase(accountNumber)) {
                    if ((AccountsOperations.accountTable[i][8].equalsIgnoreCase("Opened")))
                        AccountsOperations.accountTable[i][8] = "Closed";
                    return;
                } else {
                    System.out.println("The Account is already Closed!");
                    return;
                }

            } catch (Exception e) {
                continue;
            }
        }

        System.out.println("The account number does not exist!");
    }

    public void LoginUser(String accountNumber) {

        for (int i = 0; i < 50; i++) {
            try {
                if (AccountsOperations.accountTable[i][0].equalsIgnoreCase(accountNumber)) {
                    setLogedInUser(i);
                    if ((AccountsOperations.accountTable[i][8].equalsIgnoreCase("Opened"))) {
                        loggedInCustomerOperations.showOptions();
                    } else {
                        System.out.println("This account is closed!");
                        return;
                    }

                }

            } catch (Exception e) {
                continue;
            }


            System.out.println("The account number does not exist!");
        }

    }

    public  void displayAllAccounts()
    {
        try {
            System.out.println("\nAccount_Number\tAccount_Type\tName\tAddress\tPhone_Number\tDate\tCurrent_Balance\tNumber_of_Transactions\tAccount_Status");
            for (int i=0; i<50;i++)
            {
                if (!AccountsOperations.accountTable[i][0].equalsIgnoreCase(null)) {
                    for (int j=0;j<9;j++)
                    {
                        System.out.print(AccountsOperations.accountTable[i][j]+"\t\t");
                    }
                    System.out.println();

                }
            }
            System.out.println("\nThe Bank Owner Name is: Muhammad Haris Ehsan");
        } catch (Exception e) {

        }

    }
}
