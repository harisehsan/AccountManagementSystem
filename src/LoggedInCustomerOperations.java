import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LoggedInCustomerOperations extends Properties{
    float transactionAmount = 0;
    float currentbalance = 0;
    int options=0;

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy (hh:mm)");
    Date date = new Date();

    public void showOptions() throws IOException {
        System.out.println("Logged in as a user: "+ AccountsOperations.accountTable[getLogedInUser()][2]+"\n");
        do {
            try {
                System.out.println("\n Transaction Menu\n");
                System.out.println("1. Check Balance");
                System.out.println("2. Make Deposite");
                System.out.println("3. Make Withdrawal");
                System.out.println("4. Transfer Amount");
                System.out.println("5. Print Statment");
                System.out.println("6. Display All Deductions");
                System.out.println("7. Calculate Zakat");
                System.out.println("8. Logout User");
                System.out.println("Enter your option: ");
                options = sc.nextInt();
                switch (options) {
                    case 1: {
                        checkbalance();
                    }
                    break;
                    case 2: {
                        makeDeposite();
                    }
                    break;
                    case 3: {
                        makeWithdrawal();
                    }
                    break;
                    case 4: {
                        transferAmount();
                    }
                    break;
                    case 5: {
                        printStatment();
                    }
                    break;

                    case 6: {
                        displayAllDeductions();
                    }
                    break;

                    case 7: {
                        calculateZakat();
                    }
                    break;
                    case 8: {
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice!");
                    }

                }
            } catch (Exception e) {
                continue;
            }
        } while (options != 8);

    }


    public void checkbalance()
    {
        System.out.println("The account holder name is: "+ AccountsOperations.accountTable[getLogedInUser()][2]);
        System.out.println("The available balance is Rs. "+ AccountsOperations.accountTable[getLogedInUser()][6]);
    }

    public void makeDeposite() throws IOException {
        do {
            System.out.println("Enter the amount to deposite: ");
            transactionAmount = sc.nextInt();
            if (transactionAmount <0) {
                System.out.println("Incorrect amount!");
                continue;
            }
            else {
                currentbalance = Float.parseFloat(AccountsOperations.accountTable[getLogedInUser()][6]);
                currentbalance = currentbalance + transactionAmount;
                perfromCheckingDeduction();
                AccountsOperations.accountTable[getLogedInUser()][6] = String.valueOf(currentbalance);
                Savetransaction("Deposite", transactionAmount, currentbalance);
                currentbalance = transactionAmount = 0;
                System.out.println("Transaction Successful!");
                break;
            }
        } while (true);

    }

    public void makeWithdrawal() throws IOException {
        do {
            System.out.println("Enter the amount to Withdraw: ");
            transactionAmount = sc.nextInt();
            currentbalance = Float.parseFloat(AccountsOperations.accountTable[getLogedInUser()][6]);
            if ((AccountsOperations.accountTable[getLogedInUser()][1].equalsIgnoreCase("Saving") && transactionAmount > currentbalance) || transactionAmount<0) {
                System.out.println("Incorrect amount!");
                continue;
            }
            else if ((AccountsOperations.accountTable[getLogedInUser()][1].equalsIgnoreCase("Checking") && (transactionAmount > currentbalance && transactionAmount > 5000) || transactionAmount<0)) {
                System.out.println("Incorrect amount!");
                continue;
            }
            else {
                currentbalance = currentbalance - transactionAmount;
                perfromCheckingDeduction();
                AccountsOperations.accountTable[getLogedInUser()][6] = String.valueOf(currentbalance);
                Savetransaction("Withdrawal", transactionAmount, currentbalance);
                currentbalance = transactionAmount = 0;
                System.out.println("Transaction Successful!");
                break;
            }
        } while (true);

    }

    public void transferAmount() throws IOException {
        do {
            System.out.println("Enter the amount to transfer to another account: ");
            transactionAmount = sc.nextInt();
            currentbalance = Float.parseFloat(AccountsOperations.accountTable[getLogedInUser()][6]);
            if (transactionAmount > currentbalance || transactionAmount<0) {
                System.out.println("Incorrect amount!");
                continue;
            }
            else {
                currentbalance = currentbalance - transactionAmount;
                perfromCheckingDeduction();
                AccountsOperations.accountTable[getLogedInUser()][6] = String.valueOf(currentbalance);
                Savetransaction("Transfer", transactionAmount, currentbalance);
                currentbalance = transactionAmount = 0;
                System.out.println("Transaction Successful!");
                break;
            }
        } while (true);

    }

    public void calculateZakat() throws IOException {
        float zakatAmount;
        String zakatflag="";
        if (AccountsOperations.accountTable[getLogedInUser()][1].equalsIgnoreCase("Saving")) {
            currentbalance = Float.parseFloat(AccountsOperations.accountTable[getLogedInUser()][6]);
        if (currentbalance >= 20000) {
            zakatAmount = (currentbalance * 2.5f) / 100;
            System.out.println("The zakat amount for that account is: "+zakatAmount);
            System.out.println("Deduct zakat from that account [y/n]?: ");
            zakatflag = in.readLine();
            if (zakatflag.equalsIgnoreCase("Y") || zakatflag.equalsIgnoreCase("y"))
            {
               currentbalance = currentbalance-zakatAmount;
               AccountsOperations.accountTable[getLogedInUser()][6] = String.valueOf(currentbalance);
               SaveDeduction(zakatAmount);
                System.out.println("Deduction Successful!");
            }
        }
        else
            System.out.println("Not enough bank balance for zakat!");
        } else {
            System.out.println("Not applicable for this account type. Zakat is only appilcable for saving account!");
        }
    }

    public void printStatment()
    {
        System.out.println("\nAccount Number\tDate & Time\tTransaction Type\tTransaction Amount\tAvailable Balance");
        for (int i=0; i<50;i++)
        {
            if (Transactions.transactionsTable[i][0].equalsIgnoreCase(AccountsOperations.accountTable[getLogedInUser()][0]))
            {
               for (int j=0;j<5;j++)
               {
                   System.out.print(Transactions.transactionsTable[i][j]+"\t");
               }
                System.out.println();
            }
        }
    }

    public void displayAllDeductions()
    {
        System.out.println("\nAccount Number\tDate & Time\tAccount Type\tDeduction Amount");
        for (int i=0; i<50;i++)
        {
            if (Transactions.deductionsTable[i][0].equalsIgnoreCase(AccountsOperations.accountTable[getLogedInUser()][0]))
            {
                for (int j=0;j<4;j++)
                {
                    System.out.print(Transactions.deductionsTable[i][j]+"\t");
                }
                System.out.println();
            }
            System.out.println("\nTransactions may include deductions. Please see deductions for details!");
        }
    }

    private void perfromCheckingDeduction() {
        if (AccountsOperations.accountTable[getLogedInUser()][1].equalsIgnoreCase("Checking")) {
            int taxCount= Integer.parseInt(AccountsOperations.accountTable[getLogedInUser()][7]);
            if (taxCount<2)
                SaveDeduction(0);
            else {
                SaveDeduction(10);
                currentbalance = currentbalance - 10;
            }
            taxCount++;
            AccountsOperations.accountTable[getLogedInUser()][7] = String.valueOf(taxCount);
        }
    }

    private void Savetransaction(String transationType, float transactionAmount, float availableBalance) {
        int trasaction_col=0;
        Transactions.transactionsTable[getTrasactionRow()][trasaction_col] = AccountsOperations.accountTable[getLogedInUser()][0];
        trasaction_col++;
        Transactions.transactionsTable[getTrasactionRow()][trasaction_col] = formatter.format(date);
        trasaction_col++;
        Transactions.transactionsTable[getTrasactionRow()][trasaction_col] = transationType;
        trasaction_col++;
        Transactions.transactionsTable[getTrasactionRow()][trasaction_col] = String.valueOf(transactionAmount);
        trasaction_col++;
        Transactions.transactionsTable[getTrasactionRow()][trasaction_col] = String.valueOf(availableBalance);
        setTrasactionRow(getTrasactionRow()+1);
    }

    private void SaveDeduction(float DeductionAmount) {
        int dedecution_col=0;
        Transactions.deductionsTable[getDeductionRow()][dedecution_col] = AccountsOperations.accountTable[getLogedInUser()][0];
        dedecution_col++;
        Transactions.deductionsTable[getDeductionRow()][dedecution_col] = formatter.format(date);
        dedecution_col++;
        Transactions.deductionsTable[getDeductionRow()][dedecution_col] = AccountsOperations.accountTable[getLogedInUser()][1];
        dedecution_col++;
        Transactions.deductionsTable[getDeductionRow()][dedecution_col] = String.valueOf(DeductionAmount);
        setDeductionRow(getDeductionRow()+1);
    }


}
